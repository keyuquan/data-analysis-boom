package com.analysis.booms.source.ocean.main;

import com.analysis.booms.source.ocean.dao.AdvertiserDao;
import com.analysis.booms.source.ocean.entity.AdvertiserIdEntity;
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
            System.out.println(s.toString());
            OceanSourceUtils.getAdPlanConf(s.getAccessToken(), s.getAdvertiserId(), "2021-06-07");
        }
    }
}
