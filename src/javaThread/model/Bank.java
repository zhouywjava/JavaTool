package javaThread.model;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Description: 银行类
 * @Author: zyw
 * @Date: 2018/3/1
 */
public class Bank {
    private final double[] accounts;
    private Lock bankLock;
    private Condition sufficientFunds;

    public Bank(int n,double initialBalance) {
        this.accounts = new double[n];
        Arrays.fill(accounts,initialBalance);
        bankLock = new ReentrantLock();
        this.sufficientFunds = bankLock.newCondition();
    }

    public void transfer(int from,int to,double amount) throws InterruptedException{
        bankLock.lock();
        try{
            //循环检查条件，因为等待状态激活后还要在检查一次，确认无误
            //条件对象，用来管理当线程获得锁后，如果没有满足执行条件的时候的锁占用问题。
            while(accounts[from]<amount){
                sufficientFunds.await();
            }
            System.out.println(Thread.currentThread());
            accounts[from] -= amount;
            System.out.printf("%10.2f from %d to %d",amount,from,to);
            accounts[to] += amount;
            System.out.printf(" Total Balance: %10.2f%n",getTotalBalance());
            sufficientFunds.signalAll();
        }finally {
            bankLock.unlock();
        }
    }

    public double getTotalBalance(){
        bankLock.lock();
        try{
            double sum = 0;
            for(double b : accounts){
                sum += b;
            }
            return sum;
        }finally{
            bankLock.unlock();
        }
    }

    public int size(){
        return accounts.length;
    }


}
