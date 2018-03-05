package javaThread.pool;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
            ExecutorService pool = Executors.newCachedThreadPool();
        }
    }

    public static void enumerate(File directory) throws InterruptedException {
        File[] files = directory.listFiles();
        for(File file : files){
            if(file.isDirectory())enumerate(file);
            else queue.put(file);//阻塞方法
        }
    }

    public static void search(File file,String keyword) throws FileNotFoundException {
        try(Scanner in = new Scanner(file,"UTF-8")){
            int lineNum = 0;
            while(in.hasNextLine()){
                lineNum++;
                String line = in.nextLine();
                if(line.contains(keyword)){
                    System.out.printf("%s:%d:%s&n",file.getPath(),lineNum,line);
                }
            }

        }
    }
}
