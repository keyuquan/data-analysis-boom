package com.analysis.booms.doris.main

import com.analysis.boom.common.utils.{DateUtils, DorisDBUtils, FileUtils}

object CsvMain {
  def main(args: Array[String]): Unit = {
    val startDay = DateUtils.getStartDay(10)
    val endDay = DateUtils.getEndDay()
    val conn = DorisDBUtils.getConnection
    val listConf = DorisDBUtils.queryMap(conn, "select pkg_code,pkg_name  from  doris_boom.app_pkg_conf ")
    listConf.forEach(map => {
      val pkg_code = map.get("pkg_code")
      val pkg_name = map.get("pkg_name")
      val sql =
        s"""
           |SELECT  *  from  doris_boom.v_app_day_pkg_kpi
           |where   日期 BETWEEN '$startDay' and   '$endDay'
           |and  包名='$pkg_code'
           |ORDER BY 包名 desc, 日期
           |""".stripMargin
      val list = DorisDBUtils.query(conn, sql)
      val fileName = "日报_" + pkg_name + "_" + endDay + ".csv"
      FileUtils.appendListToNewFile(fileName, list)
    })
  }
}
