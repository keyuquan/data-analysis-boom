package com.analysis.boom.jobs.ocean.dao;

import com.analysis.boom.jobs.ocean.entity.AdPlanConfEntity;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AdPlanConfDao {
    /**
     * 批量
     *
     * @param conn
     * @param list
     * @throws SQLException
     */
    public static void batch(Connection conn, List<AdPlanConfEntity.DataDTO.ListDTO> list) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "insert  into ocean_ad_plan_conf(ad_id,name,advertiser_id,campaign_id,status,ad_create_time,modify_time,ad_modify_time) values(?,?,?,?,?,?,?,?) ON DUPLICATE KEY UPDATE name=?,advertiser_id=?,campaign_id=?,status=?,ad_create_time=?,modify_time=?,ad_modify_time=? ";
        Object[][] arr = new Object[list.size()][15];
        for (int i = 0; i < list.size(); i++) {
            AdPlanConfEntity.DataDTO.ListDTO listDTO = list.get(i);
            arr[i][0] = listDTO.getId();
            arr[i][1] = listDTO.getName();
            arr[i][2] = listDTO.getAdvertiserId();
            arr[i][3] = listDTO.getCampaignId();
            arr[i][4] = listDTO.getStatus();
            arr[i][5] = listDTO.getAdCreateTime();
            arr[i][6] = listDTO.getModifyTime();
            arr[i][7] = listDTO.getAdModifyTime();
            arr[i][8] = listDTO.getName();
            arr[i][9] = listDTO.getAdvertiserId();
            arr[i][10] = listDTO.getCampaignId();
            arr[i][11] = listDTO.getStatus();
            arr[i][12] = listDTO.getAdCreateTime();
            arr[i][13] = listDTO.getModifyTime();
            arr[i][14] = listDTO.getAdModifyTime();
        }
        queryRunner.batch(conn, sql, arr);

    }
}
