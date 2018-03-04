package javaThread.Queue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @Description: 线程安全集合
 * @Author: zyw
 * @Date: 2018/3/4
 */
public class SynchonizeTest {

    public static void main(String[] args) {
        //经常修改的情况下，同步的ArrayList可以胜过CopyOnWriteArrayList。后者适用于多读少写的情况
        List<String> synchArrayList = Collections.synchronizedList(new ArrayList<String>());
        //set有获得锁，get没有获得锁。set完成前，通过get获得的集合都是旧的集合
        //写的以后加锁是为了防止多线程产生多个副本。
        List<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        Map<String,String> synchHashMap = Collections.synchronizedMap(new HashMap<String,String>());

        //线程安全集合操作是线程安全的，但是采用迭代器遍历的时候需要上锁，因为在迭代期间，有可能发生修改
        synchronized(synchHashMap){
            Iterator<String> iter = synchHashMap.keySet().iterator();
            while(iter.hasNext()){
                System.out.println(iter.next());
            }
        }
    }
}
