package javaThread.Queue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @Description: 阻塞队列
 * @Author: zyw
 * @Date: 2018/3/3
 */
public class BlockingQueueTest {
    private static final int FILE_QUEUE_SIZE = 10;
    private static final int SEARCH_THREADS = 100;
    private static final File DUMMY = new File("");
    private static BlockingQueue<File> queue = new ArrayBlockingQueue<>(FILE_QUEUE_SIZE);

    public static void main(String[] args) {
        try(Scanner in = new Scanner(System.in)){
            System.out.println("Enter base directory：");
            String directory = in.nextLine();
            System.out.println("Enter keyword: ");
            String keyword = in.nextLine();

            //整理目录及其子目录下的文件到队列
            Runnable enumerator = ()->{
                try {
                    enumerate(new File(directory));
                    queue.put(DUMMY);//表示最后一个包的虚拟包
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };

            new Thread(enumerator).start();

            //创建指定线程数的线程，去每一个文件中查询关键字keyword
            for(int i = 0; i<SEARCH_THREADS;i++){
                Runnable searcher = ()->{
                    try {
                        boolean done = false;
                        while (!done) {
                            File file = queue.take();
                            if (file == DUMMY) {
                                queue.put(file);
                                done = true;
                            } else {
                                search(file, keyword);
                            }
                        }
                    }catch (InterruptedException e1) {
                        e1.printStackTrace();
                    } catch (FileNotFoundException e2) {
                        e2.printStackTrace();
                    }
                };
                new Thread(searcher).start();
            }
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
