package com.analysis.boom.jobs.ocean.dao;

import com.analysis.boom.jobs.ocean.entity.AdPlanConfEntity;
import com.analysis.boom.jobs.ocean.entity.AdPlanDataEntity;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AdPlanDataDao {
    /**
     * 批量
     *
     * @param conn
     * @param list
     * @throws SQLException
     */
    public static void batch(Connection conn, List<AdPlanDataEntity.DataDTO.ListDTO> list) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "insert  into ocean_ad_plan_data_day_ad_kpi(stat_datetime,ad_id,ad_name,`cost`,`show`,`click`,ctr,avg_show_cost,`convert`,convert_cost,convert_rate,`attribution_convert`,active) " +
                " values (?,?,?,?,?,?,?,?,?,?,?,?,?) " +
                "ON DUPLICATE KEY UPDATE " +
                "ad_name=?," +
                "cost=?," +
                "`show`=?," +
                "click=?," +
                "ctr=?," +
                "avg_show_cost=?," +
                "`convert`=?," +
                "convert_cost=?," +
                "convert_rate=?," +
                "`attribution_convert`=?," +
                "active=?";
        Object[][] arr = new Object[list.size()][24];
        for (int i = 0; i < list.size(); i++) {
            AdPlanDataEntity.DataDTO.ListDTO listDTO = list.get(i);
            arr[i][0] = listDTO.getStatDatetime();
            arr[i][1] = listDTO.getAdId();
            arr[i][2] = listDTO.getAdName();
            arr[i][3] = listDTO.getCost();
            arr[i][4] = listDTO.getShow();
            arr[i][5] = listDTO.getClick();
            arr[i][6] = listDTO.getCtr();
            arr[i][7] = listDTO.getAvgShowCost();
            arr[i][8] = listDTO.getConvert();
            arr[i][9] = listDTO.getConvertCost();
            arr[i][10] = listDTO.getConvertRate();
            arr[i][11] = listDTO.getAttributionConvert();
            arr[i][12] = listDTO.getActive();
            //  update
            arr[i][13] = listDTO.getAdName();
            arr[i][14] = listDTO.getCost();
            arr[i][16] = listDTO.getShow();
            arr[i][16] = listDTO.getClick();
            arr[i][17] = listDTO.getCtr();
            arr[i][18] = listDTO.getAvgShowCost();
            arr[i][19] = listDTO.getConvert();
            arr[i][20] = listDTO.getConvertCost();
            arr[i][21] = listDTO.getConvertRate();
            arr[i][22] = listDTO.getAttributionConvert();
            arr[i][23] = listDTO.getActive();
        }
        queryRunner.batch(conn, sql, arr);
    }
}
