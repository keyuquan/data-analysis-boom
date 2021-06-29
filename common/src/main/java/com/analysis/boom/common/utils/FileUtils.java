package com.analysis.boom.common.utils;

import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.List;

/**
 * @author ：kequan
 * @date ：Created in 2019/12/3 10:13
 * @description：
 * @modified By：
 */
public class FileUtils {
    public static void appendToFile(String fileName, String str) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            writer.append(str);
            writer.newLine();
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void appendListToNewFile(String fileName, List<String> list) {
        try {
            File file = new File(fileName);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "utf-8"));
            for (String str : list) {
                bw.append(str);
                bw.newLine();
                bw.flush();
            }
            System.out.println("写入文件:" + fileName + " 数据条数：" + list.size());
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendListToFile(String fileName, List<String> list) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            for (String str : list) {
                writer.append(str);
                writer.newLine();
                writer.flush();
            }
            System.out.println("写入文件:" + fileName + " 数据条数：" + list.size());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void appendJSONObjectListToFile(String fileName, List<JSONObject> list) {
        try {
            File file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
            for (JSONObject obj : list) {
                writer.append(JSONObject.toJSONString(obj));
                writer.newLine();
                writer.flush();
            }
            System.out.println("写入文件:" + fileName + " 数据条数：" + list.size());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
