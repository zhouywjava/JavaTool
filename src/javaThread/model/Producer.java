package javaThread.model;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 生产者
 * @Author: zyw
 * @Date: 2018/3/7
 */
public class Producer implements Runnable{

    private volatile boolean isRunning = true;
    private BlockingQueue<String> queue;
    private static AtomicInteger count = new AtomicInteger();
    private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;

    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    public void stop(){
        System.out.println("所有生产者停止");
        isRunning = false;
    }

    @Override
    public void run() {
        System.out.println("启动生产者线程");
        Random r = new Random();
        Thread curT = Thread.currentThread();
        try {
            while(isRunning && !curT.isInterrupted()){
                System.out.println(curT.getName() + "正在生产数据...");
                Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
                String data = "data: " + count.incrementAndGet();
                System.out.println(curT.getName() + "将数据:"+data+"放入队列...");
                /*if(!queue.offer(data,2, TimeUnit.SECONDS)){
                    System.out.println("无法放入数据: " + data);
                };*/
                queue.put(data);
            }
            //除非外部能拿到本线程的引用，调用interrupt()否则中断标识不会被设置
            if(curT.isInterrupted()){
                System.out.println(curT.getName() + "中断");
            }
            if(!isRunning){
                System.out.println(curT.getName() + "停止");
            }
        } catch (InterruptedException e) {
            //e.printStackTrace();
            System.out.println(curT.getName() + "中断异常捕获");
        }finally {
            System.out.println(curT.getName() + "退出生产者线程");
        }
    }
}
