package com.analysis.boom.jobs.ocean.main;

import com.alibaba.fastjson.JSONObject;
import com.analysis.boom.common.utils.DateUtils;
import com.analysis.boom.common.utils.FileUtils;
import com.analysis.boom.common.utils.JdbcUtils;
import com.analysis.boom.jobs.ocean.dao.AdvertiserDao;
import com.analysis.boom.jobs.ocean.entity.AdPlanDataEntity;
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
public class AdPlanDataMain {
    private final static Logger logger = LoggerFactory.getLogger(AdPlanDataMain.class);
    private static String uri = "2/report/ad/get/";

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
            String finalStartDate = startDate;
            String finalEndDate = endDate;
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    getAdPlanData(s, finalStartDate, finalEndDate);
                }
            });
        }
        ThreadPoolUtil.endThread(pool);
    }

    /**
     * 获取广告报表数据
     *
     * @param s
     * @param startDate
     * @param endDate
     */
    public static void getAdPlanData(AdvertiserEntity s, String startDate, String endDate) {
        logger.error(JSONObject.toJSONString(s));
        int totalPage = 2;
        int currentPage = 1;
        do {
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
            String str = OceanDataUtils.getDataFromOcean(s.getAccessToken(), uri, map);
            AdPlanDataEntity adPlanDataEntity = JSONObject.parseObject(str, AdPlanDataEntity.class);
            Integer code = adPlanDataEntity.getCode();
            if (code != 0) {
                continue;
            }
            AdPlanDataEntity.DataDTO data = adPlanDataEntity.getData();
            if (data == null) {
                continue;
            }
            List<JSONObject> list = data.getList();
            if (list != null && list.size() > 0) {
                System.out.println("list.size()=" + list.size());
                FileUtils.appendJSONObjectListToFile("ocean_ad_plan_data_day_ad_kpi.txt", list);
            }
            AdPlanDataEntity.DataDTO.PageInfoDTO pageInfo = data.getPageInfo();
            currentPage = pageInfo.getPage() + 1;
            totalPage = pageInfo.getTotalPage();
        } while (currentPage <= totalPage);

    }
}
