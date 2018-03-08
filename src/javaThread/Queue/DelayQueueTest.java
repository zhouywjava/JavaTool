package javaThread.Queue;

import javaThread.model.DelayedEle;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.TimeUnit;

/**
 * @Description: DelayWorkQueue 重试机制，当调用接口失败后，把当前调用信息放到delay10s的元素中，放入队列。时间由放入队列的时候开始算
 * @Author: zyw
 * @Date: 2018/3/7
 */
public class DelayQueueTest {

    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayedEle> delayQueue = new DelayQueue<DelayedEle>();
        DelayedEle element1 = new DelayedEle(10000,"zyw");
        DelayedEle element2 = new DelayedEle(15000,"wl");
       /* long delay = element1.getDelay(TimeUnit.SECONDS);
        System.out.println(delay);//10
        Thread.sleep(5000);
        System.out.println(delay);//10*/
        delayQueue.offer(element1);
        delayQueue.offer(element2);
        long start = System.currentTimeMillis();
        System.out.println("开始取元素" );
        element1 = delayQueue.take();
        long mid = System.currentTimeMillis();
        element2 = delayQueue.take();
        long end = System.currentTimeMillis();
        System.out.println("取出元素: " + element1 + "时间:" + (mid - start));
        System.out.println("取出元素: " + element2 + "时间:" + (end - start));
    }
}
