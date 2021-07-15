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
         |,ifnull(t_ta.add_user_count,0) add_user_count
         |,ifnull(t_ta.add_count,0) add_count
         |,ifnull(t_ta.active_user_count,0) active_user_count
         |,ifnull(t_ta.active_count,0) active_count
         |,ifnull(t_ta.add_ad_show_user_count,0) add_ad_show_user_count
         |,ifnull(t_ta.add_ad_show_count,0)   add_ad_show_count
         |,ifnull(t_ta.ad_show_user_count ,0) ad_show_user_count
         |,ifnull(t_ta.ad_show_count ,0) ad_show_count
         |,ifnull(t_ta.ecpm,0)  ecpm
         |,ifnull(t_ta.add_ecpm ,0) add_ecpm
         |,ifnull(t_ta.earnings ,0) earnings
         |,ifnull(t_ta.ttzx_earnings ,0) ttzx_earnings
         |,ifnull(t_ta.pangle_earnings ,0) pangle_earnings
         |,ifnull(t_ta.site_earnings ,0) site_earnings
         |,ifnull(t_ta.earnings_all ,0)  earnings_all
         |,ifnull(t_ta.ttzx_earnings_all ,0)  ttzx_earnings_all
         |,ifnull(t_ta.pangle_earnings_all ,0)  pangle_earnings_all
         |,ifnull(t_ta.site_earnings_all,0) site_earnings_all
         |,ifnull(t_ta.retain_1,0)  retain_1
         |,ifnull(t_ta.retain_2,0)  retain_2
         |,ifnull(t_ta.retain_3,0)  retain_3
         |,ifnull(t_ta.retain_4,0)  retain_4
         |,ifnull(t_ta.retain_5,0)  retain_5
         |,ifnull(t_ta.retain_6,0)  retain_6
         |,ifnull(t_ta.retain_7,0)  retain_7
         |,ifnull(t_ta.retain_15,0)  retain_15
         |,ifnull(t_ta.retain_30,0)  retain_30
         |,ifnull(t_ta.ltv_0,0)  ltv_0
         |,ifnull(t_ta.ltv_1,0) ltv_1
         |,ifnull(t_ta.ltv_2,0) ltv_2
         |,ifnull(t_ta.ltv_3,0)  ltv_3
         |,ifnull(t_ta.ltv_4,0)  ltv_4
         |,ifnull(t_ta.ltv_5,0) ltv_5
         |,ifnull(t_ta.ltv_6,0)  ltv_6
         |,ifnull(t_ta.ltv_7,0) ltv_7
         |,ifnull(t_ta.ltv_15,0) ltv_15
         |,ifnull(t_ta.ltv_30,0) ltv_30
         |,ifnull(t_ta.ttzx_retain_1,0) ttzx_retain_1
         |,ifnull(t_ta.ttzx_retain_2,0) ttzx_retain_2
         |,ifnull(t_ta.ttzx_retain_3,0) ttzx_retain_3
         |,ifnull(t_ta.ttzx_retain_4,0) ttzx_retain_4
         |,ifnull(t_ta.ttzx_retain_5,0) ttzx_retain_5
         |,ifnull(t_ta.ttzx_retain_6,0) ttzx_retain_6
         |,ifnull(t_ta.ttzx_retain_7,0) ttzx_retain_7
         |,ifnull(t_ta.ttzx_retain_15,0) ttzx_retain_15
         |,ifnull(t_ta.ttzx_retain_30,0) ttzx_retain_30
         |,ifnull(t_ta.ttzx_ltv_0,0) ttzx_ltv_0
         |,ifnull(t_ta.ttzx_ltv_1,0) ttzx_ltv_1
         |,ifnull(t_ta.ttzx_ltv_2,0) ttzx_ltv_2
         |,ifnull(t_ta.ttzx_ltv_3,0) ttzx_ltv_3
         |,ifnull(t_ta.ttzx_ltv_4,0) ttzx_ltv_4
         |,ifnull(t_ta.ttzx_ltv_5,0) ttzx_ltv_5
         |,ifnull(t_ta.ttzx_ltv_6,0) ttzx_ltv_6
         |,ifnull(t_ta.ttzx_ltv_7,0) ttzx_ltv_7
         |,ifnull(t_ta.ttzx_ltv_15,0) ttzx_ltv_15
         |,ifnull(t_ta.ttzx_ltv_30,0) ttzx_ltv_30
         |,ifnull(t_ta.pangle_retain_1,0)  pangle_retain_1
         |,ifnull(t_ta.pangle_retain_2,0) pangle_retain_2
         |,ifnull(t_ta.pangle_retain_3,0) pangle_retain_3
         |,ifnull(t_ta.pangle_retain_4,0) pangle_retain_4
         |,ifnull(t_ta.pangle_retain_5,0) pangle_retain_5
         |,ifnull(t_ta.pangle_retain_6,0) pangle_retain_6
         |,ifnull(t_ta.pangle_retain_7,0) pangle_retain_7
         |,ifnull(t_ta.pangle_retain_15,0) pangle_retain_15
         |,ifnull(t_ta.pangle_retain_30,0) pangle_retain_30
         |,ifnull(t_ta.pangle_ltv_0,0) pangle_ltv_0
         |,ifnull(t_ta.pangle_ltv_1,0) pangle_ltv_1
         |,ifnull(t_ta.pangle_ltv_2,0) pangle_ltv_2
         |,ifnull(t_ta.pangle_ltv_3,0) pangle_ltv_3
         |,ifnull(t_ta.pangle_ltv_4,0) pangle_ltv_4
         |,ifnull(t_ta.pangle_ltv_5,0) pangle_ltv_5
         |,ifnull(t_ta.pangle_ltv_6,0) pangle_ltv_6
         |,ifnull(t_ta.pangle_ltv_7,0) pangle_ltv_7
         |,ifnull(t_ta.pangle_ltv_15,0) pangle_ltv_15
         |,ifnull(t_ta.pangle_ltv_30,0) pangle_ltv_30
         |,ifnull(t_ta.site_retain_1,0) site_retain_1
         |,ifnull(t_ta.site_retain_2,0) site_retain_2
         |,ifnull(t_ta.site_retain_3,0) site_retain_3
         |,ifnull(t_ta.site_retain_4,0) site_retain_4
         |,ifnull(t_ta.site_retain_5,0) site_retain_5
         |,ifnull(t_ta.site_retain_6,0) site_retain_6
         |,ifnull(t_ta.site_retain_7,0) site_retain_7
         |,ifnull(t_ta.site_retain_15,0) site_retain_15
         |,ifnull(t_ta.site_retain_30,0) site_retain_30
         |,ifnull(t_ta.site_ltv_0,0) site_ltv_0
         |,ifnull(t_ta.site_ltv_1,0) site_ltv_1
         |,ifnull(t_ta.site_ltv_2,0) site_ltv_2
         |,ifnull(t_ta.site_ltv_3,0) site_ltv_3
         |,ifnull(t_ta.site_ltv_4,0) site_ltv_4
         |,ifnull(t_ta.site_ltv_5,0) site_ltv_5
         |,ifnull(t_ta.site_ltv_6 ,0) site_ltv_6
         |,ifnull(t_ta.site_ltv_7,0) site_ltv_7
         |,ifnull(t_ta.site_ltv_15,0) site_ltv_15
         |,ifnull(t_ta.site_ltv_30,0) site_ltv_30
         |,ifnull(t_pangle.req_cnt,0) req_cnt
         |,ifnull(t_pangle.api_req_cnt,0) api_req_cnt
         |,ifnull(t_pangle.ret_cnt,0)  ret_cnt
         |,ifnull(t_pangle.api_ret_cnt,0) api_ret_cnt
         |,ifnull(t_pangle.imp_cnt,0) imp_cnt
         |,ifnull(t_pangle.api_imp_cnt,0)  api_imp_cnt
         |,ifnull(t_pangle.clk_cnt,0)  clk_cnt
         |,ifnull(t_pangle.api_clk_cnt,0) api_clk_cnt
         |,ifnull(t_pangle.ecpm,0) pangle_ecpm
         |,ifnull(t_pangle.api_ecpm,0) api_ecpm
         |,ifnull(t_pangle.revenue,0) revenue
         |,ifnull(t_pangle.api_revenue,0) api_revenue
         |,ifnull(t_pangle.api_revenue_all,0) api_revenue_all
         |,ifnull(t_ocean.show_count,0) show_count
         |,ifnull(t_ocean.cost ,0) cost
         |,ifnull(t_ocean.active ,0) active
         |,ifnull(t_ocean.ttzx_show_count ,0) ttzx_show_count
         |,ifnull(t_ocean.ttzx_cost ,0) ttzx_cost
         |,ifnull(t_ocean.ttzx_active ,0) ttzx_active
         |,ifnull(t_ocean.pangle_show_count ,0) pangle_show_count
         |,ifnull(t_ocean.pangle_cost ,0)  pangle_cost
         |,ifnull(t_ocean.pangle_active ,0) pangle_active
         |,ifnull(t_ocean.site_show_count,0) site_show_count
         |,ifnull(t_ocean.site_cost ,0)  site_cost
         |,ifnull(t_ocean.site_active ,0) site_active
         |,ifnull(t_ocean.show_all_count ,0) show_all_count
         |,ifnull(t_ocean.cost_all ,0) cost_all
         |,ifnull(t_ocean.active_all,0) active_all
         |,ifnull(t_ocean.ttzx_show_all_count,0) ttzx_show_all_count
         |,ifnull(t_ocean.ttzx_cost_all,0) ttzx_cost_all
         |,ifnull(t_ocean.ttzx_active_all,0) ttzx_active_all
         |,ifnull(t_ocean.pangle_show_all_count,0)  pangle_show_all_count
         |,ifnull(t_ocean.pangle_cost_all,0)  pangle_cost_all
         |,ifnull(t_ocean.pangle_active_all,0)  pangle_active_all
         |,ifnull(t_ocean.site_show_all_count,0) site_show_all_count
         |,ifnull(t_ocean.site_cost_all,0) site_cost_all
         |,ifnull(t_ocean.site_active_all,0) site_active_all
         |,now() update_time
         |  from
         |(
         |  select t_d.data_date,t_c.pkg_code,t_c.pkg_name,t_c.pangle_app_id  from
         |  (
         |  select  data_date from  dws_ta_day_pkg_kpi where  data_date  between  '$startDay' AND '$endDay'
         |  union
         |  select  data_date from  dws_pangle_day_pkg_kpi  where  data_date  between  '$startDay' AND '$endDay'
         |  union
         |  select  data_date from  dws_ocean_day_pkg_kpi where  data_date  between  '$startDay' AND '$endDay'
         |  ) t_d, app_pkg_conf  t_c
         |)  t
         |left  join  (select  *  from dws_ta_day_pkg_kpi where  data_date  between  '$startDay' AND '$endDay') t_ta on   t.data_date=t_ta.data_date and  t.pkg_code=t_ta.pkg_code
         |left  join  (select  *  from dws_pangle_day_pkg_kpi where  data_date  between  '$startDay' AND '$endDay') t_pangle on   t.data_date=t_pangle.data_date and  t.pkg_code=t_pangle.pkg_code
         |left  join  (select  *  from dws_ocean_day_pkg_kpi where   data_date  between  '$startDay' AND '$endDay') t_ocean on   t.data_date=t_ocean.data_date and  t.pkg_code=t_ocean.pkg_code
         |""".stripMargin

    val conn = DorisDBUtils.getConnection;
    DorisDBUtils.execute(conn, "use doris_boom", "use doris_boom");
    DorisDBUtils.execute(conn, "sql_app_day_pkg_kpi", sql_app_day_pkg_kpi);
    DorisDBUtils.close();
  }
}
