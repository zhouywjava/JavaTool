package javaArithmetic.sort;

import javaArithmetic.sort.base.BaseSort;
import javaArithmetic.util.Stopwatch;

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
       if(alg.equals("Insertion")) sort = new InsertSort();
       if(alg.equals("Selection")) sort = new Selection();
       if(alg.equals("shell")) sort = new Shell();
       sort.sort(a);
      return stopwatch.elapsedTime();
   }

    /**
      * 描述: 模拟随机输入<br>
      * 作者: zyw<br>
      * @param  alg 算法关键字
      * @param N 创建N个数组
      * @param T 每个数组T个元素
      * @result
      */
   public static double timeRandomInput(String alg,int N,int T){
       double total = 0.0d;

        Integer i = new Integer(N);
       return total;
   }

}
