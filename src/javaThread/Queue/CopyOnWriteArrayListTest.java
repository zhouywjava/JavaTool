package javaThread.Queue;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: CopyOnWriteArrayList 测试类 说明了CopyOnWriteArrayList并发多线程的环境下，仍然能很好的工作。
 * @Author: zyw
 * @Date: 2018/3/4
 */
public class CopyOnWriteArrayListTest {


    public static void main(String[] args) {
        final List<Integer> list = new CopyOnWriteArrayList<>(Arrays.asList(new Integer[]{1,2,3}));

        Runnable read = () -> {
            list.forEach(System.out::println);
            System.out.println("====================");
        };
        Runnable write = () -> {
            list.add(5);
        };

        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new Thread(read));
        executorService.execute(new Thread(write));
        executorService.execute(new Thread(read));
        executorService.execute(new Thread(write));
        executorService.execute(new Thread(read));
        executorService.execute(new Thread(write));
        executorService.execute(new Thread(read));
        executorService.execute(new Thread(write));

        System.out.println("List size:"+list.size());
    }
}
