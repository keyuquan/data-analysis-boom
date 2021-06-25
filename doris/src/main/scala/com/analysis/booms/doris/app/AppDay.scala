package com.analysis.booms.doris.app

import com.analysis.boom.common.utils.DorisDBUtils

object AppDay {
  def runData(startDay: String, endDay: String): Unit = {
    val sql_app_day_pkg_kpi =
      s"""
         |insert  into app_day_pkg_kpi
         |select
         |t.data_date
         |,t.pkg_code
         |,t.pkg_name
         |,ifnull(add_user_count,0)
         |,ifnull(add_count,0)
         |,ifnull(active_user_count,0)
         |,ifnull(active_count,0)
         |,ifnull(add_ad_show_user_count,0)
         |,ifnull(add_ad_show_count,0)
         |,ifnull(ad_show_user_count ,0)
         |,ifnull(ad_show_count ,0)
         |,ifnull(t_ta.ecpm,0)
         |,ifnull(add_ecpm ,0)
         |,ifnull(earnings ,0)
         |,ifnull(ttzx_earnings ,0)
         |,ifnull(pangle_earnings ,0)
         |,ifnull(site_earnings ,0)
         |,ifnull(earnings_all ,0)
         |,ifnull(ttzx_earnings_all ,0)
         |,ifnull(pangle_earnings_all ,0)
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
         |,ifnull(site_ltv_6 ,0)
         |,ifnull(site_ltv_7,0)
         |,ifnull(site_ltv_15,0)
         |,ifnull(site_ltv_30,0)
         |,ifnull(req_cnt,0)
         |,ifnull(api_req_cnt,0)
         |,ifnull(ret_cnt,0)
         |,ifnull(api_ret_cnt,0)
         |,ifnull(imp_cnt,0)
         |,ifnull(api_imp_cnt,0)
         |,ifnull(clk_cnt,0)
         |,ifnull(api_clk_cnt,0)
         |,ifnull(t_pangle.ecpm,0) pangle_ecpm
         |,ifnull(api_ecpm,0)
         |,ifnull(revenue,0)
         |,ifnull(api_revenue,0)
         |,ifnull(show_count,0)
         |,ifnull(cost ,0)
         |,ifnull(active ,0)
         |,ifnull(active_cost ,0)
         |,ifnull(ttzx_show_count ,0)
         |,ifnull(ttzx_cost ,0)
         |,ifnull(ttzx_active ,0)
         |,ifnull(ttzx_active_cost ,0)
         |,ifnull(pangle_show_count ,0)
         |,ifnull(pangle_cost ,0)
         |,ifnull(pangle_active ,0)
         |,ifnull(pangle_active_cost ,0)
         |,ifnull(site_show_count,0)
         |,ifnull(site_cost ,0)
         |,ifnull(site_active ,0)
         |,ifnull(site_active_cost ,0)
         |,ifnull(show_all_count ,0)
         |,ifnull(cost_all ,0)
         |,ifnull(active_all,0)
         |,ifnull(active_cost_all,0)
         |,ifnull(ttzx_show_all_count,0)
         |,ifnull(ttzx_cost_all,0)
         |,ifnull(ttzx_active_all,0)
         |,ifnull(ttzx_active_cost_all,0)
         |,ifnull(pangle_show_all_count,0)
         |,ifnull(pangle_cost_all,0)
         |,ifnull(pangle_active_all,0)
         |,ifnull(pangle_active_cost_all,0)
         |,ifnull(site_show_all_count,0)
         |,ifnull(site_cost_all,0)
         |,ifnull(site_active_all,0)
         |,ifnull(site_active_cost_all,0)
         |,now() update_time
         |  from
         |(
         |  select t_d.data_date,t_c.pkg_code,t_c.pkg_name,t_c.pangle_app_id  from
         |  (
         |  select  data_date from  dws_ta_day_pkg_kpi where  data_date  between  '$startDay' AND '$endDay'
         |  union
         |  select  data_date from  dws_pangle_day_site_kpi  where  data_date  between  '$startDay' AND '$endDay'
         |  union
         |  select  data_date from  dws_ocean_day_pkg_kpi where  data_date  between  '$startDay' AND '$endDay'
         |  ) t_d, app_pkg_conf  t_c
         |)  t
         |left  join  (select  *  from dws_ta_day_pkg_kpi where  data_date  between  '$startDay' AND '$endDay') t_ta on   t.data_date=t_ta.data_date and  t.pkg_code=t_ta.pkg_code
         |left  join  (select  *  from dws_pangle_day_site_kpi where  data_date  between  '$startDay' AND '$endDay') t_pangle on   t.data_date=t_pangle.data_date and  t.pangle_app_id=t_pangle.site_id
         |left  join  (select  *  from dws_ocean_day_pkg_kpi where   data_date  between  '$startDay' AND '$endDay') t_ocean on   t.data_date=t_ocean.data_date and  t.pkg_code=t_ocean.pkg_code
         |""".stripMargin

    val conn = DorisDBUtils.getConnection;
    DorisDBUtils.execute(conn, "use doris_boom", "use doris_boom");
    DorisDBUtils.execute(conn, "sql_app_day_pkg_kpi", sql_app_day_pkg_kpi);
    DorisDBUtils.close();
  }
}
