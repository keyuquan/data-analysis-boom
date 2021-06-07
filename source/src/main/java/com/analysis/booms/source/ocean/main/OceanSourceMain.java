package com.analysis.booms.source.ocean.main;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.analysis.booms.source.ocean.dao.AdvertiserDao;
import com.analysis.booms.source.ocean.entity.AdvertiserIdEntity;
import com.analysis.booms.source.ocean.utils.JsonUtils;
import com.analysis.booms.source.ocean.utils.OceanSourceUtils;
import com.analysis.booms.source.utils.JdbcHelper;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 巨量广告数据拉取
 */
public class OceanSourceMain {
    private final static Logger logger = LoggerFactory.getLogger(OceanSourceMain.class);

    public static void main(String[] args) {
        BasicDataSource boomDataSource = JdbcHelper.getBoomsDataSource();
        List<AdvertiserIdEntity> list = AdvertiserDao.getTtPlatformTokenAdvertiserIdData(boomDataSource);
        for (int i = 0; i < list.size(); i++) {
            AdvertiserIdEntity s = list.get(i);
            logger.info("AdvertiserIdEntity : {} ", s.toString());
            int total_page = 2;
            int current_page = 1;
            do {
                JSONObject object = OceanSourceUtils.getAdPlanConf(s.getAccessToken(), s.getAdvertiserId(), "2021-06-07", current_page);
                JSONObject data = object.getJSONObject("data");
                if (data != null) {
                    JSONArray listData = data.getJSONArray("list");
                    JsonUtils.jsonToCsvAndSave(listData.toJSONString(), "广告配置表_" + s.getAdvertiserId() + ".csv");
                    JSONObject page_info = data.getJSONObject("page_info");
                    total_page = page_info.getInteger("total_page");
                    current_page = page_info.getInteger("page") + 1;
                    logger.info("page_info : {} ", page_info);
                }
                logger.info("object : {} ", object.toJSONString());
            } while (current_page <= total_page);
        }
        JdbcHelper.close();

    }
}
