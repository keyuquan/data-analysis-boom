package com.analysis.boom.jobs.ocean.main;

import com.alibaba.fastjson.JSONObject;
import com.analysis.boom.common.utils.DateUtils;
import com.analysis.boom.common.utils.FileUtils;
import com.analysis.boom.common.utils.JdbcUtils;
import com.analysis.boom.jobs.ocean.dao.AdvertiserDao;
import com.analysis.boom.jobs.ocean.entity.AdPlanConfEntity;
import com.analysis.boom.jobs.ocean.entity.AdvertiserEntity;
import com.analysis.boom.jobs.ocean.utils.OceanDataUtils;
import com.analysis.boom.jobs.utils.ThreadPoolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
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
        String startDate = DateUtils.getStartDay();
        String endDate = DateUtils.getEndDay();
        if (args.length >= 2) {
            startDate = args[0];
            endDate = args[1];
        }
        logger.info("startDate {} ,endDate {}", startDate, endDate);
        //  获取广告主列表
        Connection boomConnection = JdbcUtils.getBoomConnection();
        List<AdvertiserEntity> adList = AdvertiserDao.getTtPlatformTokenAdvertiserIdData(boomConnection);
        JdbcUtils.closeBoom();
        // 遍历广告主列表,获取巨量数据,把数据存入DT

        ExecutorService pool = ThreadPoolUtil.getScheduledThreadPool(10);
        logger.info(" adList.size() : {} ", adList.size());
        for (int i = 0; i < adList.size(); i++) {
            AdvertiserEntity s = adList.get(i);
            String finalEndDate = endDate;
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    getAdPlanConfData(s, finalEndDate);
                }
            });
        }
        ThreadPoolUtil.endThread(pool);

    }

    /**
     * 获取广告配置数据
     *
     * @param s
     */
    public static void getAdPlanConfData(AdvertiserEntity s, String endDate) {
        logger.error(JSONObject.toJSONString(s));
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
                            put("ad_create_time", null);
                        }
                    });
                    put("fields", fields);
                }
            };
            String str = OceanDataUtils.getDataFromOcean(s.getAccessToken(), uri, map);
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
            List<JSONObject> list = data.getList();
            if (list != null && list.size() > 0) {
                FileUtils.appendJSONObjectListToFile("ocean_ad_plan_conf.txt", list);
            }
            AdPlanConfEntity.DataDTO.PageInfoDTO pageInfo = data.getPageInfo();
            currentPage = pageInfo.getPage() + 1;
            totalPage = pageInfo.getTotalPage();
        } while (currentPage <= totalPage);
    }

}
