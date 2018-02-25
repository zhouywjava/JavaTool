package javaCore.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @Description: 视图与包装器
 * @Author: zyw
 * @Date: 2018/2/24
 */
public class ViewTest {

    public static void main(String[] args) {
        String[] strs = {"a","b","c"};
        //视图1
        List<String> list = Arrays.asList(strs);
        //这个看起来像ArrayList,但是它的长度是固定的,add会报错,remove也会报错
        //list.add("d");
        System.out.println(list.get(1));
        System.out.println("====================");
        List<String> lists = new ArrayList<>();
        lists.add("a");lists.add("b");lists.add("c");
        System.out.println(lists.get(1));
        System.out.println("====================");
        //视图2
        List<String> viewList = lists.subList(1,2);
        viewList.forEach((v)-> System.out.println(v) );
        System.out.println("====================");
        viewList.clear();//视图清掉，也会反应到原集合
        lists.forEach((v)-> System.out.println(v) );
        System.out.println("====================");
        //不可修改视图
        List unmodifierList = Collections.unmodifiableList(lists);
        try {
            //unmodifierList.add("1");
            //unmodifierList.remove("a");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("error:" + e);
        }
        lists.add("eeeeee");
        lists.forEach((v)-> System.out.println(v) );
        System.out.println("============================");

        List<String> strings = new ArrayList<>();
        List objs = strings;
        objs.add(new Date());
        System.out.println(objs.get(0));
        //System.out.println(strings.get(0)); //error

        List<String> saveString = Collections.checkedList(strings,String.class);
        List objss = saveString;
        //objss.add(new Date());//受检视图对象在运行时会检查类型是否匹配，不匹配时抛出异常
        objss.add("yesss");
        System.out.println(strings.get(1));
    }
}
