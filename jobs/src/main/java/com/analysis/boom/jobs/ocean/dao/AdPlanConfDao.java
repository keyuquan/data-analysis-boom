package com.analysis.boom.jobs.ocean.dao;

import com.alibaba.fastjson.JSONObject;
import com.analysis.boom.common.utils.FileUtils;
import com.analysis.boom.jobs.ocean.entity.AdPlanConfEntity;
import com.analysis.boom.jobs.ocean.entity.AdvertiserEntity;
import com.analysis.boom.jobs.utils.HttpUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdPlanConfDao {
    public static String ad_plan_conf_uri = "https://ad.oceanengine.com/open_api/2/ad/get/";

    /**
     * 获取广告配置数据
     *
     * @param s
     */
    public static void getAdPlanConfData(AdvertiserEntity s, String runDate) {
        //  遍历广告Id,获取对应数据
        int totalPage = 2;
        int currentPage = 1;
        do {
            // 整理参数
            String[] fields = new String[]{"id", "name", "budget", "budget_mode", "status", "opt_status", "open_url", "modify_time", "start_time", "end_time", "bid", "advertiser_id", "pricing", "flow_control_mode", "download_url", "quick_app_url", "schedule_type", "app_type", "cpa_bid", "cpa_skip_first_phrase", "external_url", "package", "campaign_id", "ad_modify_time", "ad_create_time", "audit_reject_reason", "retargeting_type", "retargeting_tags", "convert_id", "interest_tags", "hide_if_converted", "external_actions", "device_type", "auto_extend_enabled", "auto_extend_targets", "dpa_lbs", "dpa_city", "dpa_province", "dpa_recommend_type", "roi_goal", "subscribe_url", "form_id", "form_index", "app_desc", "app_thumbnails", "feed_delivery_search", "intelligent_flow_switch"};
            int page = currentPage;
            Map<String, Object> map = new HashMap();
            map.put("advertiser_id", s.getAdvertiserId());
            map.put("page", page);
            map.put("page_size", 1000);
            HashMap filtering = new HashMap() {{
                put("ad_create_time", runDate);
            }};
            map.put("filtering", filtering);
            map.put("fields", fields);
            // 发送数据请求
            String str = HttpUtils.doBodyGet(ad_plan_conf_uri, map, s.getAccessToken());
            // 解析数据
            AdPlanConfEntity adPlanConfEntity = JSONObject.parseObject(str, AdPlanConfEntity.class);
            Integer code = adPlanConfEntity.getCode();
            if (code != 0) {
                continue;
            }
            AdPlanConfEntity.DataDTO data = adPlanConfEntity.getData();
            if (data == null) {
                continue;
            }
            // 数据存入文件
            List<JSONObject> list = data.getList();
            if (list != null && list.size() > 0) {
                FileUtils.appendJSONObjectListToFile("ocean_ad_plan_conf_" + runDate + ".txt", list);
            }
            AdPlanConfEntity.DataDTO.PageInfoDTO pageInfo = data.getPageInfo();
            currentPage = pageInfo.getPage() + 1;
            totalPage = pageInfo.getTotalPage();
        } while (currentPage <= totalPage);
    }

}
