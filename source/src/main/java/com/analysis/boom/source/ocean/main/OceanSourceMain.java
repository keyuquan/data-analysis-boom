package com.analysis.boom.source.ocean.main;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.analysis.boom.source.ocean.entity.AdvertiserEntity;
import com.analysis.boom.source.ocean.utils.JdbcHelper;
import com.analysis.boom.source.ocean.dao.AdvertiserDao;
import com.analysis.boom.source.ocean.utils.OceanSourceUtils;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 巨量广告数据拉取
 */
public class OceanSourceMain {
    private final static Logger logger = LoggerFactory.getLogger(OceanSourceMain.class);

    public static void main(String[] args) {
        // ocean_ad_plan_conf   广告计划配置
        // ocean_ad_plan_data   广告计划数据
        // ocean_ad_creative_conf   广告创意配置
        // ocean_ad_creative_data   广告创意数据
        String dataMode = args[0];

        //  获取广告Id 数据
        BasicDataSource boomDataSource = JdbcHelper.getBoomsDataSource();
        List<AdvertiserEntity> list = AdvertiserDao.getTtPlatformTokenAdvertiserIdData(boomDataSource);

        //  遍历广告Id,获取对应数据
        for (int i = 0; i < list.size(); i++) {
            AdvertiserEntity s = list.get(i);
            logger.info("AdvertiserEntity : {} ", s.toString());
            int total_page = 2;
            int current_page = 1;
            do {
                String uri = "";
                String[] fields;
                int page = current_page;
                Map<String, Object> map = null;
                if (dataMode.equals("ocean_ad_plan_conf")) {
                    // ocean_ad_plan_conf   广告计划配置
                    uri = "2/ad/get/";
                    fields = new String[]{"id", "name", "budget", "budget_mode", "status", "opt_status", "open_url", "modify_time", "start_time", "end_time", "bid", "advertiser_id", "pricing", "flow_control_mode", "download_url", "quick_app_url", "schedule_type", "app_type", "cpa_bid", "cpa_skip_first_phrase", "external_url", "package", "campaign_id", "ad_modify_time", "ad_create_time", "audit_reject_reason", "retargeting_type", "retargeting_tags", "convert_id", "interest_tags", "hide_if_converted", "external_actions", "device_type", "auto_extend_enabled", "auto_extend_targets", "dpa_lbs", "dpa_city", "dpa_province", "dpa_recommend_type", "roi_goal", "subscribe_url", "form_id", "form_index", "app_desc", "app_thumbnails", "feed_delivery_search", "intelligent_flow_switch"}; //查询字段集合
                    String[] finalFields = fields;
                    map = new HashMap() {
                        {
                            put("advertiser_id", s.getAdvertiserId());
                            put("page", page);
                            put("page_size", 1000);
//                            put("filtering", new HashMap() {
//                                {
//                                    put("ad_create_time", "2021-06-07");
//                                }
//                            });
                            put("fields", finalFields);
                        }
                    };
                } else if (dataMode.equals("ocean_ad_plan_data")) {
                    // ocean_ad_plan_data   广告计划数据
                    uri = "2/report/ad/get/";
                    map = new HashMap() {
                        {
                            put("advertiser_id", s.getAdvertiserId());
                            put("page", page);
                            put("page_size", 1000);
                            put("start_date", "2021-06-01");
                            put("end_date", "2021-06-07");
                            put("group_by", new String[]{"STAT_GROUP_BY_FIELD_ID", "STAT_GROUP_BY_FIELD_STAT_TIME"});
                            put("fields", null);
                        }
                    };
                } else if (dataMode.equals("ocean_ad_creative_conf")) {
                    // ocean_ad_creative_conf   广告创意配置
                    uri = "2/creative/get/";
                    map = new HashMap() {
                        {
                            put("advertiser_id", s.getAdvertiserId());
                            put("page", page);
                            put("page_size", 1000);
                            put("'group_by'", new String[]{"STAT_GROUP_BY_FIELD_ID", "STAT_GROUP_BY_FIELD_STAT_TIME"});
                            put("fields", null);
//                            put("filtering", new HashMap() {
//                                {
//                                    put("creative_modify_time", "2021-06-07");
//                                }
//                            });
                        }
                    };
                } else if (dataMode.equals("ocean_ad_creative_data")) {
                    // ocean_ad_creative_data   广告创意数据
                    uri = "2/report/creative/get/";
                    map = new HashMap() {
                        {
                            put("advertiser_id", s.getAdvertiserId());
                            put("page", page);
                            put("page_size", 1000);
                            put("start_date", "2021-05-10");
                            put("end_date", "2021-06-07");
                            put("group_by", new String[]{"STAT_GROUP_BY_FIELD_ID", "STAT_GROUP_BY_FIELD_STAT_TIME"});
                            put("fields", null);
                        }
                    };
                }
                JSONObject object = OceanSourceUtils.getDataFromOcean(s.getAccessToken(), uri, map, current_page);
                JSONObject data = object.getJSONObject("data");
                if (data != null) {
                    JSONArray listData = data.getJSONArray("list");
                    JSONObject page_info = data.getJSONObject("page_info");
                    total_page = page_info.getInteger("total_page");
                    logger.info("page_info : {} ", page_info);
                    if (dataMode.equals("ocean_ad_plan_conf")) {
                       // JsonUtils.jsonToCsvAndSave(listData.toJSONString(), "广告计划配置表_" + s.getAdvertiserId() + ".csv");
                    } else if (dataMode.equals("ocean_ad_plan_data")) {
                       // JsonUtils.jsonToCsvAndSave(listData.toJSONString(), "广告计划数据表_" + s.getAdvertiserId() + ".csv");
                    } else if (dataMode.equals("ocean_ad_creative_conf")) {
                       // JsonUtils.jsonToCsvAndSave(listData.toJSONString(), "广告创意配置表_" + s.getAdvertiserId() + ".csv");
                    } else if (dataMode.equals("ocean_ad_creative_data")) {
                      //  JsonUtils.jsonToCsvAndSave(listData.toJSONString(), "广告创意数据表_" + s.getAdvertiserId() + ".csv");
                    }
                    current_page = page_info.getInteger("page") + 1;
                }
                current_page = current_page + 1;
            } while (current_page <= total_page);
        }
        JdbcHelper.close();
    }
}
