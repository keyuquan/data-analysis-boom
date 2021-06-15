package com.analysis.boom.jobs.ta.main;

import com.alibaba.fastjson.JSONObject;
import com.analysis.boom.jobs.ta.utils.PrestoUtils;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaDataMain {

    public static void main(String[] args) throws SQLException {
        Connection conn = PrestoUtils.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select *    from ta.v_user_2");
        List<String> list = new ArrayList();
        ResultSetMetaData md = rs.getMetaData();
        int columnCount = md.getColumnCount();
        while (rs.next()) {
            Map<String, Object> rowData = new HashMap();
            for (int i = 1; i <= columnCount; i++) {
                rowData.put(md.getColumnName(i), rs.getObject(i));
            }
            System.out.println(JSONObject.toJSONString(rowData));
            list.add(JSONObject.toJSONString(rowData));
        }
        rs.close();
        PrestoUtils.close(conn);
    }


}
