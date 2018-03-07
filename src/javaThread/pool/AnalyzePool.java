package javaThread.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Description: 线程池分析
 * @Author: zyw
 * @Date: 2018/3/5
 */
public class AnalyzePool {

    //必要时创建新的线程，空闲线程会被保留60s
    ExecutorService pool = Executors.newCachedThreadPool();
    //该池包含固定数量的线程，空闲线程会一直被保留
    ExecutorService pool2 = Executors.newFixedThreadPool(10);
    //只有一个线程的线程池，该线程顺序执行每一个提交的任务
    ExecutorService pool3 = Executors.newSingleThreadExecutor();
    //用于预定执行构建固定的线程池 比如5个。java.util.timer
    ExecutorService pool4 = Executors.newScheduledThreadPool(5);
    //用于预定执行而构建的但线程池
    ExecutorService pool5 = Executors.newSingleThreadScheduledExecutor();

}
