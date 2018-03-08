package javaThread.pool;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @Description: 线程池案例 newCachedThreadPool,newFixedThreadPool,newSingleThreadExecutor,newScheduledThreadpool,newSingleThreadScheduleExcutor
 * @Author: zyw
 * @Date: 2018/3/3
 */
public class ThreadPoolTest {
    private static final int FILE_QUEUE_SIZE = 10;
    private static final int SEARCH_THREADS = 100;
    private static final File DUMMY = new File("");
    private static BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

    public static void main(String[] args) {
        try(Scanner in = new Scanner(System.in)) {
            System.out.println("Enter base directory：");
            String directory = in.nextLine();
            System.out.println("Enter keyword: ");
            String keyword = in.nextLine();
            //newCachedThreadPool 是无限大小的线程池，它会重用空闲的线程，在线程都满的情况下，不断创建新的线程
            //线程空余60s后会被销毁
            //在 64-bit JDK 1.7 中 -Xss 默认是 1024k，也就是 1M，那如果创建10000个线程，就是需要 10000*1M = 10G 的堆外内存空间来给线程使用
            //但是我的机器总共就 8G 内存，不够创建新的线程的时候会报OutOfMemoryError异常
            //内部采用的工作队列是SynchronousQueue，因此任务会按照顺序一个接一个的处理
            ExecutorService pool = Executors.newCachedThreadPool();

            final MatchCounter counter = new MatchCounter(new File(directory),keyword,pool);
            //FutureTask 既实现了Future,可接受callable，也实现了Runnable,可以直接执行
            //用FutureTask可以在提交线程池后不用再获得其返回值
            final FutureTask<Integer> result = new FutureTask<Integer>(counter);
            //为什么之上的要用final，jvm在数据之间没有关联的时候是可以进行重排序的。初始化FutureTask对象和将对象引用提交到线程池这两步会发生重排序。
            //也就是说会提交到还未完成初始化的对象引用到线程池。
            //线程池会启动新的线程处理。由于新的线程，因此无法与原线程的重排进行契合（原线程中的重排在数据使用前是必须完成初始化的），所以有空指针异常风险。
            pool.submit(result);
            //不使用FutureTask也是可以,FutureTask相当于是一个工具类
            //Future<Integer> result = pool.submit(counter);
            try {
                System.out.println(result.get() + " matching files");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            pool.shutdown();
            int largestPoolSize = ((ThreadPoolExecutor)pool).getLargestPoolSize();
            System.out.println("largest pool size= " + largestPoolSize);
        }
    }
}
class MatchCounter implements Callable<Integer>{
    private File directory;
    private String keyWord;
    private ExecutorService pool;//里面要进行递归调用所以带一个pool引用进来
    private int count;

    public MatchCounter(File directory, String keyWord, ExecutorService pool) {
        this.directory = directory;
        this.keyWord = keyWord;
        this.pool = pool;
    }

    @Override
    public Integer call() throws Exception {
        count = 0;
        List<Future<Integer>> results = new ArrayList<>();
        File[] files = directory.listFiles();
        for(File file : files){
            if(file.isDirectory()){
                MatchCounter counter = new MatchCounter(file,keyWord,pool);
                FutureTask<Integer> result = new FutureTask<Integer>(counter);
                pool.submit(result);
                results.add(result);//将results用集合装起来，这样后面可以一起获取执行结果
            }else{
                if(search(file)) count++;
            }
        }
        for(Future<Integer> result : results){
            count += result.get();
        }
        return count;
    }

    public boolean search(File file){
        try {
            try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file),"UTF-8"))){//编码方式需要和实际文件的编码方式相匹配，才能正确读取中文不出现乱码
                boolean found = false;
                while(!found){
                    String line = bufferedReader.readLine();
                    if(line.contains(keyWord)){
                        System.out.println(file.getName());
                        System.out.println(line);
                        found = true;}
                }
                return found;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}