package javaArithmetic.sort;

import javaArithmetic.sort.base.BaseSort;

/**
 * @Description: 自顶向下归并算法: 对于长度是N的数组，要(1/2)NlgN~NlgN次比较。6NlgN次访问数组
 * @Author: zyw
 * @Date: 2018/3/10
 */
public class Merge implements BaseSort{

    @Override
    public void sort(Comparable[] a) {
        sort(a,0,a.length-1);
    }

    private void sort(Comparable[] a,int lo ,int hi){
        //递归的返回条件
        if(lo >= hi) return;
        int mid = lo + ((hi - lo) >> 1);
        //左边子递归
        sort(a,lo,mid);
        //右边子递归
        sort(a,mid+1,hi);
        //假设左右子递归完成了，则归并当前的数据
        BaseSort.merge(a,lo,mid,hi);
    }

    public static void main(String[] args) {
        Integer[] a = new Integer[]{0,9,2,3,7,8,6,5,4,1};
        Merge merge = new Merge();
        merge.sort(a);
        for(int i : a){
            System.out.print(i+",");
        }
    }
}
