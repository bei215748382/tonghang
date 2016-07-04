package com.tonghang.server.util;

import java.security.MessageDigest;

public class MD5Util {

    private final static String saltA = "tonghang";

    private final static String saltB = "bei";

    /***
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String inStr) {
        String addSalt = saltA + inStr + saltB;// MD5一般不可解密,加密这里的操作可以自己定义
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        char[] charArray = addSalt.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];

        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }

    // /**
    // * 加密解密算法 执行一次加密，两次解密
    // */
    // public static String convertPassword(String inStr,String salt){
    // char[] sa = salt.toCharArray();
    // char[] a = inStr.toCharArray();
    // for (int i = 0; i < a.length; i++){
    // if(i % 3 == 0){
    // a[i] = (char) (a[i] ^ sa[0]);
    // }
    // if(i % 5 == 0){
    // a[i] = (char) (a[i] ^ sa[2]);
    // }
    // }
    // String s = new String(a);
    // return s;
    //
    // }
    //
    // // 测试主函数
    public static void main(String args[]) {
        String s = new String("123456");
        System.out.println("原始：" + s);
        System.out.println("MD5后：" + string2MD5(s));

    }
}