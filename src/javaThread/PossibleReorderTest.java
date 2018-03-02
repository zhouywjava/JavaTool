package javaThread;

/**
 * @Description: 多线程下指令重排问题
 * @Author: zyw
 * @Date: 2018/3/2
 */
public class PossibleReorderTest {

    static int x = 0,y = 0;
    static int a = 0,b = 0;

    public static void main(String[] args) throws InterruptedException {
        Runnable r1 = () -> {
            a = 1;
            x = b;
        };
        Runnable r2 = () -> {
            b = 1;
            y = a;
        };
        int count = 1;
        do {
            //重置
            x = y = 0;
            a = b = 0;
            Thread t1 = new Thread(r1);
            Thread t2 = new Thread(r2);
            t1.start();
            t2.start();
            t1.join();
            t2.join();
            System.out.println("第" + count + "次执行结果: " + "(" + x + "," + y + ")");
            count++;
        }while(x!=1 || y!=1);
    }



}
