package javaCore.collections;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Description: LinkHashMap
 * 1.同时具备HashMap 和 LinkedList的结构。在计算hashCode的基础上加上了前后指针
 * 2.将对象放入到LinkedHashMap或LinkedHashSet中时，有两个方法需要特别关心：hashCode()和equals()。
 * hashCode()方法决定了对象会被放到哪个bucket里，当多个对象的哈希值冲突时，equals()方法决定了这些对象是否是“同一个对象”。
 * 所以，如果要将自定义的对象放入到LinkedHashMap或LinkedHashSet中，需要*@Override*hashCode()和equals()方法。
 * 如果equal判断相等，则直接返回，如果不等，则进行插入。一方面插入到table对应的bucket中链表头部，一方面插入到双向链表的尾部
 * @Author: zyw
 * @Date: 2018/2/24
 */
public class LinkedHashMapTest {


    public static void main(String[] args) {
        LinkedHashMap<Integer,String> linkedHashMap = new LinkedHashMap(){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > 10;
            }
        };
        linkedHashMap.put(1,"a");
        linkedHashMap.put(2,"b");
        linkedHashMap.put(3,"c");
        linkedHashMap.put(4,"d");
        linkedHashMap.put(5,"e");
        linkedHashMap.put(6,"f");
        linkedHashMap.put(7,"g");
        linkedHashMap.put(8,"h");
        linkedHashMap.put(9,"i");
        linkedHashMap.put(10,"j");
        linkedHashMap.put(11,"k");
        linkedHashMap.put(12,"l");
        linkedHashMap.forEach((k,v)-> System.out.println("key:" + k + " ,value:" + v));
    }
}
class LinkedHashSetTest{

}