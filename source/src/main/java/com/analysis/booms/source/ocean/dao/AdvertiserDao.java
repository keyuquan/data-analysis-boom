package com.analysis.booms.source.ocean.dao;

import com.analysis.booms.source.ocean.entity.AdvertiserIdEntity;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * 广告主数据
 */
public class AdvertiserDao {

    /**
     * 获取巨量token 和 广告主ID
     *
     * @param datasource
     * @return
     */
    public static List<AdvertiserIdEntity> getTtPlatformTokenAdvertiserIdData(BasicDataSource datasource) {
        try {
            String sql = "SELECT   t.platform,t.access_token accessToken,t.refresh_token refreshToken,t.main_id mainId,a.advertiser_id  advertiserId from  \n" +
                    "platform_token t\n" +
                    "join  advertiser_id  a \n" +
                    "on t.main_id=a.main_id\n" +
                    "where  t.platform=10";
            List<AdvertiserIdEntity> list = new QueryRunner(datasource).query(sql, new BeanListHandler<>(AdvertiserIdEntity.class));
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
//
//    public static void main(String[] args) {
//
//        List<AdvertiserId> list = getTtPlatformTokenAdvertiserIdData(JdbcHelper.getBoomDataSource());
//        for (int i = 0; i < list.size(); i++) {
//            AdvertiserId s = list.get(i);
//            System.out.println(s.toString());
//        }
//    }
}
