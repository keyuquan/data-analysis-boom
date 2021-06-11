package com.analysis.boom.jobs.ocean.dao;

import com.analysis.boom.jobs.ocean.entity.AdPlanConfEntity;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;

import java.sql.SQLException;
import java.util.List;

public class AdPlanConfDao {
    /**
     * 批量
     * @param datasource
     * @param list
     * @throws SQLException
     */
    public static void batch(BasicDataSource datasource, List<AdPlanConfEntity> list) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(datasource);
        Object[][] arr = new Object[0][];
        String sql="insert   into ";
        queryRunner.batch("", arr);

    }
}
