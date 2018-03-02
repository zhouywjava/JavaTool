package javaThread;

/**
 * @Description: 多线程共享参数本地化
 * @Author: zyw
 * @Date: 2018/3/2
 */
public class ThreadLocalTest {

    static ThreadLocal<Integer> x = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };
    static ThreadLocal<Integer> y = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };
    static ThreadLocal<Integer> a = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };
    static ThreadLocal<Integer> b = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public static void main(String[] args) throws InterruptedException {
        Runnable r1 = () -> {
            a.set(1);
            x.set(b.get());
        };
        Runnable r2 = () -> {
            b.set(1);
            y.set(a.get());
        };

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("(" + x.get() + "," + y.get() + ")");
    }

}
