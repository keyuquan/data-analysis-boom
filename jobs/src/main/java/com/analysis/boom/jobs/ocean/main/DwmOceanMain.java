package com.analysis.boom.jobs.ocean.main;

import com.analysis.boom.common.utils.DateUtils;
import com.analysis.boom.common.utils.JdbcUtils;
import com.analysis.boom.jobs.ocean.dao.AdPlanDataDao;
import com.analysis.boom.jobs.ocean.dao.AdvertiserDao;
import com.analysis.boom.jobs.ocean.entity.AdvertiserEntity;
import com.analysis.boom.jobs.utils.KafkaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;

/**
 * 巨量广告数据拉取 :ocean_ad_plan_data
 */
public class DwmOceanMain {
    private final static Logger logger = LoggerFactory.getLogger(DwmOceanMain.class);
    private static String uri = "2/report/ad/get/";

    public static void main(String[] args) throws Exception {
        String startDate = DateUtils.getStartDay(-2);
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
        for (int i = 0; i < adList.size(); i++) {
            AdvertiserEntity s = adList.get(i);
            String finalStartDate = startDate;
            String finalEndDate = endDate;
            logger.info("Advertiser {} ,startDate {},endDate {}", s.getAdvertiserId(), finalStartDate, finalEndDate);

            List<String> list = AdPlanDataDao.getDayAdPlanData(s, finalStartDate, finalEndDate);
            logger.info("list size {}", list.size());
            KafkaUtils.sendDataToKafka("boom_dwm_ocean_day_ad_plan_kpi", list);


        }
        KafkaUtils.close();
    }


}
