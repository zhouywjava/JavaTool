package javaArithmetic.sort;

import javaArithmetic.sort.base.BaseSort;

/**
 * @Description: 自底向上归并 要(1/2)NlgN~NlgN次比较。6NlgN次访问数组
 * 1.描述算法过程：因为顶层的归并要有作用必须是子序列有序。因此可以反过来，先完成底部的排序在完成顶部的排序.因为子序列必须至少有一个元素因此子序列的大小由1开始逐渐变大到N/2。
 * 考虑到奇数的情况，可能左子列不等于右子列，但是只要他们是有序的，merge就能正确排序。除了子序列的元素数量是关键的，还应该识别出总的参与归并排序的元素总数量也是关键。因为merge
 * 针对的是总序列进行归并排序的。因此由子序列元素数量，参与排序的总元素数量可以组成一个数据元(1,2),(2,4),(4,8)...
 * 2.写出静态方法调用：merge(a,0,0,1) merge(a,2,2,3)...
 * 3.画图出静态方法调用图(1,2){merge(a,0,0,1),merge(a,2,2,3)...},(2,4){merge(a,0,1,3),merge(a,4,5,7)...}..要确定出结束的地方(N/2,N){merge(a,0,N/2,N)}
 * 4.写出动态方法 应该是用循环。
 * @Author: zyw
 * @Date: 2018/3/11
 */
public class MergeUp implements BaseSort {

    public void sort(Comparable[] a){
       int N = a.length;
       for(int sz = 1;sz<=N;sz = sz + sz){
           for(int lo = 0;lo<N-1;lo = Math.min(lo + sz + sz,N-1)) {//lo每次增加2 sz 如果超过了数组元素最后一个下标N-1则设置为N-1
               int hi = Math.min(lo + sz + sz -1,N-1);//lo每次增加2 sz 如果超过了数组元素最后一个下标N-1则设置为N-1
               int mid = lo + sz - 1 ;//不能用lo + (hi - lo)/2 因为有可能存在 N/2<sz<N的情况，这个时候就是左边子序列大于右边子序列的情况.
               BaseSort.merge(a, lo, mid, hi);
           }
       }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{0,9,2,3,7,8,6,5,4,1};
        MergeUp mergeUp = new MergeUp();
        mergeUp.sort(a);
        for(int i : a){
            System.out.print(i+",");
        }
    }
}
