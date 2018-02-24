package javaCore.collections;

import java.util.EnumMap;
import java.util.EnumSet;

/**
 * @Description: EnumMap EnumSet
 * @Author: zyw
 * @Date: 2018/2/24
 */
public class EnumTest {

    private enum Day{
        MONDAY(1),TUESDAY(2),WENSDAY(3),THOSDAY(4),FRIDAY(5)
         ;
        private int num;
        Day(int num){
            this.num = num;
        }

        @Override
        public String toString() {
            return "Day{" +
                    "num=" + num +
                    '}';
        }
    }
    private enum Car{
        BMW(200000),AUDI(100000),HONDA(100000)
        ;
        private long price;
        Car(long price){
            this.price = price;
        }

        @Override
        public String toString() {
            return "Car{" +
                    "price=" + price +
                    '}';
        }
    }


    public static void main(String[] args) {
        EnumMap enumMap = new EnumMap<>(Day.class);
        enumMap.put(Day.MONDAY,"星期一");
        enumMap.put(Day.TUESDAY,"星期二");
        enumMap.forEach((k,v)-> System.out.println("key: " + k + " value:" + v));
        System.out.println("==========================");
        EnumSet enumSet = EnumSet.allOf(Car.class);
        enumSet.forEach((v)-> System.out.println(v));
        System.out.println("==========================");
        EnumSet enumSet2 = EnumSet.range(Car.AUDI,Car.HONDA);
        enumSet2.forEach((v)-> System.out.println(v));
        System.out.println("==========================");
        EnumSet enumSet3 = EnumSet.of(Car.BMW,Car.HONDA);
        enumSet3.forEach((v)-> System.out.println(v));
    }

}
