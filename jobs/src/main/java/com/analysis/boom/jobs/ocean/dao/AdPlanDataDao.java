package com.analysis.boom.jobs.ocean.dao;

import com.alibaba.fastjson.JSONObject;
import com.analysis.boom.common.utils.DateUtils;
import com.analysis.boom.common.utils.FileUtils;
import com.analysis.boom.jobs.ocean.entity.AdPlanDataEntity;
import com.analysis.boom.jobs.ocean.entity.AdvertiserEntity;
import com.analysis.boom.jobs.utils.HttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdPlanDataDao {
    public static String ad_plan_data_uri = "https://ad.oceanengine.com/open_api/2/report/ad/get/";

    /**
     * 获取报表数据:天-广告计划  维度
     *
     * @param s
     * @param startDate
     * @param endDate
     */
    public static List<String> getAdPlanData(AdvertiserEntity s, String startDate, String endDate) {
        List<String> listAll = new ArrayList<>();
        int totalPage = 2;
        int currentPage = 1;
        do {
            // 整理参数
            int page = currentPage;
            Map<String, Object> map = new HashMap() {
                {
                    put("advertiser_id", s.getAdvertiserId());
                    put("page", page);
                    put("page_size", 1000);
                    put("start_date", startDate);
                    put("end_date", endDate);
                    put("group_by", new String[]{"STAT_GROUP_BY_FIELD_ID", "STAT_GROUP_BY_FIELD_STAT_TIME"});
                    put("time_granularity", "STAT_TIME_GRANULARITY_DAILY");
                    put("fields", null);
                }
            };
            // 发送数据请求
            String str = HttpUtils.doBodyGet(ad_plan_data_uri, map, s.getAccessToken());
            // 解析数据
            AdPlanDataEntity adPlanDataEntity = JSONObject.parseObject(str, AdPlanDataEntity.class);
            Integer code = adPlanDataEntity.getCode();
            if (code != 0) {
                continue;
            }
            AdPlanDataEntity.DataDTO data = adPlanDataEntity.getData();
            if (data == null) {
                continue;
            }
            // 数据存入文件
            List<JSONObject> list = data.getList();
            String updateTime = DateUtils.getSysFullDate();
            for (JSONObject obj : list) {
                obj.put("update_time", updateTime);
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
    public static List<String> getAdPlanInventoryData(AdvertiserEntity s, String startDate, String endDate) {
        List<String> listAll = new ArrayList<>();
        int totalPage = 2;
        int currentPage = 1;
        do {
            // 整理参数
            int page = currentPage;
            Map<String, Object> map = new HashMap() {
                {
                    put("advertiser_id", s.getAdvertiserId());
                    put("page", page);
                    put("page_size", 1000);
                    put("start_date", startDate);
                    put("end_date", endDate);
                    put("group_by", new String[]{"STAT_GROUP_BY_FIELD_ID", "STAT_GROUP_BY_FIELD_STAT_TIME", "STAT_GROUP_BY_INVENTORY"});
                    put("time_granularity", "STAT_TIME_GRANULARITY_DAILY");
                    put("fields", null);
                }
            };
            // 发送数据请求
            String str = HttpUtils.doBodyGet(ad_plan_data_uri, map, s.getAccessToken());
            // 解析数据
            AdPlanDataEntity adPlanDataEntity = JSONObject.parseObject(str, AdPlanDataEntity.class);
            Integer code = adPlanDataEntity.getCode();
            if (code != 0) {
                continue;
            }
            AdPlanDataEntity.DataDTO data = adPlanDataEntity.getData();
            if (data == null) {
                continue;
            }
            // 数据存入文件
            List<JSONObject> list = data.getList();
            String updateTime = DateUtils.getSysFullDate();
            for (JSONObject obj : list) {
                obj.put("update_time", updateTime);
                listAll.add(obj.toJSONString());
            }
            AdPlanDataEntity.DataDTO.PageInfoDTO pageInfo = data.getPageInfo();
            currentPage = pageInfo.getPage() + 1;
            totalPage = pageInfo.getTotalPage();
        } while (currentPage <= totalPage);
        return listAll;
    }

}
