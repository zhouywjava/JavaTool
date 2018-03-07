package javaThread.Queue;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @Description: SynchronousQueue
 * @Author: zyw
 * @Date: 2018/3/7
 */
public class SynchronousQueueTest {

    public static void main(String[] args) {
        ArrayBlockingQueue<Integer> blockingQueue = new ArrayBlockingQueue<Integer>(10);
        blockingQueue.add(1);
        blockingQueue.offer(1);
        try {
            blockingQueue.put(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        blockingQueue.peek();
        blockingQueue.poll();
    }
}
