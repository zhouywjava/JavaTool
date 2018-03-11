package javaArithmetic.sort;

import javaArithmetic.sort.base.BaseSort;

/**
 * @Description: 自顶向下归并算法: 对于长度是N的数组，要(1/2)NlgN~NlgN次比较。6NlgN次访问数组
 * @Author: zyw
 * @Date: 2018/3/10
 */
public class Merge {

    private static Comparable[] aux;

    public static void sort(Comparable[] a){
        aux = new Comparable[a.length];
        sort(a,0,a.length-1);
    }

    private static void sort(Comparable[] a,int lo,int hi){
        if(lo >= hi) return;
        int mid = lo + ((hi - lo)>>1);
        //递归调用
        sort(a,lo,mid);
        sort(a,mid+1,hi);
        merge(a,lo,mid,hi);
    }

     /**
       * 描述: 归并算法<br>
       * 作者: zyw<br>
       * @param  a 待归并数组
       * @param lo 开始归并位置
       * @param mid 归并中间位置
       * @param hi 归并结尾位置
       * @result
       */
    private static void merge(Comparable[] a,int lo,int mid,int hi){
        //lo~mid mid+1 ~ hi分为两个待归并数组
        int i = lo,j = mid+1;
        //复制一个辅助数组
        for(int k = 0;k<=hi;k++){
            aux[k] = a[k];
        }
        //开始比较
        //因为上面复制辅助数组的时候会把之前排序好的元素带出来，所以下面在比较赋值的时候就不从0开始了，而是从lo开始
        for (int k = lo; k <= hi; k++) {
            //如果左边 lo ~ mid已经都比较完了，那么直接取右边了
            if (i > mid) {
                a[k] = aux[j++];
            }
            //如果右边 mid+1 ~hi 已经都比较完了，那么直接取左边了
            else if (j > hi) {
                a[k] = aux[i++];
            }
            //如果a[j] < a[i] 那么取a[j]
            else if (BaseSort.less(aux[j], aux[i])) {
                a[k] = aux[j++];
            }
            //否则 取a[i]
            else {
                a[k] = aux[i++];
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{0,9,2,3,7,8,6,5,4,1};
        Merge.sort(a);
        for(int i : a){
            System.out.print(i+",");
        }
    }
}
