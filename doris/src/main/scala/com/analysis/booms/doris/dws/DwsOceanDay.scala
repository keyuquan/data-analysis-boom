package com.analysis.booms.doris.dws

import com.analysis.boom.common.utils.DorisDBUtils

object DwsOceanDay {

  def runData(startDay: String, endDay: String): Unit = {

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
         |stat_datetime data_date
         |,package pkg_code
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
         |(
         |    select
         |    stat_datetime
         |    ,package
         |    ,ad_id
         |    ,classify
         |    ,show_count show_count
         |    ,cost
         |    ,active
         |    -- ,sum(t.show_count) over( partition by package,ad_id order by stat_datetime  )  show_all
         |    -- ,sum(t.cost) over( partition by   package,ad_id order by stat_datetime  )  cost_all
         |    -- ,sum(t.active) over( partition by  package,ad_id  order by stat_datetime  )  active_all
         |    from
         |    (
         |       select
         |       t.stat_datetime
         |       ,t_f.package
         |       ,t.ad_id
         |       ,CASE WHEN find_in_set('INVENTORY_UNIVERSAL', t_f.inventory_type)>0 THEN  1
         |       WHEN find_in_set('UNION_BOUTIQUE_GAME', t_f.inventory_type)>0 or find_in_set('INVENTORY_UNION_SLOT', t_f.inventory_type)>0 or find_in_set('INVENTORY_UNION_SPLASH_SLOT', t_f.inventory_type)>0  THEN 2
         |       WHEN  t_f.inventory_type is not  null   THEN 3
         |       ELSE 4  END classify
         |       ,t.`show`  show_count
         |       ,t.cost
         |       ,t.active
         |       from
         |       dwm_ocean_day_ad_plan_kpi t
         |       join ods_ocean_ad_plan_conf t_f on  t.ad_id=t_f.ad_id
         |    )  t
         |) t
         |group  by  stat_datetime,package
         |) t
         |""".stripMargin

    val conn = DorisDBUtils.getConnection;
    DorisDBUtils.execute(conn, "use doris_boom", "use doris_boom");
    DorisDBUtils.execute(conn, "sql_dws_ocean_day_pkg_kpi", sql_dws_ocean_day_pkg_kpi);
    DorisDBUtils.close();
  }


}
