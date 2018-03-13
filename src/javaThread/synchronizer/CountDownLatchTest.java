package javaThread.synchronizer;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 线程集等待计数器
 * @Author: zyw
 * @Date: 2018/3/9
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(2);
        final Integer[] arr = new Integer[10];

        Runnable r = ()->{
            System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
            try {
                Thread.sleep(3000);
                for(int i = 0;i<5;i++){
                    arr[i] = i;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
            countDownLatch.countDown();
        };
        Runnable r2 = ()->{
            System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
            try {
                Thread.sleep(3000);
                for(int i = 5;i<10;i++){
                    arr[i] = i;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
            countDownLatch.countDown();
        };
        Arrays.asList(arr).forEach(System.out::print);
        System.out.println("启动两个并行计算的线程");
       /* Thread t1 = new Thread(r);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();*/
        ExecutorService pool = Executors.newCachedThreadPool();
        pool.submit(r);
        pool.submit(r2);
        System.out.println("当前线程等待两个线程执行完毕...");
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("2个线程已经执行完毕");
        System.out.println("继续执行当前线程");
        Arrays.asList(arr).forEach(System.out::print);
    }
}
