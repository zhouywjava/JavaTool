package javaArithmetic.sort.base;

/**
 * @Description: 排序模板
 * @Author: zyw
 * @Date: 2018/2/23
 */
public interface BaseSort {
    
     /**
       * 描述: 排序方法<br>
       * 作者: zyw<br>
       * @param  a 可排序元素集合
       */
     void sort(Comparable[] a);

      /**
        * 描述: 交换集合元素<br>
        * 作者: zyw<br>
        * @param  a 可排序元素集合
        * @param i 待交换下标
        * @param j 待交换下标
        */
     static void exch(Comparable[] a,int i,int j){
        Comparable t = a[i];a[i] = a[j];a[j] = t;
     }

      /**
        * 描述: 打印集合中的元素<br>
        * 作者: zyw<br>
        * @param  a 可排序元素集合
        */
     static void show(Comparable[] a){
        for(Comparable obj :a){
            System.out.println(obj + " ");
        }
     }



}
