package com.analysis.boom.jobs.ocean.main;

import com.alibaba.fastjson.JSONObject;
import com.analysis.boom.common.utils.JdbcUtils;
import com.analysis.boom.jobs.ocean.dao.AdPlanConfDao;
import com.analysis.boom.jobs.ocean.dao.AdPlanDataDao;
import com.analysis.boom.jobs.ocean.dao.AdvertiserDao;
import com.analysis.boom.jobs.ocean.entity.AdPlanConfEntity;
import com.analysis.boom.jobs.ocean.entity.AdPlanDataEntity;
import com.analysis.boom.jobs.ocean.entity.AdvertiserEntity;
import com.analysis.boom.jobs.ocean.utils.OceanSourceUtils;
import com.analysis.boom.jobs.utils.ThreadPoolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * 巨量广告数据拉取 :ocean_ad_plan_data
 */
public class AdPlanConfMain {
    private final static Logger logger = LoggerFactory.getLogger(AdPlanConfMain.class);
    private static String uri = "2/ad/get/";

    public static void main(String[] args) throws Exception {
        //  获取广告主列表
        Connection boomConnection = JdbcUtils.getBoomConnection();
        List<AdvertiserEntity> adList = AdvertiserDao.getTtPlatformTokenAdvertiserIdData(boomConnection);
        JdbcUtils.closeBoom();
        // 遍历广告主列表,获取巨量数据,把数据存入DT
        Connection dtConnection = JdbcUtils.getDtConnection();
        ExecutorService pool = ThreadPoolUtil.getScheduledThreadPool(10);
        logger.info(" adList.size() : {} ", adList.size());
        for (int i = 0; i < adList.size(); i++) {
            AdvertiserEntity s = adList.get(i);
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    insertAdPlanConfDataToDT(s, dtConnection);
                    insertAdPlanDataToDT(s, dtConnection);
                }
            });
        }
        ThreadPoolUtil.endThread(pool);
        JdbcUtils.closeDt();
    }

    /**
     * 广告计划配置存入mysql
     *
     * @param s
     * @param dtConnection
     */
    public static void insertAdPlanConfDataToDT(AdvertiserEntity s, Connection dtConnection) {
        System.out.println(JSONObject.toJSONString(s));
        //  遍历广告Id,获取对应数据
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
                    put("filtering", new HashMap() {
                        {
                            put("ad_create_time", "2021-06-11");
                        }
                    });
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
            // 存入数据库
            List<AdPlanConfEntity.DataDTO.ListDTO> list = data.getList();
            try {
                AdPlanConfDao.batch(dtConnection, list);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            AdPlanConfEntity.DataDTO.PageInfoDTO pageInfo = data.getPageInfo();
            currentPage = pageInfo.getPage() + 1;
            totalPage = pageInfo.getTotalPage();
        } while (currentPage <= totalPage);
    }


    /**
     * 广告计划数据存入MySQL
     *
     * @param s
     * @param dtConnection
     */
    public static void insertAdPlanDataToDT(AdvertiserEntity s, Connection dtConnection) {
        int totalPage = 2;
        int currentPage = 1;
        do {
            int page = currentPage;
            Map<String, Object> map = new HashMap() {
                {
                    put("advertiser_id", s.getAdvertiserId());
                    put("page", page);
                    put("page_size", 1000);
                    put("start_date", "2021-06-11");
                    put("end_date", "2021-06-11");
                    put("group_by", new String[]{"STAT_GROUP_BY_FIELD_ID", "STAT_GROUP_BY_FIELD_STAT_TIME"});
                    put("fields", null);
                }
            };
            String str = OceanSourceUtils.getDataFromOcean(s.getAccessToken(), uri, map);
            AdPlanDataEntity adPlanDataEntity = JSONObject.parseObject(str, AdPlanDataEntity.class);
            Integer code = adPlanDataEntity.getCode();
            if (code != 0) {
                continue;
            }
            AdPlanDataEntity.DataDTO data = adPlanDataEntity.getData();
            if (data == null) {
                continue;
            }
            // 存入数据库
            try {
                AdPlanDataDao.batch(dtConnection, data.getList());
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            AdPlanDataEntity.DataDTO.PageInfoDTO pageInfo = data.getPageInfo();
            currentPage = pageInfo.getPage() + 1;
            totalPage = pageInfo.getTotalPage();
        } while (currentPage <= totalPage);

    }
}
