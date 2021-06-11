package com.analysis.boom.jobs.ocean.main;

import com.alibaba.fastjson.JSONObject;
import com.analysis.boom.common.utils.JdbcUtils;
import com.analysis.boom.jobs.ocean.dao.AdvertiserDao;
import com.analysis.boom.jobs.ocean.entity.AdPlanConfEntity;
import com.analysis.boom.jobs.ocean.entity.AdvertiserEntity;
import com.analysis.boom.jobs.ocean.utils.OceanSourceUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 巨量广告数据拉取 :ocean_ad_plan_data
 */
public class OceanAdPlanConfMain {
    private final static Logger logger = LoggerFactory.getLogger(OceanAdPlanConfMain.class);

    public static void main(String[] args) throws Exception {
        //  获取数据库连接
        Connection boomConnection = JdbcUtils.getBoomConnection();
        Connection dtConnection = JdbcUtils.getDtConnection();
        //  获取广告Id 数据
        List<AdvertiserEntity> adList = AdvertiserDao.getTtPlatformTokenAdvertiserIdData(boomConnection);
        String uri = "2/ad/get/";
        //  遍历广告Id,获取对应数据
        for (int i = 0; i < adList.size(); i++) {
            AdvertiserEntity s = adList.get(i);
            logger.info("AdvertiserEntity : {} ", s.toString());
            int totalPage = 2;
            int currentPage = 1;
            do {
                String[] fields = new String[]{"id", "name", "budget", "budget_mode", "status", "opt_status", "open_url", "modify_time", "start_time", "end_time", "bid", "advertiser_id", "pricing", "flow_control_mode", "download_url", "quick_app_url", "schedule_type", "app_type", "cpa_bid", "cpa_skip_first_phrase", "external_url", "package", "campaign_id", "ad_modify_time", "ad_create_time", "audit_reject_reason", "retargeting_type", "retargeting_tags", "convert_id", "interest_tags", "hide_if_converted", "external_actions", "device_type", "auto_extend_enabled", "auto_extend_targets", "dpa_lbs", "dpa_city", "dpa_province", "dpa_recommend_type", "roi_goal", "subscribe_url", "form_id", "form_index", "app_desc", "app_thumbnails", "feed_delivery_search", "intelligent_flow_switch"};
                int page = currentPage;
                Map<String, Object> map = new HashMap() {
                    {
                        put("advertiser_id", s.getAdvertiserId());
                        put("page", page);
                        put("page_size", 1000);
//                            put("filtering", new HashMap() {
//                                {
//                                    put("ad_create_time", "2021-06-07");
//                                }
//                            });
                        put("fields", fields);
                    }
                };
                String str = OceanSourceUtils.getDataFromOcean(s.getAccessToken(), uri, map);
                AdPlanConfEntity adPlanConfEntity = JSONObject.parseObject(str, AdPlanConfEntity.class);
                Integer code = adPlanConfEntity.getCode();
                if (code != 0) {
                    continue;
                }
                AdPlanConfEntity.DataDTO data = adPlanConfEntity.getData();
                if (data == null) {
                    continue;
                }
                List<AdPlanConfEntity.DataDTO.ListDTO> list = data.getList();
                for (AdPlanConfEntity.DataDTO.ListDTO dto : list) {
                    System.out.println(JSONObject.toJSONString(dto));
                }
                AdPlanConfEntity.DataDTO.PageInfoDTO pageInfo = data.getPageInfo();
                currentPage = pageInfo.getPage() + 1;
                totalPage = pageInfo.getTotalPage();
            } while (currentPage <= totalPage);
        }
        JdbcUtils.closeBoom();
        JdbcUtils.closeDt();
    }
}
