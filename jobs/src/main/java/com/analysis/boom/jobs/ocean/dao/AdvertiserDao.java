package com.analysis.boom.jobs.ocean.dao;

import com.analysis.boom.jobs.ocean.entity.AdvertiserEntity;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 广告主数据
 */
public class AdvertiserDao {

    /**
     * 获取巨量token 和 广告主ID
     *
     * @param conn
     * @return
     */
    public static List<AdvertiserEntity> getTtPlatformTokenAdvertiserIdData(Connection conn) {
        try {
            String sql = "SELECT   t.platform,t.access_token accessToken,t.refresh_token refreshToken,t.main_id mainId,a.advertiser_id  advertiserId from  \n" +
                    "platform_token t\n" +
                    "join  advertiser_id  a \n" +
                    "on t.main_id=a.main_id\n" +
                    "where  t.platform=1 order by  a.advertiser_id ";
            List<AdvertiserEntity> list = new QueryRunner().query(conn,sql, new BeanListHandler<>(AdvertiserEntity.class));
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

}
