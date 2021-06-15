package com.analysis.boom.jobs.pangolin.utils;

import java.security.MessageDigest;

public class HashUtils {

    public static String sha1(String srcStr){
        return hash("SHA-1", srcStr);
    }
    public static String hash(String algorithm, String srcStr) {
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            byte[] bytes = md.digest(srcStr.getBytes("utf-8"));
            return toHex(bytes);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static String toHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        //9a4a35c43d0bc175b7273004fc2c811be142f142
       System.out.println(sha1("dddd")); ;
    }
}