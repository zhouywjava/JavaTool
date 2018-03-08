package javaThread.model;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 延迟数据
 * @Author: zyw
 * @Date: 2018/3/8
 */
public class DelayedEle implements Delayed{

    private final long delayTime;//延时时间
    private final long expire;//到期时间
    private String data;//数据

    public DelayedEle(long delayTime,  String data) {
        this.delayTime = delayTime;
        this.data = data;
        expire = System.currentTimeMillis() + delayTime;
    }

    //剩余时间 = 到期时间-当前时间
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expire-System.currentTimeMillis(),TimeUnit.MILLISECONDS);
    }

    //优先队列里的优先级别:延时时间越长，排在队头
    @Override
    public int compareTo(Delayed o) {
        return (int)(getDelay(TimeUnit.MILLISECONDS)-o.getDelay(TimeUnit.MILLISECONDS));
    }

    @Override
    public String toString() {
        return "DelayedEle{" +
                "delayTime=" + delayTime +
                ", expire=" + expire +
                ", data='" + data + '\'' +
                '}';
    }
}
