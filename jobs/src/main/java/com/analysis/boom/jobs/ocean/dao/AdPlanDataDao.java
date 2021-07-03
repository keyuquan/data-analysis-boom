package com.analysis.boom.jobs.ocean.dao;

import com.alibaba.fastjson.JSONObject;
import com.analysis.boom.common.utils.DateUtils;
import com.analysis.boom.jobs.ocean.entity.AdPlanDataEntity;
import com.analysis.boom.jobs.ocean.entity.AdvertiserEntity;
import com.analysis.boom.jobs.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdPlanDataDao {
    public static String ad_plan_data_uri = "https://ad.oceanengine.com/open_api/2/report/ad/get/";

    private final static Logger logger = LoggerFactory.getLogger(AdPlanConfDao.class);

    /**
     * 获取报表数据:天-广告计划  维度
     *
     * @param s
     * @param startDate
     * @param endDate
     */
    public static List<String> getDayAdPlanData(AdvertiserEntity s, String startDate, String endDate) {
        List<String> listAll = new ArrayList<>();
        int totalPage = 2;
        int currentPage = 1;

        do {
            // 整理参数
            int page = currentPage;
            String[] fields = new String[]{"cost", "show", "avg_show_cost", "click", "avg_click_cost", "ctr", "convert", "convert_cost", "convert_rate", "deep_convert", "deep_convert_cost", "deep_convert_rate", "attribution_convert", "attribution_convert_cost", "attribution_deep_convert", "attribution_deep_convert_cost", "download_start", "download_start_cost", "download_start_rate", "download_finish", "download_finish_cost", "download_finish_rate", "click_install", "install_finish", "install_finish_cost", "install_finish_rate", "active", "active_cost", "active_rate", "register", "active_register_cost", "active_register_rate"};
            Map<String, Object> map = new HashMap();
            map.put("advertiser_id", s.getAdvertiserId());
            map.put("page", page);
            map.put("page_size", 1000);
            map.put("start_date", startDate);
            map.put("end_date", endDate);
            map.put("group_by", new String[]{"STAT_GROUP_BY_FIELD_ID", "STAT_GROUP_BY_FIELD_STAT_TIME"});
            map.put("time_granularity", "STAT_TIME_GRANULARITY_DAILY");
            map.put("fields", fields);
            HashMap filtering = new HashMap() {{
                put("status", "AD_STATUS_ALL");
            }};
            map.put("filtering", filtering);
            // 发送数据请求
            String str = HttpUtils.doBodyGet(ad_plan_data_uri, map, s.getAccessToken());
            // 解析数据
            AdPlanDataEntity adPlanDataEntity = JSONObject.parseObject(str, AdPlanDataEntity.class);
            Integer code = adPlanDataEntity.getCode();
            if (code != 0) {
                logger.info("code {},message {}", code, adPlanDataEntity.getMessage());
                break;
            }
            AdPlanDataEntity.DataDTO data = adPlanDataEntity.getData();
            if (data == null) {
                break;
            }
            // 数据存入文件
            List<JSONObject> list = data.getList();
            String updateTime = DateUtils.getSysFullDate();
            for (JSONObject obj : list) {
                obj.put("stat_datetime", obj.getOrDefault("stat_datetime", "1970-01-01 00:00:00").toString().substring(0, 10));
                obj.put("update_time", updateTime);
                // System.out.println(obj.toJSONString());
                listAll.add(obj.toJSONString());
            }
            AdPlanDataEntity.DataDTO.PageInfoDTO pageInfo = data.getPageInfo();
            currentPage = pageInfo.getPage() + 1;
            totalPage = pageInfo.getTotalPage();
        } while (currentPage <= totalPage);
        return listAll;
    }

    /**
     * 获取报表数据:天-广告计划-投放位置  维度
     *
     * @param s
     * @param startDate
     * @param endDate
     */
    public static List<String> getDayAdPlanInventoryData(AdvertiserEntity s, String startDate, String endDate) {
        List<String> listAll = new ArrayList<>();
        int totalPage = 2;
        int currentPage = 1;
        do {
            // 整理参数
            int page = currentPage;
            String[] fields = new String[]{"cost", "show", "avg_show_cost", "click", "avg_click_cost", "ctr", "convert", "convert_cost", "convert_rate", "deep_convert", "deep_convert_cost", "deep_convert_rate", "attribution_convert", "attribution_convert_cost", "attribution_deep_convert", "attribution_deep_convert_cost", "download_start", "download_start_cost", "download_start_rate", "download_finish", "download_finish_cost", "download_finish_rate", "click_install", "install_finish", "install_finish_cost", "install_finish_rate", "active", "active_cost", "active_rate", "register", "active_register_cost", "active_register_rate"};
            Map<String, Object> map = new HashMap() {
                {
                    put("advertiser_id", s.getAdvertiserId());
                    put("page", page);
                    put("page_size", 1000);
                    put("start_date", startDate);
                    put("end_date", endDate);
                    put("group_by", new String[]{"STAT_GROUP_BY_FIELD_ID", "STAT_GROUP_BY_FIELD_STAT_TIME", "STAT_GROUP_BY_INVENTORY"});
                    put("time_granularity", "STAT_TIME_GRANULARITY_DAILY");
                    put("fields", fields);
                }
            };
            // 发送数据请求
            String str = HttpUtils.doBodyGet(ad_plan_data_uri, map, s.getAccessToken());
            // 解析数据
            AdPlanDataEntity adPlanDataEntity = JSONObject.parseObject(str, AdPlanDataEntity.class);
            Integer code = adPlanDataEntity.getCode();
            if (code != 0) {
                logger.info("code {},message {}", code, adPlanDataEntity.getMessage());
                break;
            }
            AdPlanDataEntity.DataDTO data = adPlanDataEntity.getData();
            if (data == null) {
                continue;
            }
            // 数据存入文件
            List<JSONObject> list = data.getList();
            String updateTime = DateUtils.getSysFullDate();
            for (JSONObject obj : list) {
                obj.put("stat_datetime", obj.getOrDefault("stat_datetime", "1970-01-01 00:00:00").toString().substring(0, 10));
                obj.put("update_time", updateTime);
                // System.out.println(obj.toJSONString());
                listAll.add(obj.toJSONString());
            }
            AdPlanDataEntity.DataDTO.PageInfoDTO pageInfo = data.getPageInfo();
            currentPage = pageInfo.getPage() + 1;
            totalPage = pageInfo.getTotalPage();
        } while (currentPage <= totalPage);
        return listAll;
    }

}
