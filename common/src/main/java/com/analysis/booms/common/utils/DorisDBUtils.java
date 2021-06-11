package com.analysis.booms.common.utils;

import com.analysis.booms.common.conf.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DorisDBUtils {
    private static Connection conn = null;
    private static final Logger logger = LoggerFactory.getLogger(DorisDBUtils.class);

    /**
     * 加载驱动
     */
    static {
        try {
            Class.forName(Config.DORIS_DRIVER);//注册加载驱动
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

}

