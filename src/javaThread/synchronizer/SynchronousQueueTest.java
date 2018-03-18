package javaThread.synchronizer;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;

/**
 * @Description:允许一个线程把对象交给另一个线程
 * @Author: zyw
 * @Date: 2018/3/9
 */
public class SynchronousQueueTest {

    private static final SynchronousQueue<Integer> queue = new SynchronousQueue<>();
    private static final Random random = new Random(47);
    private static final ExecutorService exe = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        Runnable put = ()->{
            int object = random.nextInt(100);
            System.out.println("开始放入元素"+object+"操作...");
            try {
                queue.put(object);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //放入元素会一直阻塞到获取元素的时候，才会成功放入
            System.out.println("成功放入元素"+object);
        };

        Runnable take = () -> {
            Integer object = null;
            System.out.println("开始获取元素操作...");

            try {
                object = queue.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("成功获取元素"+object);
        };

        for(int i = 0; i< 10;i++){
            exe.submit(put);
        }
        for(int i = 0; i< 5;i++){
            exe.submit(take);
        }

    }

}
