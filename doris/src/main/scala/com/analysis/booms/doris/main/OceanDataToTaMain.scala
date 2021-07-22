package com.analysis.booms.doris.main

import com.analysis.boom.common.utils.{DateUtils, DorisDBUtils, FileUtils, HttpUtils}

import java.util

object OceanDataToTaMain {

  def main(args: Array[String]): Unit = {
    val startDay = DateUtils.getStartDay(-40)
    val endDay = DateUtils.getStartDay(-1)
    val conn = DorisDBUtils.getConnection
    val listConf = DorisDBUtils.queryMap(conn, "select pkg_code,pkg_name,ta_project_id  from  doris_boom.app_pkg_conf  where  ta_project_id is  not  null and ta_project_id<>''  order  by cast(ta_project_id as  int)")
    val path = "./report_data/"
    listConf.forEach(map => {
      val pkgCode = map.get("pkg_code").toString
      val taProjectId = Integer.valueOf(map.get("ta_project_id").toString.replace(".0", ""))
      val sql =
        s"""
           |SELECT
           |concat(DATE_FORMAT(data_date , 'yyyyMMdd'),'_',plan_id)   ta_id
           |,data_date activeTime
           |,pkg_code pkginfo
           |,plan_id
           |,cost plancost
           |,active  planactive
           |,convert planaction
           |,classify adImpressions
           |from doris_boom.dws_ocean_day_pkg_plan_kpi
           |where  pkg_code='$pkgCode'
           |""".stripMargin
      val list: util.List[String] = DorisDBUtils.queryCsv(conn, sql)
      if (list.size() > 1) {
        val fileName = path + "ocean_" + endDay + "_" + taProjectId + ".csv"
        FileUtils.appendListToNewFile(fileName, list)
        // 上传到数数
        val map = new util.HashMap[String, AnyRef]
        map.put("token", "jHamPtd3SQ7dKeMSl4pR6ep5ypt0yDzqD4dsttDXOemfP6lmLk53qjwAV71gVDXn")
        map.put("projectId", taProjectId)
        map.put("createParam", "{\"mainColumn\":{\"property\":{\"columnName\":\"#vp@planday\",\"tableType\":\"user\",\"timestampJoinFormat\":null}},\"columns\":[{\"property\":{\"columnName\":\"ta_id\",\"columnDesc\":\"巨量计划加日期\",\"selectType\":\"string\"}},\n{\"property\":{\"columnName\":\"activeTime\",\"columnDesc\":\"激活时间\",\"selectType\":\"date\"}},{\"property\":{\"columnName\":\"pkginfo\",\"columnDesc\":\"包名\",\"selectType\":\"string\"}},{\"property\":{\"columnName\":\"plan_id\",\"columnDesc\":\"广告计划ID\",\"selectType\":\"number\"}},{\"property\":{\"columnName\":\"plancost\",\"columnDesc\":\"消耗金额\",\"selectType\":\"number\"}},{\"property\":{\"columnName\":\"planactive\",\"columnDesc\":\"激活数\",\"selectType\":\"number\"}},{\"property\":{\"columnName\":\"planaction\",\"columnDesc\":\"转化数\",\"selectType\":\"number\"}},{\"property\":{\"columnName\":\"adImpressions\",\"columnDesc\":\"投放位置\",\"selectType\":\"string\"}}]}")
        val s: String = HttpUtils.doFilePost("http://ta2:8992/open/dict-create", map, "jHamPtd3SQ7dKeMSl4pR6ep5ypt0yDzqD4dsttDXOemfP6lmLk53qjwAV71gVDXn", fileName)
        System.out.println(s)
      }

    })
  }
}
