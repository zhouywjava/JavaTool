package javaThread.Queue;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Description: 用多线程散列映射表
 * @Author: zyw
 * @Date: 2018/3/3
 */
public class ConcurrentHashMapTest {

    public static void main(String[] args) {
        ConcurrentHashMap<String,Long> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("a",0L);
        concurrentHashMap.compute("b",(k,v)-> {
            if("b".equals(k)){return 999L;}else{
            return v ==null?1:v+2;
        }});
        concurrentHashMap.computeIfPresent("a",(k,v)->v == null?1:v+3);
        concurrentHashMap.computeIfAbsent("c",k->{
            System.out.println(k);
            System.out.println("=======================");
            return 1L;
        });
        concurrentHashMap.merge("c",10L,(exist,newOne)->exist+newOne);
        System.out.println(concurrentHashMap.get("a"));
        System.out.println(concurrentHashMap.get("b"));
        System.out.println(concurrentHashMap.get("c"));
        System.out.println("==============================");
        //threshold：如果查找的元素多余阈值，则会并发的完成。比如如果匹配的元素有3个，那么设置为1则会批量的完成查找
        String targetKey = concurrentHashMap.search(1,(k,v)->v>1?k:null);
        System.out.println(targetKey);//返回第一个匹配的key
        System.out.println("===========================");
        concurrentHashMap.forEach(1,(k,v)-> System.out.println(k + "->" + v));
        System.out.println("----------------------------------");
        //使用转换器辅助输出,转换器可以当作过滤器使用，当过滤器返回null的时候不执行后续函数
        concurrentHashMap.forEach(1,(k,v)->v>100?k+"->"+v:null,System.out::println);
        //计算总和
        long sum = concurrentHashMap.reduceValues(1,Long::sum);
        System.out.println("计算总和:" + sum);
        //键长度最长,转换为长度，然后用max()函数比较，即返回最大长度
        int maxlength = concurrentHashMap.reduceKeys(1,String::length,Integer::max);
        System.out.println("最大键长:" + maxlength);
        //设置计算基数base，建立在这个基础上进计算
        long result = concurrentHashMap.reduceValuesToLong(1,Long::longValue,0,Long::sum);
        System.out.println("将结果转为long型:" + result);
    }
}
