package com.analysis.boom.common.utils;

import com.analysis.boom.common.conf.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtils {
    private static Connection conn_boom = null;
    private static final Logger logger = LoggerFactory.getLogger(JdbcUtils.class);
    /**
     * 加载驱动
     */
    static {
        try {
            Class.forName(Config.JDBC_DRIVER);//注册加载驱动
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取 Connetion
     *
     * @return
     * @throws SQLException
     */
    public static Connection getBoomConnection() throws Exception {
        conn_boom = DriverManager.getConnection(Config.MYSQL_URL, Config.USER_NAME, Config.PASS_WORD);
        logger.info("获取mysql连接:" + Config.MYSQL_URL);
        return conn_boom;
    }


    /**
     * 关闭 conn
     */
    public static void closeBoom() {
        if (conn_boom != null) {
            try {
                conn_boom.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }




}
