package com.analysis.boom.jobs.utils;


import com.alibaba.fastjson.JSONObject;
import com.analysis.boom.common.conf.Config;
import com.analysis.boom.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PrestoUtils {
    private static Connection conn = null;
    private static final Logger logger = LoggerFactory.getLogger(PrestoUtils.class);

    /**
     * 加载驱动
     */
    static {
        try {
            Class.forName("com.facebook.presto.jdbc.PrestoDriver");//注册加载驱动
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
    public static Connection getConnection() throws SQLException {
        conn = DriverManager.getConnection(Config.PRESTO_URL, Config.PRESTO_USER, null);
        logger.info("获取 persto 连接:" + Config.PRESTO_URL);
        return conn;
    }

    /**
     * 关闭conn
     *
     * @param conn
     */
    public static void close(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 执行语句
     *
     * @param sql
     * @throws SQLException
     */
    public static void execute(Connection conn, String label, String sql) throws SQLException {
        long start = System.currentTimeMillis();
        PreparedStatement stmt = conn.prepareStatement(sql);
        logger.info(sql);
        stmt.execute();
        stmt.close();
        long end = System.currentTimeMillis();
        logger.info(label + " 执行耗时(毫秒):" + (end - start));
        logger.info(" -----------------------");
    }

    public static List<String> query(Connection conn, String label, String sql) throws SQLException {
        logger.info(sql);
        long start = System.currentTimeMillis();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<String> list = new ArrayList();
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();
        String updateTime = DateUtils.getSysFullDate();
        while (rs.next()) {
            Map<String, Object> rowData = new HashMap();
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), rs.getObject(i));
            }
            rowData.put("update_time", updateTime);
            System.out.println(JSONObject.toJSONString(rowData));
            list.add(JSONObject.toJSONString(rowData));
        }
        rs.close();
        stmt.close();
        long end = System.currentTimeMillis();
        logger.info(label + " 执行耗时(毫秒):" + (end - start));
        return list;
    }

    public static void main(String[] args) throws SQLException {
        Connection connection = PrestoUtils.getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("show tables");
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }
        rs.close();
        connection.close();
    }
}

