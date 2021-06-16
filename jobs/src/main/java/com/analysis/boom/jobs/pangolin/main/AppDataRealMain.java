package com.analysis.boom.jobs.pangolin.main;

import com.alibaba.fastjson.JSONObject;
import com.analysis.boom.common.utils.DateUtils;
import com.analysis.boom.common.utils.FileUtils;
import com.analysis.boom.jobs.pangolin.entity.AppDataRealEntity;
import com.analysis.boom.jobs.pangolin.utils.PangolinRealDataUtils;
import com.analysis.boom.jobs.utils.ThreadPoolUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

/**
 * 穿山甲 app 数据拉取
 */
public class AppDataRealMain {
    private final static Logger logger = LoggerFactory.getLogger(AppDataRealMain.class);

    public static void main(String[] args) throws Exception {
        String startDate = DateUtils.getEndDay();
        String endDate = DateUtils.getEndDay();
        if (args.length >= 2) {
            startDate = args[0];
            endDate = args[1];
        }
        logger.info("startDate {} ,endDate {}", startDate, endDate);
        //  穿山甲用户数据
        Map<String, String> map = new HashMap();
        map.put("30773", "375daf3527bb7b9f2516cdb9879c4fe9");
        ExecutorService pool = ThreadPoolUtil.getScheduledThreadPool(10);
        for (String userId : map.keySet()) {
            String finalStartDate = startDate;
            String finalEndDate = endDate;
            pool.submit(new Runnable() {
                @Override
                public void run() {
                    getDayAppData(finalStartDate, finalEndDate, userId, userId, map.get(userId));
                }
            });
        }
        ThreadPoolUtil.endThread(pool);
    }

    /**
     * 获取每天的app数据
     *
     * @param startDate
     * @param endDate
     * @param userId
     * @param roleId
     * @param secureKey
     */
    public static void getDayAppData(String startDate, String endDate, String userId, String roleId, String secureKey) {
        int hasNext = 1;
        int offset = 0;
        do {
            String appDataFromPangolin = PangolinRealDataUtils.getAppDataFromPangolin(startDate, endDate, userId, roleId, secureKey, offset);
            AppDataRealEntity appDataRealEntity = JSONObject.parseObject(appDataFromPangolin, AppDataRealEntity.class);
            AppDataRealEntity.DataDTO data = appDataRealEntity.getData();
            List<JSONObject> reportList = data.getReportList();
            if (reportList != null && reportList.size() > 0) {
                FileUtils.appendJSONObjectListToFile("pangolin_day_app_kpi.txt", reportList);
            }
            hasNext = appDataRealEntity.getData().getHasNext();
            offset = offset + 500;
        } while (hasNext == 1);
    }


}
