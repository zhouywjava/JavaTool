package javaThread.Queue;

import java.util.PriorityQueue;

/**
 * @Description: 优先级队列
 * @Author: zyw
 * @Date: 2018/3/8
 */
public class PriorityQueueTest {

    public static void main(String[] args) {
        PriorityQueue<String> priorityQueue = new PriorityQueue<>();
        priorityQueue.add("dog");
        priorityQueue.add("apple");
        priorityQueue.add("fox");
        priorityQueue.add("easy");
        priorityQueue.add("boy");

        priorityQueue.forEach(System.out::println);
        System.out.println("========================");
        while (!priorityQueue.isEmpty()){
            System.out.println(priorityQueue.poll());
        }
    }
}
