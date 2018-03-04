package javaThread.Queue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * @Description: Future测试
 * @Author: zyw
 * @Date: 2018/3/3
 */
public class FutureTest {

    public static void main(String[] args) {
        try(Scanner in = new Scanner(System.in)){
            System.out.println("Enter base directory：");
            String directory = in.nextLine();
            System.out.println("Enter keyword: ");
            String keyword = in.nextLine();

            MatchCounter counter = new MatchCounter(new File(directory),keyword);
            FutureTask<Integer> task = new FutureTask<Integer>(counter);
            Thread t = new Thread(task);
            t.start();

            try {
                System.out.println(task.get() + "matching files");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
class MatchCounter implements Callable<Integer>{
    private File director;
    private String keyWord;

    public MatchCounter(File director, String keyWord) {
        this.director = director;
        this.keyWord = keyWord;
    }

    @Override
    public Integer call() throws Exception {
        int count = 0;
        File[] files = director.listFiles();
        List<Future<Integer>> results = new ArrayList();//装载嵌套的任务
        for(File file : files){
            if(file.isDirectory()){
                MatchCounter counter = new MatchCounter(file,keyWord);
                FutureTask<Integer> task = new FutureTask<Integer>(counter);
                results.add(task);//这个引用用于后续读取线程的计算结果
                Thread t = new Thread(task);
                t.start();
            }else{
                if(search(file)) count++;
            }
        }
        for(Future<Integer> result : results){
            count += result.get();
        }
        return 1;
    }
    public boolean search(File file) throws FileNotFoundException {
        try(Scanner in = new Scanner(file,"UTF-8")){
            boolean found = false;
            while(!found && in.hasNextLine()){
                String line = in.nextLine();
                if(line.contains(keyWord)) found = true;
            }
            return found;
        }
    }
}