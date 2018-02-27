package javaCore.collections;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * @Description: 标识散列映射
 * 1.key 通过 == 而不是通过 equal判断是同一个key
 * 2.value 不是用hashcode计算散列，而是通过System.identityHashCode来计算
 * @Author: zyw
 * @Date: 2018/2/24
 */
public class IdentityHashMapTest {

    public static void main(String[] args) {
        IdentityHashMap identityHashMap = new IdentityHashMap();
        identityHashMap.put("java","a");
        identityHashMap.put("java","b");
        identityHashMap.put(new String("test"),"c");
        identityHashMap.put(new String("test"),"d");
        identityHashMap.forEach((k,v)-> System.out.println("key:" + k + " ,value:" + v));
        System.out.println("======================================");
        Map map = new HashMap();
        map.put("java","a");
        map.put("java","b");
        map.put(new String("test"),"c");
        map.put(new String("test"),"d");
        map.forEach((k,v)-> System.out.println("key:" + k + " ,value:" + v));



    }
}
