package javaCore.collections;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 列表新方法
 * @Author: zyw
 * @Date: 2018/2/24
 */
public class ArrayListTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        // forEach:
        list.forEach((obj) -> System.out.println("obj: " + obj));
        System.out.println("==========================");
        // removeIf: Predicate param T return boolean
        list.removeIf((obj) -> obj <= 2);
        list.forEach((obj) -> System.out.println("obj: " + obj));
        System.out.println("==========================");
        // replaceAll: UnarrayOperator param T return target
        list.replaceAll((obj)->{if(obj>3){return 0;}return obj;});
        list.forEach((obj) -> System.out.println("obj: " + obj));
        System.out.println("==========================");
        // sort: lamda 从小到大排序
        list.sort((o1,o2)->o1.compareTo(o2));
        list.forEach((obj) -> System.out.println("obj: " + obj));
        System.out.println("==========================");

    }
}
