package javaCore.collections;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Description: Iterator 删除集合元素
 * @Author: zyw
 * @Date: 2018/3/1
 */
public class IteratorTest {

    public static void main(String[] args) {
        List<String> strs = new ArrayList<>();
        strs.add("a");
        strs.add("b");
        strs.add("c");
        strs.add("d");
        Iterator<String> iterator = strs.iterator();
        while(iterator.hasNext()){
            String temp = iterator.next();
            System.out.println(temp);
            if(temp.equals("a")){
                iterator.remove();
            }
        }
        strs.forEach(System.out::println);
    }
}
