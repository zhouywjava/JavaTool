package javaThread;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Description: Volatitle使用场景 结果不依赖于变量或者只有一个线程做写入操作
 * @Author: zyw
 * @Date: 2018/3/3
 */
public class VolatileTest2 {

    private static volatile boolean close = false;

    public static void main(String[] args) {
        Runnable r1 = () ->{
            close = true;
            System.out.println("发出通知停止");
        };
        Runnable r2 = () -> {
            while(!close)
            System.out.println("我在运行中...");

            System.out.println("收到通知停止");
        };
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t2.start();
        Timer delay = new Timer();
        delay.schedule(new TimerTask() {
            @Override
            public void run() {
                t1.start();
            }
        }, 5000);
    }
}
