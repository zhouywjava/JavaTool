package javaCore.enumts;

import javaCore.model.Color;

import static javaCore.model.Color.Red;
import static javaCore.model.Color.Yellow;

/**
 * @Description: 枚举测试
 * @Author: zyw
 * @Date: 2018/3/7
 */
public class EnumTest {

    public static void main(String[] args) {
        Color color = Color.Red;
        switch (color){
            case Red:
                System.out.println(Red.getCode());break;
            case Yellow:
                System.out.println(Yellow.getCode());break;
        }
    }
}
