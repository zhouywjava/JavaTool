package javaThread;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description: 使用lamda表达式更新变量，不用使用循环(如果打开方法，可以看到实现其实就是compareAndSet + 循环)
 * @Author: zyw
 * @Date: 2018/3/1
 */
public class AtomicUpdateAndGetTest {

    public static void main(String[] args) {
        AtomicLong atomicLong = new AtomicLong();
        Runnable r = ()->{
            //注意不能写成x++，那样会先返回在自增就一直是0了
            //LongUnaryOperator 接受一个参数同为类型long,返回值类型也为long。
            System.out.println(atomicLong.updateAndGet(x->++x));
        };
        for(int i = 0;i<100;i++){
            Thread t = new Thread(r);
            t.start();
        }
    }
}
