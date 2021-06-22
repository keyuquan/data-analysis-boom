package com.analysis.booms.doris.dws

import com.analysis.boom.common.utils.DorisDBUtils

object DwsDay {
  def runData(startDay: String, endDay: String): Unit = {

    val sql_dws_ta_day_pkg_kpi =
      s"""
         |insert into dws_ta_day_pkg_kpi
         |select t.data_date, t.pkg_code,
         |ifnull(add_user_count,0)
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
         |, now()
         |from
         |(
         |select data_date, pkg_code from dws_ta_event_day_pkg_kpi  where  data_date between  '$startDay' AND '$endDay'
         |union
         |select data_date, pkg_code from dws_ta_event_day_pkg_retain   where  data_date between  '$startDay' AND '$endDay'
         |)  t
         |left join  (select * from dws_ta_event_day_pkg_kpi  where  data_date between  '$startDay' AND '$endDay' ) t1 on  t1.data_date=t.data_date and t1.pkg_code=t.pkg_code
         |left join  (select * from dws_ta_event_day_pkg_retain  where  data_date between  '$startDay' AND '$endDay' ) t2 on  t2.data_date=t.data_date and t2.pkg_code=t.pkg_code
         |""".stripMargin

    val conn = DorisDBUtils.getConnection;
    DorisDBUtils.execute(conn, "use doris_boom", "use doris_boom");
    DorisDBUtils.execute(conn, "sql_dws_ta_day_pkg_kpi", sql_dws_ta_day_pkg_kpi);
    DorisDBUtils.close();
  }

}
