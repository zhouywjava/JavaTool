package javaCore.collections;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description:
 * @Author: zyw
 * @Date: 2018/2/25
 */
public class LinkedListTest {

    public static void main(String[] args) {
        List<String> lists = new LinkedList<>();
        lists.add("a");
        lists.add("b");
        lists.add("c");
        lists.add("d");
        System.out.println(lists.get(1));
    }
}
