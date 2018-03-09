package javaThread.pool;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.DoublePredicate;

/**
 * @Description: Fork-join framework 用来将任务分解为小任务，然后最后在把结果总结起来
 * @Author: zyw
 * @Date: 2018/3/8
 */
public class ForkJoinTest {

    public static void main(String[] args) {
        //初始化一个大小为1百万的数组
        final int SIZE = 1000000;
        double[] numbers = new double[SIZE];
        for(int i = 0; i<SIZE ; i++){
            numbers[i] = Math.random();
        }
        //计数器，统计大于0.5的数的个数
        //创建RecursiveTask
        Counter counter = new Counter(numbers,0,numbers.length,x-> x > 0.5);
        ForkJoinPool pool = new ForkJoinPool();
        //提交ForkJoinPool线程池
        //invoke方法会返回计算结果
        System.out.println(pool.invoke(counter));
        System.out.println("======================");
        //或者通过ForkJoinTask的join方法去获取结果
        System.out.println(counter.join());
    }
}
class Counter extends RecursiveTask<Integer>{
    public static final int THRESHOLD = 1000;//任务的最小单元
    private double[] values;
    private int from;
    private int to;
    private DoublePredicate filter; //double输入，boolean输出 函数

    public Counter(double[] values, int from, int to, DoublePredicate filter) {
        this.values = values;
        this.from = from;
        this.to = to;
        this.filter = filter;
    }

    @Override
    protected Integer compute() {
        //如果数组大小小于任务的最小单元则直接计算，返回计算结果
        if(to - from < THRESHOLD){
            int count = 0;
            for(int i = from ; i< to; i++){
                if(filter.test(values[i])) count++;
            }
            return count;
        }else{
            //否则采用二分法,分割任务
            int mid = (to + from)>>1;
            Counter first = new Counter(values,from,mid,filter);
            Counter second = new Counter(values,from,mid,filter);
            //批量提交任务到ForkJoin线程池,ExecutorService 线程池也有相同的方法但后者可以设置超时时间
            invokeAll(first,second);
            //RecursiveTask join方法获得计算结果，在结果出来前阻塞
            return first.join() + second.join();
        }
    }
}
