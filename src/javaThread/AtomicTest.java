package javaThread;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description: 原子类
 * @Author: zyw
 * @Date: 2018/3/1
 */
public class AtomicTest {

    public static void main(String[] args) {
        AtomicLong next = new AtomicLong();
        Runnable r = ()->{
            //会发现打印的顺序有的时候会出现逆序，这是因为incrementAndGet方法和打印方法之间是可中断的
            System.out.println(next.incrementAndGet());
        };
        for(int i = 0; i<100; i++){
            Thread thread = new Thread(r);
            thread.start();
        }
    }
}
