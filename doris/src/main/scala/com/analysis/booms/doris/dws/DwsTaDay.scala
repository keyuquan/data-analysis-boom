package com.analysis.booms.doris.dws

import com.analysis.boom.common.utils.DorisDBUtils

object DwsTaDay {
  def runData(startDay: String, endDay: String): Unit = {
    val sql_dws_ta_day_pkg_plan_kpi =
      s"""
         |insert  into dws_ta_day_pkg_plan_kpi
         |select
         |t1.data_date
         |, t1.pkg_code
         |, t1.plan_id
         |,CASE WHEN find_in_set('INVENTORY_UNIVERSAL', t_f.inventory_type)>0 THEN  1
         |  WHEN find_in_set('UNION_BOUTIQUE_GAME', t_f.inventory_type)>0 or find_in_set('INVENTORY_UNION_SLOT', t_f.inventory_type)>0 or find_in_set('INVENTORY_UNION_SPLASH_SLOT', t_f.inventory_type)>0  THEN 2
         |  ELSE 3  END classify
         |,ifnull(add_user_count,0)
         |,ifnull(add_count,0)
         |,ifnull(active_user_count,0)
         |,ifnull(active_count,0)
         |,ifnull(add_ad_show_user_count,0)
         |,ifnull(add_ad_show_count,0)
         |,ifnull(ad_show_user_count,0)
         |,ifnull(ad_show_count,0)
         |,ifnull(ecpm,0)
         |,ifnull(add_ecpm,0)
         |,ifnull(earnings,0)
         |,ifnull(earnings_all,0)
         |,ifnull(retain_1,0)
         |,ifnull(retain_2,0)
         |,ifnull(retain_3,0)
         |,ifnull(retain_4,0)
         |,ifnull(retain_5,0)
         |,ifnull(retain_6,0)
         |,ifnull(retain_7,0)
         |,ifnull(retain_15,0)
         |,ifnull(retain_30,0)
         |,ifnull(ltv_0,0)
         |,ifnull(ltv_1,0)
         |,ifnull(ltv_2,0)
         |,ifnull(ltv_3,0)
         |,ifnull(ltv_4,0)
         |,ifnull(ltv_5,0)
         |,ifnull(ltv_6,0)
         |,ifnull(ltv_7,0)
         |,ifnull(ltv_15,0)
         |,ifnull(ltv_30,0)
         |,now() update_time
         |from
         |(
         |select data_date, pkg_code,plan_id from dwm_ta_event_day_pkg_plan_kpi  where  data_date between  '$startDay' AND '$endDay'
         |union
         |select data_date, pkg_code,plan_id from dwm_ta_event_day_pkg_plan_retain   where  data_date between  '$startDay' AND '$endDay'
         |) t1
         |join  ods_ocean_ad_plan_conf t_f on  t1.plan_id=t_f.ad_id
         |left  join
         |(
         |  select  data_date, pkg_code,plan_id,
         |  add_user_count, add_count, active_user_count, active_count, add_ad_show_user_count, add_ad_show_count, ad_show_user_count, ad_show_count, ecpm, add_ecpm, earnings
         |  ,sum(earnings) over( partition by pkg_code,plan_id order by data_date  )   earnings_all
         |  from dwm_ta_event_day_pkg_plan_kpi
         |)  t_k  on  t1.data_date=t_k.data_date and t1.pkg_code=t_k.pkg_code and t1.plan_id=t_k.plan_id
         |left join
         |(
         |  select  data_date, pkg_code, plan_id,
         |  retain_1, retain_2, retain_3, retain_4, retain_5, retain_6, retain_7, retain_15, retain_30, ltv_0, ltv_1, ltv_2, ltv_3, ltv_4, ltv_5, ltv_6, ltv_7, ltv_15, ltv_30, update_time
         |  from dwm_ta_event_day_pkg_plan_retain  where  data_date between  '$startDay' AND '$endDay'
         |)  t_r  on  t1.data_date=t_r.data_date and t1.pkg_code=t_r.pkg_code and t1.plan_id=t_r.plan_id
         |""".stripMargin

    val sql_dws_ta_day_pkg_kpi =
      s"""
         |insert into dws_ta_day_pkg_kpi
         |select t.data_date, t.pkg_code
         |,ifnull(add_user_count,0)
         |,ifnull(add_count,0)
         |,ifnull(active_user_count,0)
         |,ifnull(active_count,0)
         |,ifnull(add_ad_show_user_count,0)
         |,ifnull(add_ad_show_count,0)
         |,ifnull(ad_show_user_count,0)
         |,ifnull(ad_show_count,0)
         |,ifnull(ecpm,0)
         |,ifnull(add_ecpm,0)
         |,ifnull(earnings,0)
         |,ifnull(ttzx_earnings,0)
         |,ifnull(pangle_earnings,0)
         |,ifnull(site_earnings,0)
         |,ifnull(earnings_all,0)
         |,ifnull(ttzx_earnings_all,0)
         |,ifnull(pangle_earnings_all,0)
         |,ifnull(site_earnings_all,0)
         |,ifnull(retain_1,0)
         |,ifnull(retain_2,0)
         |,ifnull(retain_3,0)
         |,ifnull(retain_4,0)
         |,ifnull(retain_5,0)
         |,ifnull(retain_6,0)
         |,ifnull(retain_7,0)
         |,ifnull(retain_15,0)
         |,ifnull(retain_30,0)
         |,ifnull(ltv_0,0)
         |,ifnull(ltv_1,0)
         |,ifnull(ltv_2,0)
         |,ifnull(ltv_3,0)
         |,ifnull(ltv_4,0)
         |,ifnull(ltv_5,0)
         |,ifnull(ltv_6,0)
         |,ifnull(ltv_7,0)
         |,ifnull(ltv_15,0)
         |,ifnull(ltv_30,0)
         |,ifnull(ttzx_retain_1,0)
         |,ifnull(ttzx_retain_2,0)
         |,ifnull(ttzx_retain_3,0)
         |,ifnull(ttzx_retain_4,0)
         |,ifnull(ttzx_retain_5,0)
         |,ifnull(ttzx_retain_6,0)
         |,ifnull(ttzx_retain_7,0)
         |,ifnull(ttzx_retain_15,0)
         |,ifnull(ttzx_retain_30,0)
         |,ifnull(ttzx_ltv_0,0)
         |,ifnull(ttzx_ltv_1,0)
         |,ifnull(ttzx_ltv_2,0)
         |,ifnull(ttzx_ltv_3,0)
         |,ifnull(ttzx_ltv_4,0)
         |,ifnull(ttzx_ltv_5,0)
         |,ifnull(ttzx_ltv_6,0)
         |,ifnull(ttzx_ltv_7,0)
         |,ifnull(ttzx_ltv_15,0)
         |,ifnull(ttzx_ltv_30,0)
         |,ifnull(pangle_retain_1,0)
         |,ifnull(pangle_retain_2,0)
         |,ifnull(pangle_retain_3,0)
         |,ifnull(pangle_retain_4,0)
         |,ifnull(pangle_retain_5,0)
         |,ifnull(pangle_retain_6,0)
         |,ifnull(pangle_retain_7,0)
         |,ifnull(pangle_retain_15,0)
         |,ifnull(pangle_retain_30,0)
         |,ifnull(pangle_ltv_0,0)
         |,ifnull(pangle_ltv_1,0)
         |,ifnull(pangle_ltv_2,0)
         |,ifnull(pangle_ltv_3,0)
         |,ifnull(pangle_ltv_4,0)
         |,ifnull(pangle_ltv_5,0)
         |,ifnull(pangle_ltv_6,0)
         |,ifnull(pangle_ltv_7,0)
         |,ifnull(pangle_ltv_15,0)
         |,ifnull(pangle_ltv_30,0)
         |,ifnull(site_retain_1,0)
         |,ifnull(site_retain_2,0)
         |,ifnull(site_retain_3,0)
         |,ifnull(site_retain_4,0)
         |,ifnull(site_retain_5,0)
         |,ifnull(site_retain_6,0)
         |,ifnull(site_retain_7,0)
         |,ifnull(site_retain_15,0)
         |,ifnull(site_retain_30,0)
         |,ifnull(site_ltv_0,0)
         |,ifnull(site_ltv_1,0)
         |,ifnull(site_ltv_2,0)
         |,ifnull(site_ltv_3,0)
         |,ifnull(site_ltv_4,0)
         |,ifnull(site_ltv_5,0)
         |,ifnull(site_ltv_6,0)
         |,ifnull(site_ltv_7,0)
         |,ifnull(site_ltv_15,0)
         |,ifnull(site_ltv_30,0)
         |,now() as  update_time
         |from
         |(
         |select data_date, pkg_code from dwm_ta_event_day_pkg_kpi  where  data_date between  '$startDay' AND '$endDay'
         |union
         |select data_date, pkg_code from dwm_ta_event_day_pkg_retain   where  data_date between  '$startDay' AND '$endDay'
         |union
         |select data_date, pkg_code from dws_ta_day_pkg_plan_kpi   where  data_date between  '$startDay' AND '$endDay'
         |)  t
         |left join  (select *,sum(earnings) over( partition by pkg_code order by data_date  )   earnings_all from dwm_ta_event_day_pkg_kpi  ) t1 on  t1.data_date=t.data_date and t1.pkg_code=t.pkg_code
         |left join  (select * from dwm_ta_event_day_pkg_retain  where  data_date between  '$startDay' AND '$endDay' ) t2 on  t2.data_date=t.data_date and t2.pkg_code=t.pkg_code
         |left  join
         |(
         |select
         |data_date, pkg_code
         |,sum(if(classify=1,earnings,0)) ttzx_earnings
         |,sum(if(classify=1,earnings_all,0)) ttzx_earnings_all
         |,sum(if(classify=1,retain_1,0)) ttzx_retain_1
         |,sum(if(classify=1,retain_2,0)) ttzx_retain_2
         |,sum(if(classify=1,retain_3,0)) ttzx_retain_3
         |,sum(if(classify=1,retain_4,0)) ttzx_retain_4
         |,sum(if(classify=1,retain_5,0)) ttzx_retain_5
         |,sum(if(classify=1,retain_6,0)) ttzx_retain_6
         |,sum(if(classify=1,retain_7,0)) ttzx_retain_7
         |,sum(if(classify=1,retain_15,0)) ttzx_retain_15
         |,sum(if(classify=1,retain_30,0)) ttzx_retain_30
         |,sum(if(classify=1,ltv_0,0)) ttzx_ltv_0
         |,sum(if(classify=1,ltv_1,0)) ttzx_ltv_1
         |,sum(if(classify=1,ltv_2,0)) ttzx_ltv_2
         |,sum(if(classify=1,ltv_3,0)) ttzx_ltv_3
         |,sum(if(classify=1,ltv_4,0)) ttzx_ltv_4
         |,sum(if(classify=1,ltv_5,0)) ttzx_ltv_5
         |,sum(if(classify=1,ltv_6,0)) ttzx_ltv_6
         |,sum(if(classify=1,ltv_7,0)) ttzx_ltv_7
         |,sum(if(classify=1,ltv_15,0)) ttzx_ltv_15
         |,sum(if(classify=1,ltv_30,0)) ttzx_ltv_30
         |,sum(if(classify=2,earnings,0)) pangle_earnings
         |,sum(if(classify=2,earnings_all,0)) pangle_earnings_all
         |,sum(if(classify=2,retain_1,0)) pangle_retain_1
         |,sum(if(classify=2,retain_2,0)) pangle_retain_2
         |,sum(if(classify=2,retain_3,0)) pangle_retain_3
         |,sum(if(classify=2,retain_4,0)) pangle_retain_4
         |,sum(if(classify=2,retain_5,0)) pangle_retain_5
         |,sum(if(classify=2,retain_6,0)) pangle_retain_6
         |,sum(if(classify=2,retain_7,0)) pangle_retain_7
         |,sum(if(classify=2,retain_15,0)) pangle_retain_15
         |,sum(if(classify=2,retain_30,0)) pangle_retain_30
         |,sum(if(classify=2,ltv_0,0)) pangle_ltv_0
         |,sum(if(classify=2,ltv_1,0)) pangle_ltv_1
         |,sum(if(classify=2,ltv_2,0)) pangle_ltv_2
         |,sum(if(classify=2,ltv_3,0)) pangle_ltv_3
         |,sum(if(classify=2,ltv_4,0)) pangle_ltv_4
         |,sum(if(classify=2,ltv_5,0)) pangle_ltv_5
         |,sum(if(classify=2,ltv_6,0)) pangle_ltv_6
         |,sum(if(classify=2,ltv_7,0)) pangle_ltv_7
         |,sum(if(classify=2,ltv_15,0)) pangle_ltv_15
         |,sum(if(classify=2,ltv_30,0)) pangle_ltv_30
         |,sum(if(classify=3,earnings,0)) site_earnings
         |,sum(if(classify=3,earnings_all,0)) site_earnings_all
         |,sum(if(classify=3,retain_1,0)) site_retain_1
         |,sum(if(classify=3,retain_2,0)) site_retain_2
         |,sum(if(classify=3,retain_3,0)) site_retain_3
         |,sum(if(classify=3,retain_4,0)) site_retain_4
         |,sum(if(classify=3,retain_5,0)) site_retain_5
         |,sum(if(classify=3,retain_6,0)) site_retain_6
         |,sum(if(classify=3,retain_7,0)) site_retain_7
         |,sum(if(classify=3,retain_15,0)) site_retain_15
         |,sum(if(classify=3,retain_30,0)) site_retain_30
         |,sum(if(classify=3,ltv_0,0)) site_ltv_0
         |,sum(if(classify=3,ltv_1,0)) site_ltv_1
         |,sum(if(classify=3,ltv_2,0)) site_ltv_2
         |,sum(if(classify=3,ltv_3,0)) site_ltv_3
         |,sum(if(classify=3,ltv_4,0)) site_ltv_4
         |,sum(if(classify=3,ltv_5,0)) site_ltv_5
         |,sum(if(classify=3,ltv_6,0)) site_ltv_6
         |,sum(if(classify=3,ltv_7,0)) site_ltv_7
         |,sum(if(classify=3,ltv_15,0)) site_ltv_15
         |,sum(if(classify=3,ltv_30,0)) site_ltv_30
         |from  dws_ta_day_pkg_plan_kpi
         |where  data_date between  '$startDay' AND '$endDay'
         |group  by  data_date, pkg_code
         |) t3 on  t3.data_date=t.data_date and t3.pkg_code=t.pkg_code
         |""".stripMargin

    val conn = DorisDBUtils.getConnection;
    DorisDBUtils.execute(conn, "use doris_boom", "use doris_boom");
    DorisDBUtils.execute(conn, "sql_dws_ta_day_pkg_plan_kpi", sql_dws_ta_day_pkg_plan_kpi);
    DorisDBUtils.execute(conn, "sql_dws_ta_day_pkg_kpi", sql_dws_ta_day_pkg_kpi);
    DorisDBUtils.close();
  }

}
