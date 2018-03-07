package javaThread.Queue;

import javaThread.model.Consumer;
import javaThread.model.Producer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Description: ArrayListBlockingQueue
 * @Author: zyw
 * @Date: 2018/3/7
 */
public class ArrayListBlockingQueueTest {

    public static void main(String[] args) {
        //创建一个容量为10 的ArrayBlockingQueue
       ArrayBlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(10);

       //生产者
        Producer producer = new Producer(blockingQueue);
        //消费者
        Consumer consumer = new Consumer(blockingQueue);

        ExecutorService executor = Executors.newFixedThreadPool(10);
        executor.submit(producer);
        executor.submit(producer);
        executor.submit(producer);
        executor.submit(consumer);

        try {
            Thread.sleep(5*1000);
            System.out.println("10s 后关闭线程池");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        producer.stop();//通知相关生产线程停止
        consumer.stop();//通知相关消费线程停止
        executor.shutdown();//1.线程池不在接受任何任务。2.正在执行的任务继续执行到完
        Thread.yield();//主线程让出cpu*/
        try {
            //shutdown后，等待2s还不结束，则调用立即结束，会去中断线程池里的线程
            if(!executor.awaitTermination(5, TimeUnit.SECONDS)){
                //立即结束
                executor.shutdownNow();
            }else{
                System.out.println("线程池内的线程全部在5s内停止");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("awaitTermination 被中断也会中断线程池内部的线程");
        }
        System.out.println("END");
    }
}
