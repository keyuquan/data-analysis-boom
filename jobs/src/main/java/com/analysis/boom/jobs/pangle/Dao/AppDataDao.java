package com.analysis.boom.jobs.pangle.Dao;

import com.alibaba.fastjson.JSONObject;
import com.analysis.boom.common.utils.FileUtils;
import com.analysis.boom.jobs.pangle.entity.AppDataEntity;
import com.analysis.boom.jobs.utils.HashUtils;
import com.analysis.boom.jobs.utils.HttpUtils;
import org.apache.commons.lang.StringUtils;

import java.util.*;

public class AppDataDao {
    public static String report_user = "https://partner.oceanengine.com/union/media/open/api/report/user";
    public static String report_app = "https://partner.oceanengine.com/union/media/open/api/report/app";

    /**
     * 获取广告计划配置
     *
     * @return
     */
    public static void getDayAppData(String startDate, String endDate, String userId, String secureKey) {
        // 整理参数
        String timestamp = new Date().getTime() + "";
        String nonce = System.currentTimeMillis() + "";
        Map<String, Object> map = new TreeMap();
        map.put("user_id", userId);
        map.put("timestamp", timestamp);
        map.put("nonce", nonce);
        map.put("start_date", startDate);
        map.put("end_date", endDate);
        map.put("user_id", userId);
        map.put("sign", signatureGen(secureKey, timestamp, nonce));
        // 发送数据请求
        String str = HttpUtils.doGet(report_app, map, "");
        // 解析数据
        AppDataEntity appDataEntity = JSONObject.parseObject(str, AppDataEntity.class);
        // 数据存入文件
        List<JSONObject> list = appDataEntity.getData();
        if (list != null && list.size() > 0) {
            FileUtils.appendJSONObjectListToFile("pangle_day_app_kpi_" + endDate + ".txt", list);
        }


    }

    public static String signatureGen(String secureKey, String timestamp, String nonce) {
        List list = new ArrayList();
        list.add(secureKey);
        list.add(timestamp);
        list.add(nonce);
        Collections.sort(list);
        return HashUtils.sha1(StringUtils.join(list, ""));
    }

}
