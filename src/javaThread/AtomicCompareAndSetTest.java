package javaThread;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description: 比较更新的方式比用锁,原子类快,但也是要不断尝试更新。
 * @Author: zyw
 * @Date: 2018/3/1
 */
public class AtomicCompareAndSetTest {

    public static void main(String[] args) {
        AtomicLong atomicLong = new AtomicLong();

        Runnable r = () ->{
            long current,newOne;
            do{
               current = atomicLong.get();
               newOne = current + 1;
            }while(!atomicLong.compareAndSet(current,newOne));
            System.out.println(newOne);
        };
        for(int i = 0;i<100;i++){
            Thread t = new Thread(r);
            t.start();
        }

    }
}
