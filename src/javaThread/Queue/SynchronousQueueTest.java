package javaThread.Queue;

import java.util.concurrent.SynchronousQueue;

/**
 * @Description: SynchronousQueue
 * @Author: zyw
 * @Date: 2018/3/7
 */
public class SynchronousQueueTest {

    public static void main(String[] args) throws InterruptedException {
        final SynchronousQueue<Integer> blockingQueue = new SynchronousQueue<Integer>();

        Runnable r1 = ()->{
            System.out.println("put start");
            try {
                //阻塞，直到有线程take，实现了线程之间的数据传递
                //FIFO
                blockingQueue.put(2);
                blockingQueue.put(1);
                blockingQueue.put(2);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("put end");
        };
        Runnable r2 = ()->{
            System.out.println("take start");
            try {
                int num = blockingQueue.take();
                while(num>0){
                    System.out.println("take from queue:" + blockingQueue.take());
                    num--;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("take end");
        };
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        Thread.sleep(5*1000);
        t2.start();

    }
}
