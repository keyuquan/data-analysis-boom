package com.analysis.booms.doris.dws

import com.analysis.boom.common.utils.DorisDBUtils

object DwsOceanDay {

  def runData(startDay: String, endDay: String): Unit = {
    val sql_dws_ocean_day_pkg_plan_kpi =
      s"""
         |insert into dws_ocean_day_pkg_plan_kpi
         |select
         |t.stat_datetime data_date
         |,t_f.package pkg_code
         |,t.ad_id plan_id
         |,t_f.campaign_id
         |,CASE WHEN find_in_set('INVENTORY_UNIVERSAL', t_f.inventory_type)>0 THEN  1
         |WHEN find_in_set('UNION_BOUTIQUE_GAME', t_f.inventory_type)>0 or find_in_set('INVENTORY_UNION_SLOT', t_f.inventory_type)>0 or find_in_set('INVENTORY_UNION_SPLASH_SLOT', t_f.inventory_type)>0  THEN 2
         |WHEN  t_f.inventory_type is not  null   THEN 3
         |ELSE 4  END classify
         |,t.cost
         |,t.`show`  show_count
         |,t.avg_show_cost
         |,t.click
         |,t.avg_click_cost
         |,t.ctr
         |,t.convert
         |,t.convert_cost
         |,t.convert_rate
         |,t.deep_convert
         |,t.deep_convert_cost
         |,t.deep_convert_rate
         |,t.attribution_convert
         |,t.attribution_convert_cost
         |,t.attribution_deep_convert
         |,t.attribution_deep_convert_cost
         |,t.download_start
         |,t.download_start_cost
         |,t.download_start_rate
         |,t.download_finish
         |,t.download_finish_cost
         |,t.download_finish_rate
         |,t.click_install
         |,t.install_finish
         |,t.install_finish_cost
         |,t.install_finish_rate
         |,t.active, active_cost
         |,t.active_rate
         |,t.register
         |,t.active_register_cost
         |,t.active_register_rate
         |,t.update_time
         |from
         |dwm_ocean_day_ad_plan_kpi t
         |left join ods_ocean_ad_plan_conf t_f on  t.ad_id=t_f.ad_id
         |""".stripMargin

    val sql_dws_ocean_day_pkg_kpi =
      s"""
         |insert into  dws_ocean_day_pkg_kpi
         |select
         |data_date
         |,pkg_code
         |,show_count
         |,cost
         |,active
         |,ttzx_show_count
         |,ttzx_cost
         |,ttzx_active
         |,pangle_show_count
         |,pangle_cost
         |,pangle_active
         |,site_show_count
         |,site_cost
         |,site_active
         |,sum(show_count)  over( partition by pkg_code order by data_date  )  show_all_count
         |,sum(cost)   over( partition by pkg_code order by data_date  )  cost_all
         |,sum(active)   over( partition by pkg_code order by data_date  )  active_all
         |,sum(ttzx_show_count)  over( partition by pkg_code order by data_date  ) ttzx_show_all_count
         |,sum(ttzx_cost)  over( partition by pkg_code order by data_date  )  ttzx_cost_all
         |,sum(ttzx_active)  over( partition by pkg_code order by data_date  )  ttzx_active_all
         |,sum(pangle_show_count)  over( partition by pkg_code order by data_date  )  pangle_show_all_count
         |,sum(pangle_cost)  over( partition by pkg_code order by data_date  )  pangle_cost_all
         |,sum(pangle_active)  over( partition by pkg_code order by data_date  )   pangle_active_all
         |,sum(site_show_count)  over( partition by pkg_code order by data_date  )  site_show_all_count
         |,sum(site_cost)  over( partition by pkg_code order by data_date  )  site_cost_all
         |,sum(site_active)  over( partition by pkg_code order by data_date  )  site_active_all
         |,now() update_time
         |from
         |(
         |select
         |data_date
         |,pkg_code
         |,sum(show_count) show_count
         |,sum(cost)   cost
         |,sum(active)  active
         |,sum(if(classify=1,show_count,0)) ttzx_show_count
         |,sum(if(classify=1,cost,0)) ttzx_cost
         |,sum(if(classify=1,active,0)) ttzx_active
         |,sum(if(classify=2,show_count,0)) pangle_show_count
         |,sum(if(classify=2,cost,0)) pangle_cost
         |,sum(if(classify=2,active,0)) pangle_active
         |,sum(if(classify=3,show_count,0)) site_show_count
         |,sum(if(classify=3,cost,0)) site_cost
         |,sum(if(classify=3,active,0)) site_active
         |from
         |dws_ocean_day_pkg_plan_kpi
         |group  by  data_date,pkg_code
         |) t
         |""".stripMargin

    val conn = DorisDBUtils.getConnection;
    DorisDBUtils.execute(conn, "use doris_boom", "use doris_boom");
    DorisDBUtils.execute(conn, "sql_dws_ocean_day_pkg_plan_kpi", sql_dws_ocean_day_pkg_plan_kpi);
    DorisDBUtils.execute(conn, "sql_dws_ocean_day_pkg_kpi", sql_dws_ocean_day_pkg_kpi);
    DorisDBUtils.close();
  }


}
