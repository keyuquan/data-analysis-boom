package com.analysis.boom.jobs.utils;

import java.security.MessageDigest;

public class HashUtils {
    public static String MD5(String srcStr){
        return hash("MD5", srcStr);
    }
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
       System.out.println(sha1("dddd")); ;
    }
}