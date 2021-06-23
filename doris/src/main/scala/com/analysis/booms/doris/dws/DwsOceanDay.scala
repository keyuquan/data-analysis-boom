package com.analysis.booms.doris.dws

import com.analysis.boom.common.utils.DorisDBUtils

object DwsOceanDay {
  def runData(startDay: String, endDay: String): Unit = {

    val sql_dws_ocean_day_pkg_kpi =
      s"""
         |insert into  dws_ocean_day_pkg_kpi
         |select
         |stat_datetime data_date
         |,package pkg_code
         |,sum(`show`) show_count
         |,sum(cost)   cost
         |,sum(active) active
         |,sum(active_cost) active_cost
         |,sum(if(classify=1,`show`,0)) ttzx_show_count
         |,sum(if(classify=1,cost,0)) ttzx_cost
         |,sum(if(classify=1,active,0)) ttzx_active
         |,sum(if(classify=1,active_cost,0)) ttzx_active_cost
         |,sum(if(classify=2,`show`,0)) pangle_show_count
         |,sum(if(classify=2,cost,0)) pangle_cost
         |,sum(if(classify=2,active,0)) pangle_active
         |,sum(if(classify=2,active_cost,0)) pangle_active_cost
         |,sum(if(classify=3,`show`,0)) site_show_count
         |,sum(if(classify=3,cost,0)) site_cost
         |,sum(if(classify=3,active,0)) site_active
         |,sum(if(classify=3,active_cost,0))site_active_cost
         |,sum(show_all) show_all_count
         |,sum(cost_all)   cost_all
         |,sum(active_all) active_all
         |,sum(active_cost_all) active_cost_all
         |,sum(if(classify=1,show_all,0)) ttzx_show_all_count
         |,sum(if(classify=1,cost_all,0)) ttzx_cost_all
         |,sum(if(classify=1,active_all,0)) ttzx_active_all
         |,sum(if(classify=1,active_cost_all,0)) ttzx_active_cost_all
         |,sum(if(classify=2,show_all,0)) pangle_show_all_count
         |,sum(if(classify=2,cost_all,0)) pangle_cost_all
         |,sum(if(classify=2,active_all,0)) pangle_active_all
         |,sum(if(classify=2,active_cost_all,0)) pangle_active_cost_all
         |,sum(if(classify=3,show_all,0)) site_show_all_count
         |,sum(if(classify=3,cost_all,0)) site_cost_all
         |,sum(if(classify=3,active_all,0)) site_active_all
         |,sum(if(classify=3,active_cost_all,0))site_active_cost_all
         |,now() update_time
         |from
         |(
         |   select
         |   t.stat_datetime
         |   ,t_f.package
         |   ,t.ad_id
         |   ,t_i.classify
         |   ,t.`show`
         |   ,t.cost
         |   ,t.active
         |   ,t.active_cost
         |   ,sum(t.`show`) over( partition by  t_f.package,t.ad_id,t_i.classify order by stat_datetime  )  show_all
         |   ,sum(t.cost) over( partition by  t_f.package,t.ad_id,t_i.classify order by stat_datetime  )  cost_all
         |   ,sum(t.active) over( partition by  t_f.package,t.ad_id,t_i.classify order by stat_datetime  )  active_all
         |   ,sum(t.active_cost) over( partition by t_f.package,t.ad_id,t_i.classify order by stat_datetime  )  active_cost_all
         |   from
         |   dwm_ocean_day_ad_plan_inventory_kpi t
         |   join app_inventory_conf t_i  on  t.inventory=t_i.inventory
         |   join ods_ocean_ad_plan_conf t_f on  t.ad_id=t_f.ad_id
         |) t
         |where  stat_datetime between  '$startDay' AND '$endDay'
         |group  by  stat_datetime,package
         |""".stripMargin

    val conn = DorisDBUtils.getConnection;
    DorisDBUtils.execute(conn, "use doris_boom", "use doris_boom");
    DorisDBUtils.execute(conn, "sql_dws_ocean_day_pkg_kpi", sql_dws_ocean_day_pkg_kpi);
    DorisDBUtils.close();
  }

}
