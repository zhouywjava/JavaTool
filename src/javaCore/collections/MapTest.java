package javaCore.collections;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description: 映射新方法
 * @Author: zyw
 * @Date: 2018/2/24
 */
public class MapTest {
    public static void main(String[] args) {
        Map<Integer,String> map = new HashMap<>();
        map.put(1,"abcd");
        map.put(2,"efgh");
        map.put(3,"ijkl");
        map.put(4,"mnop");
        // forEach BiConsumer param T U return void
        map.forEach((k,v)-> System.out.println("key: " + k + " ,value: " + v));
        System.out.println("===============================");
        // getOrDefault
        System.out.println(map.getOrDefault(1,"default"));
        System.out.println(map.getOrDefault(7,"default"));
        System.out.println("===============================");
        // putIfAbsent
        map.putIfAbsent(4,"qrst");
        map.putIfAbsent(5,"qrst");
        map.forEach((k,v)-> System.out.println("key: " + k + " ,value: " + v));
        System.out.println("===============================");
        // remove
        boolean res = map.remove(4,"jiid");
        System.out.println(res);
        map.forEach((k,v)-> System.out.println("key: " + k + " ,value: " + v));
        System.out.println("===============================");
        // replaceAll BiFunction param T U return R
        map.replaceAll((k,v)->{
            if(k == 1){
                return v.toUpperCase();
            }
            return v;
        });
        map.forEach((k,v)-> System.out.println("key: " + k + " ,value: " + v));
        System.out.println("===============================");
        // merge BiFunction param T U return R
        /*如果Map中key对应的映射不存在或者为null，则将value（不能是null）关联到key上；
        否则执行remappingFunction，如果执行结果非null则用该结果跟key关联，否则在Map中删除key的映射．*/
        map.merge(6,"uvwx",(v1,v2)-> v1 + v2);
        map.forEach((k,v)-> System.out.println("key: " + k + " ,value: " + v));
        System.out.println("-------------------");
        map.merge(6,"uvwx",(v1,v2)->v1 + v2);
        map.forEach((k,v)-> System.out.println("key: " + k + " ,value: " + v));
        System.out.println("===============================");
        // compute BiFunction param k,v return v 是把remappingFunction的计算结果关联到key上，如果计算结果为null，则在Map中删除key的映射．
        map.compute(1,(k,v)-> k + v);
        map.compute(2,(k,v)-> null);
        map.forEach((k,v)-> System.out.println("key: " + k + " ,value: " + v));
        System.out.println("===============================");
        // computeIfAbsent 只有key 不存在，或者映射值为null时才计算，计算结果非null时与key 关联 Function param T return R
        map.computeIfAbsent(1,(v)->"abcd");
        map.computeIfAbsent(2,(v)->"efgh");
        map.forEach((k,v)-> System.out.println("key: " + k + " ,value: " + v));
        System.out.println("===============================");
        // computeIfPresent 与 computeIfAbsent 相反 只有key 存在，且映射值不为null 时才计算，如果计算结果为null则删除key，否则替换key的映射值
        map.computeIfPresent(1,(k,v)->null);
        map.computeIfPresent(2,(k,v)->v.toUpperCase());
        map.computeIfPresent(7,(k,v)->"zzzz");
        map.forEach((k,v)-> System.out.println("key: " + k + " ,value: " + v));
        System.out.println("===============================");
    }
}
