package javaArithmetic.sort;

import javaArithmetic.sort.base.BaseSort;

/**
 * @Description: 选择排序
 * @Author: zyw
 * @Date: 2018/2/23
 */
public class Selection implements BaseSort {
    @Override
    public void sort(Comparable[] a) {
        if(a != null && a.length > 0) {
            //假设第一个最小
            int minIndex = 0;
            for(int i = 0;i<a.length;i++) {
                for (int j = i+1; j < a.length; j++) {
                    if (BaseSort.less(a[j], a[minIndex])) {
                        BaseSort.exch(a, j, minIndex);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = {2,4,1,5,6,7,8,2,0,3,9};
        Selection selection = new Selection();
        selection.sort(a);
        BaseSort.show(a);
    }
}
