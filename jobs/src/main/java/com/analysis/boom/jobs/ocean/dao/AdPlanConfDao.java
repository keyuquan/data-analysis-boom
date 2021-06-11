package com.analysis.boom.jobs.ocean.dao;

import com.analysis.boom.jobs.ocean.entity.AdPlanConfEntity;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AdPlanConfDao {
    /**
     * 批量
     * @param conn
     * @param list
     * @throws SQLException
     */
    public static void batch(Connection conn, List<AdPlanConfEntity.DataDTO.ListDTO> list) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql="insert  into ocean_ad_plan_conf(ad_id,name,advertiser_id,campaign_id,status,ad_create_time,modify_time,ad_modify_time) values(?,?,?,?,?,?,?,?)";
        Object[][] arr = new Object[list.size()][8];
        for (int i = 0; i < list.size(); i++) {
            AdPlanConfEntity.DataDTO.ListDTO listDTO = list.get(i);
            arr[i][0]=listDTO.getId();
            arr[i][1]=listDTO.getName();
            arr[i][2]=listDTO.getAdvertiserId();
            arr[i][3]=listDTO.getCampaignId();
            arr[i][4]=listDTO.getStatus();
            arr[i][5]=listDTO.getAdCreateTime();
            arr[i][6]=listDTO.getModifyTime();
            arr[i][7]=listDTO.getAdModifyTime();
        }
        queryRunner.batch(conn,sql, arr);

    }
}
