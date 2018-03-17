package javaThread.synchronizer;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 线程间交换对象 Exchanger 提供一个同步点，在这个同步点处，两个线程可以交换彼此数据。
 * 即一个线程调用了exchange( )方法交换数据，到达了同步点，然后就会一直阻塞等待另一个线程调用exchange( )方法来交换数据。
 * 所以，要注意exchange( )方法是有阻塞的特性。Exchanger 可能在应用程序（比如遗传算法和管道设计）中很有用。
 * @Author: zyw
 * @Date: 2018/3/9
 */
public class ExchangerTest {

    private static final Exchanger<String> exgr = new Exchanger<String>();
    private static final ExecutorService exservice = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        Runnable r1 = ()->{
            String A = "A 的信息";
            System.out.println("线程：" +Thread.currentThread().getName() + "交换前:" + A);
            try {
                A = exgr.exchange(A);//同步点交换数据
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程：" +Thread.currentThread().getName() + "交换后:" + A);
        };
        Runnable r2 = ()->{
            String B = "B 的信息";
            System.out.println("线程：" +Thread.currentThread().getName() + "交换前:" + B);
            try {
                B = exgr.exchange(B);//同步点交换数据
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("线程：" +Thread.currentThread().getName() + "交换后:" + B);
        };
        exservice.submit(r1);
        exservice.submit(r2);

    }

}
