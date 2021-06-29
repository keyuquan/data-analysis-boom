package com.analysis.boom.common.utils;

import com.analysis.boom.common.conf.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DorisDBUtils {
    private static Connection conn = null;
    private static final Logger logger = LoggerFactory.getLogger(DorisDBUtils.class);

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


    public static List<Map<String, String>> queryMap(Connection conn,String sql) throws SQLException {

        logger.info(sql);
        long start = System.currentTimeMillis();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Map<String, String>> list = new ArrayList();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (rs.next()) {
            Map<String, String> map = new HashMap<>();
            for (int i = 0; i < columnCount; i++) {
                map.put(metaData.getColumnLabel(i + 1), rs.getString(i + 1));
            }
            list.add(map);
        }
        rs.close();
        stmt.close();
        long end = System.currentTimeMillis();
        logger.info("queryTaProjectId 执行耗时(毫秒):" + (end - start));
        return list;
    }

    /**
     * 查询数据，为写入 csv 做准备
     *
     * @param conn
     * @param sql
     * @return
     * @throws SQLException
     */
    public static List<String> query(Connection conn, String sql) throws SQLException {
        logger.info(sql);
        long start = System.currentTimeMillis();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        List<String> list = new ArrayList();
        String head = "";
        for (int i = 0; i < columnCount; i++) {
            head = head + metaData.getColumnLabel(i + 1) + "//";
        }
        list.add(head);
        while (rs.next()) {
            String s = "";
            for (int i = 0; i < columnCount; i++) {
                s = s + rs.getString(i + 1) + "//";
            }
            list.add(s);
        }
        rs.close();
        stmt.close();
        long end = System.currentTimeMillis();
        logger.info("queryTaProjectId 执行耗时(毫秒):" + (end - start));
        return list;
    }

    /**
     * 获取 Connetion
     *
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        conn = DriverManager.getConnection(Config.DORIS_URL, Config.DORIS_USER, Config.DORIS_PASSWORD);
        logger.info("获取mysql连接:" + Config.DORIS_URL);
        return conn;
    }

    /**
     * 关闭 conn
     */
    public static void close() {
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

    /**
     * 批量插入数据
     *
     * @param conn
     * @param sql
     * @param list
     * @throws SQLException
     */
    public static void executeBatch(Connection conn, String sql, List<List> list) throws SQLException {
        if (list.size() > 0) {
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            for (List l : list) {
                for (int i = 0; i < l.size(); i++) {
                    preparedStatement.setObject(i + 1, l.get(i));
                }
                preparedStatement.addBatch();
            }
            preparedStatement.executeBatch();
            preparedStatement.close();
            list.clear();
        }
    }

    public static void main(String[] args) throws SQLException {
        List<List> list = new ArrayList<List>();
        for (int i = 0; i < 100; i++) {
            List l = new ArrayList();
            l.add("2021-06-10");
            l.add(100 + i);
            l.add(1000 + i);
            l.add(100 + i);
            list.add(l);
        }
        String sql = "insert  into test.detail(create_time,order_id,order_state,total_price)  values(?,?,?,?)";
        Connection connection = getConnection();
        executeBatch(connection, sql, list);
        close();
    }
    public boolean isNum(String msg){
        if(java.lang.Character.isDigit(msg.charAt(0))){
            return true;
        }
        return false;
    }
}

