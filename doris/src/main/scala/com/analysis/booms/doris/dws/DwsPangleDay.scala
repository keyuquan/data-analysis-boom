package com.analysis.booms.doris.dws

import com.analysis.boom.common.utils.DorisDBUtils

/**
 * 穿山甲天维度数据聚合
 */
object DwsPangleDay {
  def runData(startDay: String, endDay: String): Unit = {

    val sql_dws_pangle_day_pkg_kpi =
      s"""
         |insert into dws_pangle_day_pkg_kpi
         |select
         |data_date
         |,pkg_code
         |,req_cnt
         |,api_req_cnt
         |,ret_cnt
         |,api_ret_cnt
         |,imp_cnt
         |,api_imp_cnt
         |,clk_cnt
         |,api_clk_cnt
         |,ecpm
         |,api_ecpm
         |,revenue
         |,api_revenue
         |,sum(api_revenue) over( partition by pkg_code order by data_date  ) api_revenue_all
         |,now() update_time
         |from
         |(
         |select
         |t.start_date data_date
         |,t_c.pkg_code
         |,sum(req_cnt) req_cnt
         |,sum(api_req_cnt) api_req_cnt
         |,sum(ret_cnt) ret_cnt
         |,sum(api_ret_cnt) api_ret_cnt
         |,sum(imp_cnt) imp_cnt
         |,sum(api_imp_cnt) api_imp_cnt
         |,sum(clk_cnt) clk_cnt
         |,sum(api_clk_cnt) api_clk_cnt
         |,sum(revenue/imp_cnt)*1000 ecpm
         |,sum(api_revenue/api_imp_cnt)*1000 api_ecpm
         |,sum(revenue) revenue
         |,sum(api_revenue) api_revenue
         |from
         |dwm_pangle_day_site_ad_unit_kpi  t
         |join  app_pkg_conf  t_c on   t.site_id=t_c.pangle_app_id
         |where  find_in_set(t.ad_unit_id,concat(t_c.pangle_ad_unit_id,','))>0
         |group  by  t.start_date,t_c.pkg_code
         |) t
         |""".stripMargin

    val conn = DorisDBUtils.getConnection;
    DorisDBUtils.execute(conn, "use doris_boom", "use doris_boom");
    DorisDBUtils.execute(conn, "sql_dws_pangle_day_pkg_kpi", sql_dws_pangle_day_pkg_kpi);
    DorisDBUtils.close();
  }


}
