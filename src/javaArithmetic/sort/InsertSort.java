package javaArithmetic.sort;

import javaArithmetic.sort.base.BaseSort;

/**
 * 插入排序
 * Created by zhouyiwei on 2018/2/23.
 */
public class InsertSort implements BaseSort {
    @Override
    public void sort(Comparable[] a) {
        if(a != null && a.length>0){
            for(int i = 1;i<a.length;i++){
                for(int j = i; j>0 && BaseSort.less(a[j],a[j-1]);j--){
                    BaseSort.exch(a,j,j-1);
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = {2,4,1,5,6,7,8,0,3,9};
        InsertSort insertSort = new InsertSort();
        insertSort.sort(a);
        BaseSort.show(a);
    }
}
