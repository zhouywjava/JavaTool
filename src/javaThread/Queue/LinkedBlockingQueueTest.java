package javaThread.Queue;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @Description:LinkedBlockingQueue
 * @Author: zyw
 * @Date: 2018/3/7
 */
public class LinkedBlockingQueueTest {

    public static void main(String[] args) {
        LinkedBlockingQueue<Integer> blockingQueue = new LinkedBlockingQueue<>();
        blockingQueue.add(1);
        blockingQueue.offer(1);
        try {
            blockingQueue.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        blockingQueue.peek();
        blockingQueue.element();
        blockingQueue.remove();
        blockingQueue.poll();
        try {
            blockingQueue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
