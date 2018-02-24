package javaCore.collections;

import java.util.WeakHashMap;

/**
 * @Description: 弱引用散列映射,对于没有强引用的key会被系统回收，前两个key是系统缓存的字符串对象，不会被回收
 * @Author: zyw
 * @Date: 2018/2/24
 */
public class WeakHashMapTest {

    public static void main(String[] args) {
        WeakHashMap weakHashMap = new WeakHashMap();
        weakHashMap.put("1","abcd");
        weakHashMap.put("2","efgh");
        weakHashMap.put(new String("3"),"ijkl");
        weakHashMap.put(new String("4"),"mnop");
        System.gc();
        weakHashMap.forEach((k,v)-> System.out.println("key: " + k + ", value: "+ v));
    }
}
