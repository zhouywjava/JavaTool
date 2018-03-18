package javaThread.synchronizer;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Description: 信号量 Semaphore经常用于限制获取某种资源的线程数量
 * @Author: zyw
 * @Date: 2018/3/9
 */
public class SemaphoreTest {

    //假设有40个请求
    private static final int COUNT = 40;
    private static final ExecutorService executor = Executors.newFixedThreadPool(COUNT);
    //假设只有15个数据库连接
    private static final Semaphore semaphore = new Semaphore(15);
    private static final Random random = new Random(47);

    public static void main(String[] args) {
        Runnable r = ()->{
            try {
                if(semaphore.availablePermits() == 0) {
                    System.out.println(Thread.currentThread().getName() + "没有获得执行机会,进行等待");
                }

                //申请许可证
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + "获得执行机会");
                Thread.sleep(random.nextInt(3000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally{
                System.out.println(Thread.currentThread().getName() + "执行结束,释放许可证");
                //归还许可证,不归还的话无法接收新的线程并执行
                semaphore.release();
            }

        };

        for(int i = 0;i<COUNT;i++){
            executor.submit(r);
        }
    }


}
