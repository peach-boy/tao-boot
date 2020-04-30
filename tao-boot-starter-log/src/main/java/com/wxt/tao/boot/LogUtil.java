package com.wxt.tao.boot;

/**
 * @author xiantao.wu
 * @email xiantao.wu@guanaitong.com
 * @create 2020/4/3016:05
 **/
public class LogUtil {

    private static final Integer MAX_LENGTH = 2000;

    public LogUtil() {
    }

    public static String truncate(String text) {
        if (text == null) {
            return null;
        } else {
            return text.length() <= MAX_LENGTH ? text : text.substring(0, MAX_LENGTH);
        }
    }

}
