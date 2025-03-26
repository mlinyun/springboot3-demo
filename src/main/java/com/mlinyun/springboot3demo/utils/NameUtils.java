package com.mlinyun.springboot3demo.utils;

public class NameUtils {

    /**
     * 生成随机用户名
     *
     * @return 随机用户名
     */
    public static String generateRandomUsername() {
        int length = (int) (Math.random() * 13) + 4; // 生成 4 到 16 位的随机长度
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder username = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = (int) (Math.random() * characters.length());
            username.append(characters.charAt(index));
        }
        return username.toString();
    }

}
