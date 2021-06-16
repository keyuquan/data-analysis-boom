package com.analysis.boom.jobs.pangolin.main;

import com.alibaba.fastjson.JSONObject;
import com.analysis.boom.common.utils.DateUtils;
import com.analysis.boom.common.utils.FileUtils;
import com.analysis.boom.jobs.pangolin.entity.AppDataEntity;
import com.analysis.boom.jobs.pangolin.utils.PangolinDataUtils;

import java.util.List;

/**
 * 穿山甲 app 数据拉取
 */
public class AppDataMain {

    public static void main(String[] args) {
        String startDate = DateUtils.getStartDay();
        String endDate = DateUtils.getEndDay();
        String appDataFromPangolin = PangolinDataUtils.getAppDataFromPangolin(startDate, endDate);
        AppDataEntity appDataEntity = JSONObject.parseObject(appDataFromPangolin, AppDataEntity.class);
        Integer code = appDataEntity.getCode();
        List<JSONObject> data = appDataEntity.getData();
        if (code == 100 && data != null && data.size() > 0) {
            FileUtils.appendJSONObjectListToFile("pangolin_app_data_" + endDate + ".txt", data);
        }
    }
}
