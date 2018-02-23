package javaArithmetic.util;

/**
 * @Description: 计时器
 * @Author: zyw
 * @Date: 2018/2/23
 */
public class Stopwatch {

    private final long start;

    public Stopwatch() {
        this.start = System.currentTimeMillis();
    }

    public double elapsedTime(){
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }
}
