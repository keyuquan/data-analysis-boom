package com.analysis.booms.doris.main

import com.analysis.boom.common.utils.{DateUtils, DorisDBUtils}
import com.analysis.booms.doris.utils.{EmailUtils, ExcelUtils}
import java.util
import java.util.Map

object EmailMain {
  def main(args: Array[String]): Unit = {
    val endDay = DateUtils.getStartDay(-1)
    val conn = DorisDBUtils.getConnection
    val listConf = DorisDBUtils.queryMap(conn, "select pkg_code,pkg_name,pkg_operator  from  doris_boom.app_pkg_conf   order  by cast(ta_project_id as  int)")
    val mapDataAll: Map[String, util.List[String]] = new util.HashMap[String, util.List[String]]
    val path = "./report_data/"
    listConf.forEach(map => {
      val pkgName = map.get("pkg_name")
      val pkgOperator = map.get("pkg_operator")
      val fileName = "运营日报_" + pkgName + "_" + endDay + ".xls"
      val emails = pkgOperator.split(",")
      emails.foreach(email => {
        EmailUtils.sendEmail(email, "运营日报:" + endDay, "运营日报:" + pkgName, path + fileName, fileName)
      })
    })
    val pkgName = "全部游戏"
    val fileName = "运营日报_" + pkgName + "_" + endDay + ".xls"
    ExcelUtils.writerExcelFile(path + fileName, mapDataAll)
    EmailUtils.sendEmail("hulk@boomgames.top", "运营日报:" + endDay, "运营日报:" + pkgName, path + fileName, fileName)
    EmailUtils.sendEmail("huasheng@boomgames.top", "运营日报:" + endDay, "运营日报:" + pkgName, path + fileName, fileName)
  }
}
