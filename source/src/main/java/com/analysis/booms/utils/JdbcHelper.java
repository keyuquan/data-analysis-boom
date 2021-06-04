package com.analysis.booms.utils;


import com.analysis.booms.common.conf.Config;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;


/**
 * Created by jh on 2017/9/8.
 */
public class JdbcHelper {

    // baoliang 数据库连接
    private static BasicDataSource datasource_boom;

    public static BasicDataSource getBoomDataSource() {
        if (datasource_boom == null) {
            System.out.println("JdbcHelper getDataSource----------------------");
            datasource_boom = new BasicDataSource();
            datasource_boom.setDriverClassName("com.mysql.cj.jdbc.Driver");
            datasource_boom.setUrl(Config.MYSQL_URL);
            datasource_boom.setUsername(Config.USER_NAME);
            datasource_boom.setPassword(Config.PASS_WORD);
            datasource_boom.setPoolPreparedStatements(true);
            datasource_boom.setInitialSize(2);//初始化的连接数
            datasource_boom.setMaxIdle(2);//最大空闲数
            datasource_boom.setMinIdle(1);//最小空闲
            datasource_boom.setTimeBetweenEvictionRunsMillis(6000L);
            datasource_boom.setMinEvictableIdleTimeMillis(300000L);
            datasource_boom.setValidationQuery("SELECT 1 FROM DUAL");
        }
        return datasource_boom;
    }

    public static synchronized void Close() {
        if (datasource_boom != null) {
            try {
                datasource_boom.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            datasource_boom = null;
        }
    }




}
