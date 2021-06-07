package com.analysis.booms.source.ocean.utils;


import com.analysis.booms.common.utils.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONException;

public class JsonUtils {

    /**
     * @param provincesJsonString
     * @param fileName
     */
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
