package com.analysis.boom.jobs.ocean.main;

import com.analysis.boom.common.utils.DateUtils;
import com.analysis.boom.common.utils.JdbcUtils;
import com.analysis.boom.jobs.ocean.dao.AdPlanConfDao;
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
public class OdsOceanMain {
    private final static Logger logger = LoggerFactory.getLogger(OdsOceanMain.class);

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
        // 遍历广告主列表,获取巨量数据,把数据存入
        int days = DateUtils.differentDays(startDate, endDate, "yyyy-MM-dd") + 1;
        for (int j = 0; j < days; j++) {
            String startOneDate = DateUtils.addDay(startDate, j);
            String endOneDate = startOneDate;
            for (int i = 0; i < adList.size(); i++) {
                AdvertiserEntity s = adList.get(i);
                logger.info("Advertiser {} ,endDate {}", s.getAdvertiserId(), endOneDate);
                List<String> list = AdPlanConfDao.getAdPlanConfData(s, endOneDate);
                logger.info("conf size {}", list.size());
                KafkaUtils.sendDataToKafka("boom_ods_ocean_ad_plan_conf", list);
            }
        }
        KafkaUtils.close();
    }
}
