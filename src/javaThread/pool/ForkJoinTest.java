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
        final int SIZE = 1000000;
        double[] numbers = new double[SIZE];
        for(int i = 0; i<SIZE ; i++){
            numbers[i] = Math.random();
        }
        Counter counter = new Counter(numbers,0,numbers.length,x-> x > 0.5);
        ForkJoinPool pool = new ForkJoinPool();
        pool.invoke(counter);
        System.out.println(counter.join());
    }
}
class Counter extends RecursiveTask<Integer>{
    public static final int THRESHOLD = 1000;
    private double[] values;
    private int from;
    private int to;
    private DoublePredicate filter; //double输入，boolean输出

    public Counter(double[] values, int from, int to, DoublePredicate filter) {
        this.values = values;
        this.from = from;
        this.to = to;
        this.filter = filter;
    }

    @Override
    protected Integer compute() {

        if(to - from < THRESHOLD){
            int count = 0;
            for(int i = from ; i< to; i++){
                if(filter.test(values[i])) count++;
            }
            return count;
        }else{
            int mid = (to + from)/2;
            Counter first = new Counter(values,from,mid,filter);
            Counter second = new Counter(values,from,mid,filter);
            invokeAll(first,second);
            return first.join() + second.join();
        }
    }
}
