package com.analysis.booms.common.utils;

import com.analysis.nxd.common.conf.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcUtils {
    private static Connection conn = null;
    private static final Logger logger = LoggerFactory.getLogger(JdbcUtils.class);

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

    /**
     * 查询某日期内最大的用户等级
     *
     * @param conn
     * @param label
     * @param sql
     * @return
     * @throws SQLException
     */
    public static int queryCount(Connection conn, String label, String sql) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        int count = 0;
        try {
            long start = System.currentTimeMillis();
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                count = rs.getInt(1);
                logger.info(label + " count:" + count);
            }
            long end = System.currentTimeMillis();
            logger.info(label + " 执行耗时(毫秒):" + (end - start));
            logger.info(" -----------------------");
        } catch (SQLException throwables) {
            if (!throwables.toString().contains("detailMessage = Partition does not exist")) {
                logger.error(sql);
                throwables.printStackTrace();
            }
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return count;
    }

    /**
     * 获取缓存表
     *
     * @param conn
     * @return
     * @throws SQLException
     */
    public static List<String> getTemptable(Connection conn, String db) throws SQLException {
        Statement stmt = null;
        ResultSet rs = null;
        List<String> tempTables = new ArrayList<>();
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select table_name from information_schema.tables where table_schema='" + db + "' ");
            while (rs.next()) {
                String tableName = rs.getString(1);
                if (tableName.startsWith("temp_")) {
                    tempTables.add(tableName);
                }
            }
        } catch (SQLException throwables) {
            if (!throwables.toString().contains("detailMessage = Partition does not exist")) {
                throwables.printStackTrace();
            }
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return tempTables;
    }

    /**
     * 清除缓存数据表
     *
     * @param conn
     * @return
     * @throws SQLException
     */
    public static void clearTemptable(Connection conn, String db) throws SQLException {
        List<String> list = getTemptable(conn, db);
        for (String tableName : list) {
            execute(conn, "drop  table  " + db + "." + tableName, "drop  table  " + db + "." + tableName);
        }
    }

    /**
     * 把 hdfs数据加载到 doris
     *
     * @throws SQLException
     */
    public static void LoadHdfsToDoris(Connection conn, String label, String dataUrl, String dbName, String tableName, String columnsNames) throws SQLException {

        String SYSTEM_DATE = DateUtils.getSysFullDateNUmber();
        String HDFS_URL = Config.HDFS_URL + dataUrl + "/part-*";
        String BROKER_NAME = Config.BROKER_NAME;
        String DORIS_USER = Config.DORIS_USER;
        String DORIS_PASSWORD = Config.DORIS_PASSWORD;

        String sql_load = "LOAD LABEL dbName.tableName_SYSTEM_DATE\n" +
                "(\n" +
                "    DATA INFILE(\"HDFS_URL\")\n" +
                "    INTO TABLE tableName\n" +
                "    COLUMNS TERMINATED BY \"|\"\n" +
                "    (columnsNames)\n" +
                ")\n" +
                "WITH BROKER 'BROKER_NAME'\n" +
                "(\n" +
                "    \"username\"=\"DORIS_USER\",\n" +
                "    \"password\"=\"DORIS_PASSWORD\"\n" +
                ")\n" +
                "PROPERTIES\n" +
                "(\n" +
                "    \"timeout\" = \"3600\"\n" +
                ")";

        JdbcUtils.execute(conn, label, sql_load.replace("SYSTEM_DATE", SYSTEM_DATE)
                .replace("HDFS_URL", HDFS_URL)
                .replace("dbName", dbName)
                .replace("tableName", tableName)
                .replace("columnsNames", columnsNames)
                .replace("BROKER_NAME", BROKER_NAME)
                .replace("DORIS_USER", DORIS_USER)
                .replace("DORIS_PASSWORD", DORIS_PASSWORD)
        );
    }

}

