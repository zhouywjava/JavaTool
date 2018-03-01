package javaThread;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Description: 比较更新的方式比用锁,原子类快,但也是要不断尝试更新。
 * @Author: zyw
 * @Date: 2018/3/1
 */
public class AtomicSecondTest {

    public static void main(String[] args) {
        AtomicLong atomicLong = new AtomicLong();
        Runnable r = () ->{
           while(true){
               long current = atomicLong.get();
               long newOne = current + 1;
               boolean res = atomicLong.compareAndSet(current,newOne);
               if(res){
                   System.out.println(newOne);
                   return;
               }
           }
        };
        for(int i = 0;i<100;i++){
            Thread t = new Thread(r);
            t.start();
        }

    }
}
