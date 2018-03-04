package javaThread;

/**
 * @Description: Volatile 关键字 还是存在不一致问题
 * @Author: zyw
 * @Date: 2018/3/3
 */
public class VolatileTest {

    public static volatile int race = 0;
    public static void increase(){
        race++;
    }
    public static final int THREADS_COUNT = 20;//线程数

    public static void main(String[] args) {
        Runnable r = ()->{
            for(int i = 0;i<100;i++){
                increase();
            }
        };
        for(int j =0;j<THREADS_COUNT;j++){
            Thread t = new Thread(r);
            t.start();
        }

        while (Thread.activeCount()>1){
            //如果当前活动线程大于1，主线程让步。
            Thread.yield();
        }
        System.out.println(race);//预计是2000但是输出的是1992
    }

}
