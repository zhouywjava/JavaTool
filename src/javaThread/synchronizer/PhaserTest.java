package javaThread.synchronizer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

/**
 * @Description: 循环障栅
 * CountDownLatch和CyclicBarrier都是只适用于固定数量的参与者.
 * 移相器适用于可变数目的屏障,在这个意义上,可以在任何时间注册新的参与者.
 * 并且在抵达屏障是可以注销已经注册的参与者.
 * 因此,注册到同步移相器的参与者的数目可能会随着时间的推移而变化.
 * @Author: zyw
 * @Date: 2018/3/9
 */
public class PhaserTest {
  private static final ExecutorService executorService = Executors.newCachedThreadPool();
  private static final Phaser phaser = new Phaser(5){
      //重写这个方法，每次到达的时候会自动调用
      @Override
      protected boolean onAdvance(int phase, int registeredParties) {
          System.out.println(Thread.currentThread().getName() + ":全部"+registeredParties+"个人都到齐了,现在是第"+(phase + 1)
                  +"次集合准备去下一个地方..................");
          return super.onAdvance(phase, registeredParties);
      }
  };//线程数量
    public static void main(String[] args) {
        Runnable r = ()->{
            //事件A
            System.out.println(Thread.currentThread().getName() + "完成事件A");
            //所有当前线程等待其他线程执行到相同位置后在继续执行 arrive相当于countDown  awaitAdvance 相当于 await
            phaser.arriveAndAwaitAdvance();
            //事件B
            System.out.println(Thread.currentThread().getName() + "完成事件B");
            phaser.arriveAndAwaitAdvance();
            //事件C
            System.out.println(Thread.currentThread().getName() + "完成事件C");
            phaser.arriveAndAwaitAdvance();
            //事件D
            System.out.println(Thread.currentThread().getName() + "完成事件D");
            phaser.arriveAndAwaitAdvance();
        };
        for(int i = 0;i<5;i++) {
            executorService.submit(r);
        }
    }

}
