package com.analysis.booms.source.ocean.utils;


import com.analysis.booms.common.conf.Config;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;


/**
 * Created by jh on 2017/9/8.
 */
public class JdbcHelper {

    // baoliang 数据库连接
    private static BasicDataSource datasource_booms;

    public static BasicDataSource getBoomsDataSource() {
        if (datasource_booms == null) {
            System.out.println("JdbcHelper getDataSource----------------------");
            datasource_booms = new BasicDataSource();
            datasource_booms.setDriverClassName("com.mysql.cj.jdbc.Driver");
            datasource_booms.setUrl(Config.MYSQL_URL);
            datasource_booms.setUsername(Config.USER_NAME);
            datasource_booms.setPassword(Config.PASS_WORD);
            datasource_booms.setPoolPreparedStatements(true);
            datasource_booms.setInitialSize(2);//初始化的连接数
            datasource_booms.setMaxIdle(2);//最大空闲数
            datasource_booms.setMinIdle(1);//最小空闲
            datasource_booms.setTimeBetweenEvictionRunsMillis(6000L);
            datasource_booms.setMinEvictableIdleTimeMillis(300000L);
            datasource_booms.setValidationQuery("SELECT 1 FROM DUAL");
        }
        return datasource_booms;
    }

    public static  void close() {
        if (datasource_booms != null) {
            try {
                datasource_booms.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            datasource_booms = null;
        }
    }




}
