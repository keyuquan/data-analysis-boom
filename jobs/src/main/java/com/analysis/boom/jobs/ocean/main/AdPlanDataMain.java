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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 巨量广告数据拉取 :ocean_ad_plan_data
 */
public class AdPlanDataMain {
    private final static Logger logger = LoggerFactory.getLogger(AdPlanDataMain.class);
    private static String uri = "2/report/ad/get/";

    public static void main(String[] args) throws Exception {
        //  获取广告Id 数据
        Connection boomConnection = JdbcUtils.getBoomConnection();
        List<AdvertiserEntity> adList = AdvertiserDao.getTtPlatformTokenAdvertiserIdData(boomConnection);
        JdbcUtils.closeBoom();

        Connection dtConnection = JdbcUtils.getDtConnection();
        //  遍历广告Id,获取对应数据
        for (int i = 0; i < adList.size(); i++) {
            AdvertiserEntity s = adList.get(i);
            logger.info("AdvertiserEntity : {} ", s.toString());
            int totalPage = 2;
            int currentPage = 1;
            do {
                int page = currentPage;
                Map<String, Object> map = new HashMap() {
                    {
                        put("advertiser_id", s.getAdvertiserId());
                        put("page", page);
                        put("page_size", 1000);
                        put("start_date", "2021-06-01");
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
                List<AdPlanDataEntity.DataDTO.ListDTO> list = data.getList();
                for (int j = 0; j < list.size(); j++) {
                    System.out.println(JSONObject.toJSONString(list.get(j)));
                }
                AdPlanDataDao.batch(dtConnection, list);

                AdPlanDataEntity.DataDTO.PageInfoDTO pageInfo = data.getPageInfo();
                currentPage = pageInfo.getPage() + 1;
                totalPage = pageInfo.getTotalPage();
            } while (currentPage <= totalPage);
        }
        JdbcUtils.closeDt();
    }
}
