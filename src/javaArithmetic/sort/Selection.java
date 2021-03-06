package javaArithmetic.sort;

import javaArithmetic.sort.base.BaseSort;

/**
 * @Description: 选择排序需要N^2 /2 次比较，N次交换
 * @Author: zyw
 * @Date: 2018/2/23
 */
public class Selection implements BaseSort {
    @Override
    public void sort(Comparable[] a) {
        if(a != null && a.length > 0) {
            int minIndex = 0;
            for(int i = 0;i<a.length;i++) {
                //总是假设第一个元素最小
                minIndex = i;
                for (int j = i+1; j < a.length; j++) {
                    if(BaseSort.less(a[j],a[minIndex])){
                        minIndex = j;
                    }
                }
                BaseSort.exch(a,i,minIndex);
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = {2,4,1,5,6,7,8,0,3,9};
        Selection selection = new Selection();
        selection.sort(a);
        BaseSort.show(a);
    }
}
