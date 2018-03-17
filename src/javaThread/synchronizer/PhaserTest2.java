package javaThread.synchronizer;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Description: 在旅游过程中,有可能很凑巧遇到几个朋友,
 * 然后他们听说你们在旅游,所以想要加入一起继续接下来的旅游.(直接加入当前相位器管辖的任务中)
 * 也有可能,在旅游过程中,突然其中有某几个人临时有事,想退出这次旅游了.
 * 在自由行的旅游,这是很常见的一些事情.
 * 如果现在我们使用CyclicBarrier这个类来实现,我们发现是实现不了,这是用Phaser就可实现这个功能.
 * @Author: zyw
 * @Date: 2018/3/17
 */
public class PhaserTest2 {
    private static final String names = "明刚红";
    private static final ExecutorService exeCutor = Executors.newCachedThreadPool();
    private static final Random random = new Random(47);
    private static final AtomicInteger frientCount = new AtomicInteger(1);
    private static final Phaser phaser = new Phaser(names.length()){
        @Override
        protected boolean onAdvance(int phase, int registeredParties) {
            System.out.println(Thread.currentThread().getName() + ":全部"+registeredParties+"个人都到齐了,现在是第"+(phase + 1)
                    +"次集合准备去下一个地方..................");
            return super.onAdvance(phase, registeredParties);
        }
    };

    /**
     * 准备返程
     * @return 返回true,说明还要继续旅游,否则就临时退出了
     */
    private boolean goToEndPoint() {
        return goToPoint("飞机场,准备登机回家");
    }

    /**
     * 到达旅游点3
     * @return 返回true,说明还要继续旅游,否则就临时退出了
     */
    private boolean goToTourismPoint3() {
        return goToPoint("旅游点3");
    }

    /**
     * 到达旅游点2
     * @return 返回true,说明还要继续旅游,否则就临时退出了
     */
    private boolean goToTourismPoint2() {
        return goToPoint("旅游点2");
    }

    /**
     * 到达旅游点1
     * @return 返回true,说明还要继续旅游,否则就临时退出了
     */
    private boolean goToTourismPoint1() {
        return goToPoint("旅游点1");
    }

    /**
     * 入住酒店
     * @return 返回true,说明还要继续旅游,否则就临时退出了
     */
    private boolean goToHotel() {
        return goToPoint("酒店");
    }

    /**
     * 出发点集合
     * @return 返回true,说明还要继续旅游,否则就临时退出了
     */
    private boolean goToStartingPoint() {
        return goToPoint("出发点");
    }

    private int getRandomTime() {
        int time = random.nextInt(400) + 100;
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return time;
    }

    /**
     * @param point 集合点
     * @return 返回true,说明还要继续旅游,否则就临时退出了
     */
    private boolean goToPoint(String point){
       return randomEvent(point);
    }

    /**
     * 随机事件
     * @return 返回true,说明还要继续旅游,否则就临时退出了
     */
    private boolean randomEvent(String point) {
        int r = random.nextInt(100);
        String name = Thread.currentThread().getName();
        System.out.println(name + " 花了 " + getRandomTime() + " 时间才到了" + point);
        if (r < 5){
            int friendNum = 1;
            System.out.println(name + ":在这里竟然遇到了"+friendNum+"个朋友,他们说要一起去旅游...");
            phaser.bulkRegister(friendNum);//注册当前相位器，新的线程会直接运行当前相位器所处阶段的任务
            for (int i = 0; i < friendNum; i ++){
                exeCutor.submit(()->{
                    Thread.currentThread().setName(name + "的朋友" + frientCount.getAndAdd(1) + "号");
                    travel();
                });
            }
        }else if(r > 95){
            System.out.println(name + ":突然有事要离开一下,不和他们继续旅游了....");
            phaser.arriveAndDeregister();
            return false;
        }
        phaser.arriveAndAwaitAdvance();
        return true;
    }

    private void travel(){
        switch(phaser.getPhase()){
            case 0:if(!goToStartingPoint()) break;
            case 1:if(!goToHotel()) break;
            case 2:if(!goToTourismPoint1()) break;
            case 3:if(!goToTourismPoint2()) break;
            case 4:if(!goToTourismPoint3()) break;
            case 5:if(!goToEndPoint())break;
            default:break;
        }
    }

    public static void main(String[] args) {
        PhaserTest2 test2 = new PhaserTest2();
        for(int i = 0; i< names.length();i++){
            String name = "小" + names.toCharArray()[i];
            exeCutor.submit(()->{
                Thread.currentThread().setName(name);
                test2.travel();
            });
        }
    }

}
