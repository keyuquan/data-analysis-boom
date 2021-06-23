package com.analysis.boom.jobs.pangle.main;

import com.analysis.boom.common.utils.DateUtils;
import com.analysis.boom.jobs.pangle.Dao.AppDataDao;
import com.analysis.boom.jobs.utils.KafkaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 穿山甲 app 数据拉取
 */
public class AppDataMain {
    private final static Logger logger = LoggerFactory.getLogger(AppDataMain.class);

    public static void main(String[] args) throws Exception {
        String startDate = DateUtils.getStartDay(5);
        String endDate = DateUtils.getEndDay();
        if (args.length >= 2) {
            startDate = args[0];
            endDate = args[1];
        }
        logger.info("startDate {} ,endDate {}", startDate, endDate);
        //  穿山甲用户数据
        Map<String, String> map = new HashMap();
        map.put("30773", "375daf3527bb7b9f2516cdb9879c4fe9");// 豹亮
        map.put("41953", "2964b22953d124a005010d3a08fff3b6");// 海南迅游
        // 数据循环跑，每天跑一次
        int days = DateUtils.differentDays(startDate, endDate, "yyyy-MM-dd") + 1;
        for (int i = 0; i < days; i++) {
            String startOneDate = DateUtils.addDay(startDate, i);
            String endOneDate = startOneDate;
            logger.info("startOneDate {} ,endOneDate {}", startOneDate, endOneDate);
            for (String userId : map.keySet()) {
                List<String> list = AppDataDao.getDayAppData(startOneDate, endOneDate, userId, userId, map.get(userId));
             //   KafkaUtils.sendDataToKafka("boom_dwm_pangle_day_site_kpi", list);
            }
        }
        KafkaUtils.close();
    }

}
