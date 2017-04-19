package com.jay.amateur;

import java.util.regex.Pattern;



public class Check {

    public static void isNull(Object obj, String what) {
        if (obj == null) {
            throw new NullPointerException(what + " cannot be null !");
        }
    }

    public static void throwException(String msg) {
        throw new RuntimeException(msg);
    }

    /**
     * 验证URL地址
     * @param url 格式：http://blog.csdn.net:80/xyang81/article/details/7705960? 或 http://www.csdn.net:80
     * @return 验证成功返回true，验证失败返回false
     */
    public static boolean checkURL(String url) {
        String regex = "(https?://(w{3}\\.)?)?\\w+\\.\\w+(\\.[a-zA-Z]+)*(:\\d{1,5})?(/\\w*)*(\\??(.+=.*)?(&.+=.*)?)?";
        return Pattern.matches(regex, url);
    }

}
