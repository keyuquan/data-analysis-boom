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
        String sql = "insert  into ocean_ad_plan_conf(ad_id,name,advertiser_id,campaign_id,status,ad_create_time,modify_time,ad_modify_time) values(?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE name=?,advertiser_id=?,campaign_id=?,status=?,ad_create_time=?,modify_time=?,ad_modify_time=? ";
        Object[][] arr = new Object[list.size()][15];
        for (int i = 0; i < list.size(); i++) {
            AdPlanDataEntity.DataDTO.ListDTO listDTO = list.get(i);

        }
//        queryRunner.batch(conn, sql, arr);

    }
}
