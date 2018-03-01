package javaThread.model;

import java.util.Arrays;

/**
 * @Description: Synchronized 版本 使用类的内部锁，简化代码，降低出错率，但是粒度比ReenTranLock大
 * @Author: zyw
 * @Date: 2018/3/1
 */
public class BankSy {
    private final double[] accounts;

    public BankSy(int n,double initialBalance) {
        this.accounts = new double[n];
        Arrays.fill(accounts,initialBalance);
    }

    public synchronized void transfer(int from,int to,double amount) throws InterruptedException{
        //循环检查条件，因为等待状态激活后还要在检查一次，确认无误
        //条件对象，用来管理当线程获得锁后，如果没有满足执行条件的时候的锁占用问题。
        while(accounts[from]<amount){
           wait();
        }
        System.out.println(Thread.currentThread());
        accounts[from] -= amount;
        System.out.printf("%10.2f from %d to %d",amount,from,to);
        accounts[to] += amount;
        System.out.printf(" Total Balance: %10.2f%n",getTotalBalance());
        notifyAll();
    }

    public synchronized double getTotalBalance(){
        double sum = 0;
        for(double b : accounts){
            sum += b;
        }
        return sum;
    }

    public int size(){
        return accounts.length;
    }
}
