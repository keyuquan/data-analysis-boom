package com.analysis.booms.source.ocean.utils;


import com.analysis.booms.common.utils.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.File;
import java.io.IOException;

public class JsonUtils {
    /*
     * @Author
     * @Description
     * @Date 14:44 2020/3/28
     * @Param provincesJsonString json串
     * @Param fileName 文件路径
     * @return
     **/
    public static void jsonToCsvAndSave(String provincesJsonString, String fileName) {
        try {
            FileUtils.appendToFile(fileName, json2csv(provincesJsonString));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static String json2csv(String provincesJsonString) throws JSONException {
        JSONArray jsonArray = new JSONArray(provincesJsonString);
        return CDL.toString(jsonArray);
    }


}
