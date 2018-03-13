package javaArithmetic.sort.base;

/**
 * @Description: 排序模板 排序成本模型:计算比较和交换的次数。如果算法不交换元素，计算访问数组的次数
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
        * 描述: 比较大小<br>
        * 作者: zyw<br>
        * @param  a 可排序元素
        * @param  b 可排序元素
        * @result 是否a比b小
        */
     static boolean less(Comparable a,Comparable b){
        return a.compareTo(b) < 0;
     }

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

      /**
        * 描述: 检查是否有序<br>
        * 作者: zyw<br>
        * @param  a 可排序元素集合
        * @result 是否a按照由小到大排序
        */
     static boolean isSorted(Comparable[] a){
         for(int i = 1; i< a.length; i++){
            if(less(a[i],a[i-1])){
                return false;
            }
         }
         return true;
     }

      /**
        * 描述: 归并排序<br>
        * 作者: zyw<br>
        * @param  a 待排序数组
        * @param  lo 归并数组开始位置
        * @param  mid 归并数组中间位置
        * @param  hi 归并数组结束位置
        * @result
        */
     static void merge(Comparable[] a,int lo,int mid,int hi){
         //创建两个指针，分别代表待归并数组分为两个数组
         int i = lo , j = mid + 1;
         //辅助集合
         Comparable[] aux = new Comparable[a.length];
         //只拷贝需要归并的元素，即lo~hi之间的元素
         for(int k = lo;k<=hi;k++){
             aux[k] = a[k];
         }
         //开始归并
         for(int k = lo;k<=hi;k++){
             if(i > mid) a[k] = aux[j++];
             else if(j>hi) a[k] = aux[i++];
             else if(less(aux[j],aux[i]))a[k] = aux[j++];
             else a[k] = aux[i++];
         }
     }

    public static void main(String[] args) {
        System.out.println(less(0,1));
    }


}
