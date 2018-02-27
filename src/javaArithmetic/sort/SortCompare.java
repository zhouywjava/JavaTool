package javaArithmetic.sort;

import com.sun.javafx.binding.StringFormatter;
import javaArithmetic.sort.base.BaseSort;
import javaArithmetic.util.StdRandom;
import javaArithmetic.util.Stopwatch;

import java.text.Format;
import java.util.Random;

/**
 * 比较排序效率的方法
 * Created by zhouyiwei on 2018/2/23.
 */
public class SortCompare {

    /**
      * 描述: 根据算法获得算法执行时间<br>
      * 作者: zyw<br>
      * @param  alg 算法关键字
      * @param a 可排序集合
      * @result 排序时间
      */
   public static double time(String alg,Comparable[] a){

       Stopwatch stopwatch = new Stopwatch();
       BaseSort sort = null;
       if(alg.equals("insertsort")) sort = new InsertSort();
       if(alg.equals("selection")) sort = new Selection();
       if(alg.equals("shell")) sort = new Shell();
       sort.sort(a);
      return stopwatch.elapsedTime();
   }

    /**
      * 描述: 模拟随机输入<br>
      * 作者: zyw<br>
      * @param  alg 算法关键字
      * @param N 创建长度为N的数组
      * @param T 重复测试T次
      * @result
      */
   public static double timeRandomInput(String alg,int N,int T){
       double totalTime = 0d;
       Double[] arr = new Double[N];
        for(int i = 0 ; i<T;i++){
            for(int j = 0 ;j < N;j++) {
                arr[j] = StdRandom.uniform();
            }
            totalTime += time(alg,arr);
        }
       return totalTime;
   }

    public static void main(String[] args) {
        String alg1 = args[0];
        String alg2 = args[1];
        String alg3 = args[2];
        int N = Integer.valueOf(args[3]);
        int T = Integer.valueOf(args[4]);
        double t1 = timeRandomInput(alg1,N,T);
        double t2 = timeRandomInput(alg2,N,T);
        double t3 = timeRandomInput(alg3,N,T);
        System.out.println(String.format(" For %d length integer random array,test %d times, %s is %n %.1f times faster than %s %n",N,T,alg2,t1/t2,alg1));
        System.out.println(String.format(" For %d length integer random array,test %d times, %s is %n %.1f times faster than %s %n",N,T,alg3,t2/t3,alg2));
        System.out.println(String.format(" For %d length integer random array,test %d times, %s is %n %.1f times faster than %s %n",N,T,alg3,t1/t3,alg1));
   }

}
