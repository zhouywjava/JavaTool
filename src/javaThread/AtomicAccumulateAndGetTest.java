package javaThread;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description: 原子类新方法测试
 * @Author: zyw
 * @Date: 2018/3/2
 */
public class AtomicAccumulateAndGetTest {

    public static void main(String[] args) {
        AtomicLong atomicLong = new AtomicLong();
        Runnable r = ()->{
            //是updateandget方法的扩展，增加了一个参与计算的参数。
            //与updateandget方法一个样能够提供计算函数
            //底层也是依靠循环+compareandset来实现
            System.out.println(atomicLong.accumulateAndGet(1,(x,y)->x + y));
        };
        for(int i = 0;i<100;i++){
            Thread t = new Thread(r);
            t.start();
        }
    }
}
