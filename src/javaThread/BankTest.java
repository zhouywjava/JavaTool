package javaThread;

import javaThread.model.Bank;
import javaThread.model.BankSy;

/**
 * @Description: 测试类
 * @Author: zyw
 * @Date: 2018/3/1
 */
public class BankTest {

    public static final int NACCOUNTS = 100;//账户数
    public static final double INITIAL_BALANCE = 1000;//每个账户的初始金额
    public static final double MAX_AMOUNT = 1000;//最大金额
    public static final int DELAY = 10;//延时因子

    public static void main(String[] args) {
        BankSy bank = new BankSy(NACCOUNTS,INITIAL_BALANCE);
        for(int i =0; i<NACCOUNTS;i++){
            int fromAcount = i;
            Runnable r = () ->{
                try{
                    for(int j = 0;j < 100;j++){
                        int toAccount = (int) (bank.size()*Math.random());
                        double amount = MAX_AMOUNT * Math.random();
                        bank.transfer(fromAcount,toAccount,amount);
                        Thread.sleep((int)(DELAY * Math.random()));
                    }

                }catch (InterruptedException e){

                }
            };
            Thread t = new Thread(r);
            t.start();
        }
    }
}
