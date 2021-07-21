package com.analysis.boom.common.utils;

import com.alibaba.fastjson.JSONObject;
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


    public static List<Map<String, Object>> queryMap(Connection conn, String sql) throws SQLException {

        logger.info(sql);
        long start = System.currentTimeMillis();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        List<Map<String, Object>> list = new ArrayList();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        while (rs.next()) {
            Map<String, Object> map = new HashMap<>();
            for (int i = 0; i < columnCount; i++) {
                String value = rs.getString(i + 1);
                if (validateNumber(value)){
                    map.put(metaData.getColumnLabel(i + 1), Double.valueOf(value));
                }else {
                    map.put(metaData.getColumnLabel(i + 1), value);
                }

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
     * 判断是否是整数或者是小数
     *
     * @param str
     * @return true：是，false不是
     */
    private static boolean validateNumber(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return str.matches("[+-]?[0-9]+(\\.[0-9]{1,4})?");

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
     * 查询数据，为写入 csv 做准备
     *
     * @param conn
     * @param sql
     * @return
     * @throws SQLException
     */
    public static List<String> queryCsv(Connection conn, String sql) throws SQLException {
        logger.info(sql);
        long start = System.currentTimeMillis();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        List<String> list = new ArrayList();
        String head = "";
        for (int i = 0; i < columnCount; i++) {
            head = head + metaData.getColumnLabel(i + 1) + ",";
        }
        list.add(head.substring(0,head.lastIndexOf(",")));
        while (rs.next()) {
            String s = "";
            for (int i = 0; i < columnCount; i++) {
                s = s + rs.getString(i + 1) + ",";
            }
            list.add(s.substring(0,s.lastIndexOf(",")));
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
     *
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
        Connection connection = getConnection();

      String sql  ="select\n" +
                "data_date  as  dataDate\n" +
                ",pkg_code as  pkgCode\n" +
                ",pkg_name as  pkgName\n" +
                ",add_user_count as  addUserCount\n" +
                ",active_user_count as  activeUserCount\n" +
                ",concat(ifnull(round(add_user_count*100 /active_user_count,2),0) ,'%')   as addUserCountEachActiveUserCount\n" +
                ",concat(ifnull(round(retain_1*100/add_user_count,2),0),'%')  retain1\n" +
                ",concat(ifnull(round(retain_2*100/add_user_count,2),0),'%')  retain2\n" +
                ",concat(ifnull(round(retain_3*100/add_user_count,2),0),'%')  retain3\n" +
                ",concat(ifnull(round(retain_4*100/add_user_count,2),0),'%')  retain4\n" +
                ",concat(ifnull(round(retain_5*100/add_user_count,2),0),'%')  retain5\n" +
                ",concat(ifnull(round(retain_6*100/add_user_count,2),0),'%')  retain6\n" +
                ",concat(ifnull(round(retain_7*100/add_user_count,2),0),'%')  retain7\n" +
                ",concat(ifnull(round(retain_15*100/add_user_count,2),0),'%')   retain15\n" +
                ",concat(ifnull(round(retain_30*100 /add_user_count,2),0) ,'%') retain30\n" +
                ",ifnull(round(add_ad_show_count /add_ad_show_user_count,2),0)  addAdShowCountEachAddAdShowEserCount\n" +
                ",ifnull(round(ad_show_count /ad_show_user_count,2),0)  adShowCountEachAdShowEserCount\n" +
                ",api_revenue as apiRevenue\n" +
                ",api_revenue_all as  apiRevenueAll\n" +
                ",revenue as  revenue\n" +
                ",if(date(now())=data_date ,api_revenue_all+revenue,api_revenue_all) as  apiRevenueRevenueAll\n" +
                ",api_imp_cnt as apiImpCnt\n" +
                ",round(api_ecpm,1)  as  ecpm\n" +
                ",ifnull(round(add_ecpm,2),0)  addEcpm\n" +
                ",ifnull(round(api_revenue/active_user_count,2),0)  activeArpu\n" +
                ",ifnull(round(ltv_0 /add_user_count,2),0)   ltv0\n" +
                ",ifnull(round(ltv_1 /add_user_count,2),0)  ltv1\n" +
                ",ifnull(round(ltv_2 /add_user_count,2),0)  ltv2\n" +
                ",ifnull(round(ltv_3 /add_user_count,2),0)  ltv3\n" +
                ",ifnull(round(ltv_4 /add_user_count,2),0)  ltv4\n" +
                ",ifnull(round(ltv_5 /add_user_count,2),0)  ltv5\n" +
                ",ifnull(round(ltv_6 /add_user_count,2),0)  ltv6\n" +
                ",ifnull(round(ltv_7 /add_user_count,2),0)  ltv7\n" +
                ",ifnull(round(ltv_15 /add_user_count,2),0) ltv15\n" +
                ",ifnull(round(ltv_30 /add_user_count,2),0)  ltv30\n" +
                ",concat(ifnull(round(api_revenue * 100/cost,2),0),'%')   ROI\n" +
                ",concat(ifnull(round(api_revenue_all * 100/cost_all,2),0),'%')   ROIAll\n" +
                ",pangle_cost pangleCost\n" +
                ",ifnull(round(pangle_cost/pangle_active,2),0)  pangleCpa\n" +
                ",pangle_active pangleActive\n" +
                ",concat(ifnull(round(pangle_ltv_0 * 100 /pangle_cost,2),0),'%') pangleFirstROI\n" +
                ",site_cost siteCost\n" +
                ",ifnull(round(site_cost/site_active,2),0) siteCpa\n" +
                ",site_active siteActive\n" +
                ",concat(ifnull(round(site_ltv_0 * 100 /site_cost,2),0),'%') siteFirstROI\n" +
                ",cost_all costAll\n" +
                ",active active\n" +
                ",concat(ifnull(round(ltv_0 * 100 /cost,2),0),'%') firstROI\n" +
                "from\n" +
                "doris_boom.app_day_pkg_kpi";
        List<Map<String, Object>> list = queryMap(connection, "select\n" +
                "data_date  as  dataDate\n" +
                ",pkg_code as  pkgCode\n" +
                ",pkg_name as  pkgName\n" +
                ",add_user_count as  addUserCount\n" +
                ",active_user_count as  activeUserCount\n" +
                ",concat(ifnull(round(add_user_count*100 /active_user_count,2),0) ,'%')   as addUserCountEachActiveUserCount\n" +
                ",concat(ifnull(round(retain_1*100/add_user_count,2),0),'%')  retain1\n" +
                ",concat(ifnull(round(retain_2*100/add_user_count,2),0),'%')  retain2\n" +
                ",concat(ifnull(round(retain_3*100/add_user_count,2),0),'%')  retain3\n" +
                ",concat(ifnull(round(retain_4*100/add_user_count,2),0),'%')  retain4\n" +
                ",concat(ifnull(round(retain_5*100/add_user_count,2),0),'%')  retain5\n" +
                ",concat(ifnull(round(retain_6*100/add_user_count,2),0),'%')  retain6\n" +
                ",concat(ifnull(round(retain_7*100/add_user_count,2),0),'%')  retain7\n" +
                ",concat(ifnull(round(retain_15*100/add_user_count,2),0),'%')   retain15\n" +
                ",concat(ifnull(round(retain_30*100 /add_user_count,2),0) ,'%') retain30\n" +
                ",ifnull(round(add_ad_show_count /add_ad_show_user_count,2),0)  addAdShowCountEachAddAdShowEserCount\n" +
                ",ifnull(round(ad_show_count /ad_show_user_count,2),0)  adShowCountEachAdShowEserCount\n" +
                ",api_revenue as apiRevenue\n" +
                ",api_revenue_all as  apiRevenueAll\n" +
                ",revenue as  revenue\n" +
                ",if(date(now())=data_date ,api_revenue_all+revenue,api_revenue_all) as  apiRevenueRevenueAll\n" +
                ",api_imp_cnt as apiImpCnt\n" +
                ",round(api_ecpm,1)  as  ecpm\n" +
                ",ifnull(round(add_ecpm,2),0)  addEcpm\n" +
                ",ifnull(round(api_revenue/active_user_count,2),0)  activeArpu\n" +
                ",ifnull(round(ltv_0 /add_user_count,2),0)   ltv0\n" +
                ",ifnull(round(ltv_1 /add_user_count,2),0)  ltv1\n" +
                ",ifnull(round(ltv_2 /add_user_count,2),0)  ltv2\n" +
                ",ifnull(round(ltv_3 /add_user_count,2),0)  ltv3\n" +
                ",ifnull(round(ltv_4 /add_user_count,2),0)  ltv4\n" +
                ",ifnull(round(ltv_5 /add_user_count,2),0)  ltv5\n" +
                ",ifnull(round(ltv_6 /add_user_count,2),0)  ltv6\n" +
                ",ifnull(round(ltv_7 /add_user_count,2),0)  ltv7\n" +
                ",ifnull(round(ltv_15 /add_user_count,2),0) ltv15\n" +
                ",ifnull(round(ltv_30 /add_user_count,2),0)  ltv30\n" +
                ",concat(ifnull(round(api_revenue * 100/cost,2),0),'%')   ROI\n" +
                ",concat(ifnull(round(api_revenue_all * 100/cost_all,2),0),'%')   ROIAll\n" +
                ",pangle_cost pangleCost\n" +
                ",ifnull(round(pangle_cost/pangle_active,2),0)  pangleCpa\n" +
                ",pangle_active pangleActive\n" +
                ",concat(ifnull(round(pangle_ltv_0 * 100 /pangle_cost,2),0),'%') pangleFirstROI\n" +
                ",site_cost siteCost\n" +
                ",ifnull(round(site_cost/site_active,2),0) siteCpa\n" +
                ",site_active siteActive\n" +
                ",concat(ifnull(round(site_ltv_0 * 100 /site_cost,2),0),'%') siteFirstROI\n" +
                ",cost_all costAll\n" +
                ",active active\n" +
                ",concat(ifnull(round(ltv_0 * 100 /cost,2),0),'%') firstROI\n" +
                "from\n" +
                "doris_boom.app_day_pkg_kpi");
      System.out.println(sql);
    }

}

