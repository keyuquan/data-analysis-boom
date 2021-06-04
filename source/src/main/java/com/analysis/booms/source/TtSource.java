package com.analysis.booms.source;

import com.analysis.booms.dao.PlatformTokenAdvertiserIdDao;
import com.analysis.booms.entity.PlatformTokenAdvertiserId;
import com.analysis.booms.utils.JdbcHelper;
import com.analysis.booms.utils.TtSourceUtils;
import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 巨量广告数据拉取
 */
public class TtSource {
    private final static Logger logger = LoggerFactory.getLogger(TtSource.class);

    public static void main(String[] args) {
        BasicDataSource boomDataSource = JdbcHelper.getBoomDataSource();
        List<PlatformTokenAdvertiserId> list = PlatformTokenAdvertiserIdDao.getTtPlatformTokenAdvertiserIdData(boomDataSource);
        for (int i = 0; i < list.size(); i++) {
            PlatformTokenAdvertiserId s = list.get(i);
            TtSourceUtils.getAdPlanConf(s.getAccessToken(),s.getAdvertiserId(),"2021-06-04");
        }
    }
}
