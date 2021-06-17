package com.analysis.boom.jobs.pangle.Dao;

import com.alibaba.fastjson.JSONObject;
import com.analysis.boom.common.utils.FileUtils;
import com.analysis.boom.jobs.pangle.entity.AppDataRealEntity;
import com.analysis.boom.jobs.utils.HashUtils;
import com.analysis.boom.jobs.utils.HttpUtils;

import java.util.*;

public class AppDataRealDao {
    public static String report_real_app = "https://www.pangle.cn/union_pangle/open/api/mediation/get_daily_income_report_data";

    /**
     * 获取每天的app数据
     *
     * @param startDate
     * @param endDate
     * @param userId
     * @param roleId
     * @param secureKey
     */
    public static void getDayAppData(String startDate, String endDate, String userId, String roleId, String secureKey) {
        int hasNext = 1;
        int offset = 0;
        do {
            // 整理参数
            String dimensions = "date,site_id";
            String signType = "MD5";
            String version = "2.0";
            String timestamp = (new Date().getTime()) / 1000 + "";
            Map<String, String> map = new TreeMap();
            map.put("start_date", startDate);
            map.put("end_date", endDate);
            map.put("dimensions", dimensions);
            map.put("limit", "500");
            map.put("offset", offset + "");
            map.put("sign_type", signType);
            map.put("timestamp", timestamp);
            map.put("user_id", userId);
            map.put("role_id", roleId);
            map.put("version", version);
            // 对参数进行排序
            Map<String, String> sortMap = sortMapByKey(map);
            sortMap.put("sign", signatureGen(sortMap, secureKey));
            // 发送数据请求
            String str = HttpUtils.doGet(report_real_app, sortMap, "");
            // 解析数据
            AppDataRealEntity appDataRealEntity = JSONObject.parseObject(str, AppDataRealEntity.class);
            AppDataRealEntity.DataDTO data = appDataRealEntity.getData();
            List<JSONObject> list = data.getReportList();
            // 数据存入文件
            if (list != null && list.size() > 0) {
                FileUtils.appendJSONObjectListToFile("pangle_real_day_app_kpi_"+endDate+".txt", list);
            }
            hasNext = appDataRealEntity.getData().getHasNext();
            offset = offset + 500;
        } while (hasNext == 1);
    }

    public static String signatureGen(Map<String, String> sortMap, String secureKey) {
        String strSign = "";
        for (String key : sortMap.keySet()) {
            strSign = strSign + key + "=" + sortMap.get(key) + "&";
        }
        return HashUtils.MD5(strSign.substring(0, strSign.length() - 1) + secureKey);
    }

    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<String, String>(
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                });
        sortMap.putAll(map);
        return sortMap;
    }

}
