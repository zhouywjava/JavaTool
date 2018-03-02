package javaThread;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Description:LongAccumulator类测试
 * @Author: zyw
 * @Date: 2018/3/2
 */
public class LongAccumulatorTest {

    public static void main(String[] args) {
        //与LongAdder相似，能指定初始值identity
        //(x,y)->x+y 中x 就是初始值1，y 就是accumulate的入参
        LongAccumulator adder = new LongAccumulator((x,y)->x+y,0);
        Runnable r = ()->{
            //并发有指令重排问题
            adder.accumulate(1);
            System.out.println(adder.get());
        };
        for(int i = 0 ;i<100;i++){
            Thread t = new Thread(r);
            t.start();
        }
    }
}
/**
 * @Description: 更快的原子类
 *               大家对AtomicInteger的基本实现机制应该比较了解,它们是在一个死循环内,不断尝试修改目标值,直到修改成功;
 *               (正式开始前，强调下，我们知道，AtomicLong的实现方式是内部有个value 变量，当多线程并发自增，自减时，均通过CAS(compareAndSwapLong,比较交换法，我认为值是a如果是a则把a改为b) 指令从机器指令级别操作保证并发的原子性)
 *               如果竞争不激烈,那么修改成功的概率就很高,否则,修改失败的概率就很高,在大量修改失败时,这些原子操作就会进行多次循环尝试,因此性能就会受到影响
 * @Author: zyw
 * @Date: 2018/3/2
 */
class LongAdderTest {

    public static void main(String[] args) {
        LongAdder adder = new LongAdder();
        Runnable r = ()->{
            //并发有指令重排问题
            adder.add(1);
            System.out.println(adder);
        };
        for(int i = 0 ;i<100;i++){
            Thread t = new Thread(r);
            t.start();
        }
    }
}
