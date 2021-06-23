package com.analysis.boom.jobs.ocean.dao;

import com.alibaba.fastjson.JSONObject;
import com.analysis.boom.common.utils.DateUtils;
import com.analysis.boom.jobs.ocean.entity.AdPlanConfEntity;
import com.analysis.boom.jobs.ocean.entity.AdvertiserEntity;
import com.analysis.boom.jobs.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdPlanConfDao {
    public static String ad_plan_conf_uri = "https://ad.oceanengine.com/open_api/2/ad/get/";
    private final static Logger logger = LoggerFactory.getLogger(AdPlanConfDao.class);

    /**
     * 获取广告配置数据
     *
     * @param s
     */
    public static List<String> getAdPlanConfData(AdvertiserEntity s, String runDate) {
        List<String> listAll = new ArrayList<>();
        //  遍历广告Id,获取对应数据
        int totalPage = 2;
        int currentPage = 1;
        do {
            // 整理参数
            String[] fields = new String[]{"advertiser_id", "ad_id", "campaign_id", "name", "package", "app_type", "download_type", "download_url", "download_mode", "inventory_type", "modify_time", "ad_create_time", "ad_modify_time"};
            int page = currentPage;
            Map<String, Object> map = new HashMap();
            map.put("advertiser_id", s.getAdvertiserId());
            map.put("page", page);
            map.put("page_size", 1000);
            HashMap filtering = new HashMap() {{
                put("ad_modify_time", runDate);
            }};
            map.put("filtering", filtering);
            map.put("fields", fields);
            // 发送数据请求
            String str = HttpUtils.doBodyGet(ad_plan_conf_uri, map, s.getAccessToken());
            // 解析数据
            AdPlanConfEntity adPlanConfEntity = JSONObject.parseObject(str, AdPlanConfEntity.class);
            Integer code = adPlanConfEntity.getCode();
            if (code != 0) {
                logger.info("code {},message {}", code, adPlanConfEntity.getMessage());
                break;
            }
            AdPlanConfEntity.DataDTO data = adPlanConfEntity.getData();
            if (data == null) {
                continue;
            }
            List<JSONObject> list = data.getList();
            String updateTime = DateUtils.getSysFullDate();
            for (JSONObject obj : list) {
                obj.put("update_time", updateTime);
                obj.put("inventory_type", obj.getOrDefault("inventory_type", "").toString().replaceAll("\"", "").replaceAll("\\[", "").replaceAll("]", ""));
                listAll.add(obj.toJSONString());
            }
            AdPlanConfEntity.DataDTO.PageInfoDTO pageInfo = data.getPageInfo();
            currentPage = pageInfo.getPage() + 1;
            totalPage = pageInfo.getTotalPage();
        } while (currentPage <= totalPage);
        return listAll;
    }

}
