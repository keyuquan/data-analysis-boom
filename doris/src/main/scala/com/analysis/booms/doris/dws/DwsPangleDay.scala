package com.analysis.booms.doris.dws

import com.analysis.boom.common.utils.DorisDBUtils

/**
 * 穿山甲天维度数据聚合
 */
object DwsPangleDay {
  def runData(startDay: String, endDay: String): Unit = {

    val sql_dws_pangle_day_pkg_kpi=
      s"""
        |insert  into dws_pangle_day_pkg_kpi
        |select  *,now()  update_time  from (
        |select  start_date, site_id, req_cnt, api_req_cnt, ret_cnt, api_ret_cnt, imp_cnt, api_imp_cnt, clk_cnt, api_clk_cnt, ecpm, api_ecpm, revenue, api_revenue
        |,sum(api_revenue) over( partition by site_id order by start_date  )  api_revenue_all
        |from  dwm_pangle_day_pkg_kpi
        |)   t     where  start_date between  '$startDay' AND '$endDay'
        |""".stripMargin

    val conn = DorisDBUtils.getConnection;
    DorisDBUtils.execute(conn, "use doris_boom", "use doris_boom");
    DorisDBUtils.execute(conn, "sql_dws_pangle_day_pkg_kpi", sql_dws_pangle_day_pkg_kpi);
    DorisDBUtils.close();
  }


}
