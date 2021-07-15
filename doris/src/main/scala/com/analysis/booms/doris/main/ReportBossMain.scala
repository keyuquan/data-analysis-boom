package com.analysis.booms.doris.main

import com.analysis.boom.common.utils.{DateUtils, DorisDBUtils}
import com.analysis.booms.doris.utils.ExcelUtils
import java.util
import java.util.Map

object ReportBossMain {

  def main(args: Array[String]): Unit = {
    val startDay = DateUtils.getStartDay(-1)
    val endDay = DateUtils.getStartDay(-1)
    val conn = DorisDBUtils.getConnection
    val path = "./report_data/"

    val sql =
      s"""
         |select
         |data_date  as  '日期'
         |,pkg_code as  '包名'
         |,pkg_name as  '应用名'
         |,(api_revenue+0-cost) as  '投放利润'
         |,0 as  '付费api回收'
         |,api_revenue as  '广告api回收'
         |,(api_revenue+0) as  '总回收'
         |,cost as  '投放消耗'
         |,active as  '投放新增'
         |,ifnull(round(cost / active,2),0) as  '投放CPA'
         |,ifnull(round(ltv_0 / add_user_count,2),0) as  '投放预估首日回收'
         |,ifnull(round(ltv_0 / cost,2),0) as  '投放首日ROI'
         |,ifnull(round(ltv_0 / active,2),0) as  '投放首日ARPU'
         |,add_ad_show_count as  '首日广告次数'
         |,ifnull(round(add_ecpm,2),0)  as  '首日ecpm'
         |,active_user_count as  'DAU'
         |,add_user_count as  '新增用户数'
         |,ifnull(round(earnings / active_user_count,2),0) as  '活跃ARPU'
         |,(add_user_count-0) as  '新增用户数-自然量'
         |,concat(ifnull(round(add_user_count*100 /active_user_count,2),0) ,'%')   '新用户占比(总新增）'
         |,(api_revenue_all-cost_all) as  '累计利润'
         |,api_revenue_all as  '累计广告收入'
         |,0 as  '累积内购收入'
         |,cost_all as  '累计消耗'
         |from
         |doris_boom.app_day_pkg_kpi
         |where   data_date BETWEEN '$startDay' and   '$endDay'
         |ORDER BY pkg_code desc, data_date
         |""".stripMargin

    val list: util.List[String] = DorisDBUtils.query(conn, sql)
    val mapData: Map[String, util.List[String]] = new util.HashMap[String, util.List[String]]
    val pkgName = "全部游戏"
    mapData.put(pkgName, list)
    val fileName = "公司总表_" + pkgName + "_" + endDay + ".xls"
    ExcelUtils.writerExcelFile(path + fileName, mapData)

  }
}
