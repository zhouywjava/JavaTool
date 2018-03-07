package javaThread.model;

import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @Description: 消费者
 * @Author: zyw
 * @Date: 2018/3/7
 */
public class Consumer implements Runnable{

    private volatile boolean isRunning = true;
    private BlockingQueue<String> blockingQueue;
    private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;

    public Consumer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public void stop(){
        System.out.println("所有消费者停止");
        isRunning = false;
    }

    @Override
    public void run() {
        System.out.println("启动消费者线程");
        Random random = new Random();
        Thread curT = Thread.currentThread();
        try {
            while(isRunning && !curT.isInterrupted()){
                System.out.println(curT.getName() + "正在从队列获取数据...");
                /*String data = blockingQueue.poll(2, TimeUnit.SECONDS);*/
                String data = blockingQueue.take();
                if(data != null){
                    System.out.println(curT.getName() + "拿到数据: " + data);
                    System.out.println(curT.getName() + "正在消费数据: " + data);
                    Thread.sleep(random.nextInt(DEFAULT_RANGE_FOR_SLEEP));
                }else{
                    stop();//2s 都取不到数据，认为生产线程已经退出，所以消费线程也退出
                }
            }
            if(curT.isInterrupted()){
                System.out.println(curT.getName() + "中断");
            }
            if(!isRunning){
                System.out.println(curT.getName() + "停止");
            }
        } catch (InterruptedException e) {
            //e.printStackTrace();
            System.out.println(curT.getName() + "中断异常捕获");
        }finally{
            System.out.println(curT.getName() + "退出消费者线程！");
        }
    }
}
