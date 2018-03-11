package javaArithmetic.sort;

import javaArithmetic.sort.base.BaseSort;

/**
 * @Description: 自底向上归并 要(1/2)NlgN~NlgN次比较。6NlgN次访问数组
 * @Author: zyw
 * @Date: 2018/3/11
 */
public class MergeUp {

    //辅助排序数组
    private static Comparable[] aux;

    public static void sort(Comparable[] a){
        aux = new Comparable[a.length];
        int N = a.length;
        //子数组中的元素数量sz
        for(int sz = 1;sz< N ; sz = sz+sz){
            for(int i = 0;i+sz<N;i += (sz + sz)){
                //i+sz<N
                merge(a,i,i + sz -1,Math.min(i+(2*sz-1),N-1));
            }
        }
    }
    private static void merge(Comparable[] a,int lo,int mid,int hi){
        //分为两组
        int i = lo, j = mid + 1;
        //将需要进行归并的项进行拷贝
        for(int k = lo;k<=hi;k++){
            aux[k] = a[k];
        }
        for(int k = lo;k<=hi;k++){
            if(i>mid) a[k] = aux[j++];
            else if(j>hi) a[k] = aux[i++];
            else if(BaseSort.less(a[j],a[i])) a[k] = aux[j++];
            else a[k] = aux[i++];
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{0,2,3,7,6,5,4,1};
        MergeUp.sort(a);
        for(int i : a){
            System.out.print(i+",");
        }
    }
}
