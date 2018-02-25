package javaCore.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * @Description: 排序测试
 * @Author: zyw
 * @Date: 2018/2/25
 */
public class SortTest {

    public static void main(String[] args) {
        List<String> staff = new LinkedList<>();
        staff.add("c");
        staff.add("a");
        staff.add("b");
        //Collections.sort(staff);
        //staff.forEach((v)-> System.out.println(v));
        staff.forEach(System.out::println);
        //staff.sort(Comparator.comparing(String::toString).reversed());
        staff.sort(Comparator.comparing(String::toString));
        staff.forEach((v)-> System.out.println(v));
        System.out.println("=====================================");
        List<String> lists = new ArrayList<>();
        lists.add("b");
        lists.add("d");
        lists.add("a");
        lists.add("c");
        System.out.println(Collections.binarySearch(lists,"a"));
        System.out.println(Collections.binarySearch(lists,"a",Comparator.comparing(String::toString)));
        lists.sort(Comparator.comparing(String::toString));
        System.out.println(Collections.binarySearch(lists,"a"));
        System.out.println(Collections.binarySearch(lists,"a",Comparator.comparing(String::toString)));
        int i = Collections.binarySearch(lists,"e");
        if(i < 0){
            //如果元素不存在，插入正确的位置
            lists.add(-i-1,"e");
        }
        lists.forEach(System.out::print);
    }
}
