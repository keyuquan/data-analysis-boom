package com.analysis.boom.jobs.ocean.main;

import com.analysis.boom.common.utils.DateUtils;
import com.analysis.boom.common.utils.JdbcUtils;
import com.analysis.boom.jobs.ocean.dao.AdPlanDataDao;
import com.analysis.boom.jobs.ocean.dao.AdvertiserDao;
import com.analysis.boom.jobs.ocean.entity.AdvertiserEntity;
import com.analysis.boom.jobs.utils.ThreadPoolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;
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
        for (int i = 0; i < adList.size(); i++) {
            AdvertiserEntity s = adList.get(i);
            String finalStartDate = startDate;
            String finalEndDate = endDate;
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    AdPlanDataDao.getAdPlanData(s, finalStartDate, finalEndDate);
                }
            });
        }
        ThreadPoolUtil.endThread(pool);
    }


}
