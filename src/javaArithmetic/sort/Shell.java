package javaArithmetic.sort;

import javaArithmetic.sort.base.BaseSort;

/**
 * 希尔排序
 * 1.是插入排序的改进。我们都知道插入排序最好的情况是O(n),就是只需要n-1次比较，不需要交换元素。最坏的情况是逆序。这时交换的次数是1 + ... + n-1 = n(n-1)/2,比较的次数也是n(n-1)/2，因为一次比较一次交换。所以效率是n(n-1)时间复杂度O(n^2)
 * 2.插入排序一般情况是。假设长度为n,倒序为m 的交换m次，比较m次，同一次比较中的倒序有可能会多引起一次比较（只比较不交换）大概是1~n-2次，正序的次数n-m ,总的就是 n-m + m + m + (1~n-2) = n+m+1 ~ n+m+n-2  其中 设一般情况m = n/2 所以就是
 *  3*n/2 ~ 5*n /2
 * 3.希尔排序解决的问题就是，如果逆序越少，则效率越高。所以采取的优化策略是先大跨步的进行粗略排序，后减少步长，当步长减少到1后就是一般的插入排序，这时序列已经相对有序了。
 * Created by zhouyiwei on 2018/2/23.
 */
public class Shell implements BaseSort {
    @Override
    public void sort(Comparable[] a) {

    }
}
