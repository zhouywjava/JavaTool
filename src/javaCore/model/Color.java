package javaCore.model;

/**
 * @Description: 颜色
 * @Author: zyw
 * @Date: 2018/3/7
 */
public enum Color {
    Red("1"),Yellow("2");

    private String code;

    Color(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
