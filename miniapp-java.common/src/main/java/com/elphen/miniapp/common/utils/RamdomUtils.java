package com.elphen.miniapp.common.utils;

import java.util.Random;

/**
 * @program: miniapp
 * @description: 随机生成工具类
 * @author: Elphen
 * @create: 2019-12-31 18:16
 **/
public class RamdomUtils {

    /**
     * 随机生成制定位数的字符串，包含A-Z,a-z，0-9
     * @param length
     * @return
     */
    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

}
