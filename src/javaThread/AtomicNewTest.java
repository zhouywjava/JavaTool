package javaThread;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description: 使用lamda表达式更新变量，不用使用循环
 * @Author: zyw
 * @Date: 2018/3/1
 */
public class AtomicNewTest {

    public static void main(String[] args) {
        AtomicLong atomicLong = new AtomicLong();
        Runnable r = ()->{
            long current = atomicLong.get();
            long newOne = atomicLong.updateAndGet(x -> x++);
            System.out.println(newOne);
        };
        for(int i = 0;i<100;i++){
            Thread t = new Thread(r);
            t.start();
        }
    }
}
