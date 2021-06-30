package com.analysis.booms.doris.main

import com.analysis.boom.common.utils.{DateUtils, DorisDBUtils}
import com.analysis.booms.doris.utils.{EmailUtils, ExcelUtils}

import java.util
import java.util.Map

object ReportMain {
  def main(args: Array[String]): Unit = {
    val startDay = DateUtils.getStartDay(-15)
    val endDay = DateUtils.getStartDay(-1)
    val conn = DorisDBUtils.getConnection
    val listConf = DorisDBUtils.queryMap(conn, "select pkg_code,pkg_name,pkg_operator  from  doris_boom.app_pkg_conf   order  by cast(ta_project_id as  int)")
    val mapDataAll: Map[String, util.List[String]] = new util.HashMap[String, util.List[String]]
    val path = "./report_data/"

    listConf.forEach(map => {
      val pkgCode = map.get("pkg_code")
      val pkgName = map.get("pkg_name")
      val pkgOperator = map.get("pkg_operator")
      val sql =
        s"""
           |select
           |data_date  as  '日期'
           |,pkg_code as  '包名'
           |,pkg_name as  '应用名'
           |,add_user_count as  '新增'
           |,active_user_count as  '活跃人数'
           |,concat(ifnull(round(add_user_count*100 /active_user_count,2),0) ,'%')   as '新增占比'
           |,concat(ifnull(round(retain_1*100/add_user_count,2),0),'%')  '次日留存'
           |,concat(ifnull(round(retain_2*100/add_user_count,2),0),'%')  '2日留存'
           |,concat(ifnull(round(retain_3*100/add_user_count,2),0),'%')  '3日留存'
           |,concat(ifnull(round(retain_4*100/add_user_count,2),0),'%')  '4日留存'
           |,concat(ifnull(round(retain_5*100/add_user_count,2),0),'%')  '5日留存'
           |,concat(ifnull(round(retain_6*100/add_user_count,2),0),'%')  '6日留存'
           |,concat(ifnull(round(retain_7*100/add_user_count,2),0),'%')  '7日留存'
           |,concat(ifnull(round(retain_15*100/add_user_count,2),0),'%')   '15日留存'
           |,concat(ifnull(round(retain_30*100 /add_user_count,2),0) ,'%') '30日留存'
           |,ifnull(round(add_ad_show_count /add_ad_show_user_count,2),0)  '新用户人均广告次数'
           |,ifnull(round(ad_show_count /ad_show_user_count,2),0)  '全用户人均广告次数'
           |,api_revenue as  '穿山甲收入'
           |,api_revenue_all as  '昨日累计收入'
           |,revenue as  '今日预计收入'
           |,if(date(now())=data_date ,api_revenue_all+revenue,api_revenue_all) as  '累计收入(昨日累计+今日预计)'
           |,api_imp_cnt as  '穿山甲广告次数'
           |,round(api_ecpm,1)  as  '穿山甲ecpm'
           |,ifnull(round(add_ecpm,2),0)  '数数新用户ecpm'
           |,api_revenue '活跃arpu-收入'
           |,active_user_count '活跃arpu-活跃人数'
           |,ifnull(round(api_revenue/active_user_count,2),0)  '活跃arpu'
           |,ifnull(round(ltv_0 /add_user_count,2),0)  'arpu'
           |,ifnull(round(ltv_1 /add_user_count,2),0)  '1日ltv'
           |,ifnull(round(ltv_2 /add_user_count,2),0)  '2日ltv'
           |,ifnull(round(ltv_3 /add_user_count,2),0)  '3日ltv'
           |,ifnull(round(ltv_4 /add_user_count,2),0)  '4日ltv'
           |,ifnull(round(ltv_5 /add_user_count,2),0)  '5日ltv'
           |,ifnull(round(ltv_6 /add_user_count,2),0)  '6日ltv'
           |,ifnull(round(ltv_7 /add_user_count,2),0)  '7日ltv'
           |,ifnull(round(ltv_15 /add_user_count,2),0)  '15日ltv'
           |,ifnull(round(ltv_30 /add_user_count,2),0)  '30日ltv'
           |,api_revenue '边际ROI-当日回收'
           |,cost '边际ROI-当日消耗'
           |,ifnull(round(api_revenue/cost,2),0)   '边际ROI'
           |,api_revenue_all '累计ROI-累计回收'
           |,cost_all '累计ROI-累计消耗'
           |,ifnull(round(api_revenue_all /cost_all,2),0)   '累计ROI'
           |,pangle_cost '穿山甲消耗'
           |,ifnull(round(pangle_cost/pangle_active,2),0)  '穿山甲CPA'
           |,pangle_active '穿山甲激活用户数'
           |,pangle_ltv_0 '穿山甲首日ROI-首日回收'
           |,pangle_cost '穿山甲首日ROI-消耗'
           |,ifnull(round(pangle_ltv_0 /pangle_cost,2),0) '穿山甲首日ROI'
           |,site_cost '站内消耗'
           |,ifnull(round(site_cost/site_active,2),0) '站内CPA'
           |,site_active '站内激活用户数'
           |,round(site_ltv_0 ,2) '站内首日ROI-首日回收'
           |,ifnull(round(site_ltv_0 /site_cost,2),0) '站内首日ROI'
           |,cost_all '总消耗'
           |,active '激活数'
           |,round(ltv_0,2)  '首日ROI-首日回收'
           |,cost '首日ROI-消耗'
           |,ifnull(round(ltv_0 /cost,2),0) '首日ROI'
           |from
           |doris_boom.app_day_pkg_kpi
           |where   data_date BETWEEN '$startDay' and   '$endDay'
           |and  pkg_code='$pkgCode'
           |ORDER BY pkg_code desc, data_date
           |""".stripMargin
      val list: util.List[String] = DorisDBUtils.query(conn, sql)
      val mapData: Map[String, util.List[String]] = new util.HashMap[String, util.List[String]]
      mapData.put(pkgName, list)
      mapDataAll.put(pkgName, list)

      val fileName = "运营日报_" + pkgName + "_" + endDay + ".xls"
      ExcelUtils.writerExcelFile(path + fileName, mapData);
      val emails = pkgOperator.split(",")
      emails.foreach(email => {
        EmailUtils.sendEmail(email, "运营日报:" + endDay, "运营日报:" + pkgName, path + fileName, fileName)
      })
    })

    val pkgName = "全部游戏'"
    val fileName = "运营日报_" + pkgName + "_" + endDay + ".xls"
    ExcelUtils.writerExcelFile(path + fileName, mapDataAll)

    EmailUtils.sendEmail("hulk@boomgames.top", "运营日报:" + endDay, "运营日报:" + pkgName, path + fileName, fileName)
    EmailUtils.sendEmail("huasheng@boomgames.top", "运营日报:" + endDay, "运营日报:" + pkgName, path + fileName, fileName)

  }
}
