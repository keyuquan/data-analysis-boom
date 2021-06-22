package com.analysis.boom.jobs.pangle.Dao;

import com.alibaba.fastjson.JSONObject;
import com.analysis.boom.common.utils.DateUtils;
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
    public static List<String> getDayAppData(String startDate, String endDate, String userId, String roleId, String secureKey) {

        List<String> listAll = new ArrayList<>();
        int hasNext = 1;
        int offset = 0;
        do {
            // 整理参数
            String dimensions = "date,site_id";
            String signType = "MD5";
            String version = "2.0";
            String timestamp = (new Date().getTime()) / 1000 + "";
            Map<String, Object> map = new TreeMap();
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
            Map<String, Object> sortMap = sortMapByKey(map);
            sortMap.put("sign", signatureGen(sortMap, secureKey));
            // 发送数据请求
            String str = HttpUtils.doGet(report_real_app, sortMap, "");
            // 解析数据
            AppDataRealEntity appDataRealEntity = JSONObject.parseObject(str, AppDataRealEntity.class);
            AppDataRealEntity.DataDTO data = appDataRealEntity.getData();
            List<JSONObject> list = data.getReportList();
            String updateTime = DateUtils.getSysFullDate();
            for (JSONObject obj : list) {
                obj.put("api_req_cnt", obj.getOrDefault("api_req_cnt", 0));
                obj.put("api_ret_cnt", obj.getOrDefault("api_ret_cnt", 0));
                obj.put("api_imp_cnt", obj.getOrDefault("api_imp_cnt", 0));
                obj.put("api_clk_cnt", obj.getOrDefault("api_clk_cnt", 0));
                obj.put("api_ecpm", obj.getOrDefault("api_ecpm", 0d));
                obj.put("api_revenue", obj.getOrDefault("api_revenue", 0d));
                obj.put("update_time", updateTime);
                listAll.add(obj.toJSONString());
            }
            hasNext = appDataRealEntity.getData().getHasNext();
            offset = offset + 500;
        } while (hasNext == 1);

        return listAll;
    }

    public static String signatureGen(Map<String, Object> sortMap, String secureKey) {
        String strSign = "";
        for (String key : sortMap.keySet()) {
            strSign = strSign + key + "=" + sortMap.get(key) + "&";
        }
        return HashUtils.MD5(strSign.substring(0, strSign.length() - 1) + secureKey);
    }

    public static Map<String, Object> sortMapByKey(Map<String, Object> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, Object> sortMap = new TreeMap<String, Object>(
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
