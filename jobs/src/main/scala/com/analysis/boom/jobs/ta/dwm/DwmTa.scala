package com.analysis.boom.jobs.ta.dwm

import com.analysis.boom.common.utils.{DateUtils, DorisDBUtils}
import com.analysis.boom.jobs.utils.{KafkaUtils, PrestoUtils}
import java.util

/**
 * ta 数据 dws 层统计
 */
object DwmTa {
  def runData(startDay: String, endDay: String): Unit = {
    val part_date = "$part_date"
    val part_event = "$part_event"

    // 游戏维度
    val sql_dwm_ta_event_day_pkg_kpi_base =
      s"""
         |select
         |part_date   data_date -- 统计日期
         |,bundle_id  pkg_code  --  包名
         |,count(distinct if(t.event_date=u.active_date ,t.user_id,null) )  add_user_count --  新增用户数
         |,count(if(t.event_date=u.active_date ,t.user_id,null) )  add_count --  新增次数
         |,count(distinct t.user_id)  active_user_count --  活跃用户数
         |,count(t.user_id)  active_count --  活跃次数
         |,count(distinct if(t.event_date=u.active_date and  part_event IN ('ad_show'),t.user_id,null) )  add_ad_show_user_count --  新增广告用户数
         |,count(if(t.event_date=u.active_date and  part_event IN ('ad_show'),t.user_id,null) )  add_ad_show_count --  新增广告次数
         |,count(distinct if(part_event IN ('ad_show'),t.user_id,null) )  ad_show_user_count --  广告用户数
         |,count(if(part_event IN ('ad_show'),t.user_id,null) )  ad_show_count --  广告次数
         |,count(1)   --  活跃次数
         |,ROUND(sum(if(part_event IN ('ad_show') and ecpm>0 ,ecpm,0))/if(sum(if(part_event IN ('ad_show')  and ecpm>0,1,0))=0,1,sum(if(part_event IN ('ad_show') and ecpm>0,1,0))),4) ecpm
         |,ROUND(sum(if(part_event IN ('ad_show') and ecpm>0 and t.event_date=u.active_date ,ecpm,0))/if(sum(if(part_event IN ('ad_show')  and ecpm>0 and  t.event_date=u.active_date ,1,0))=0,1,sum(if(part_event IN ('ad_show')  and ecpm>0 and  t.event_date=u.active_date ,1,0))),4) add_ecpm
         |,ROUND(sum(if(part_event IN ('ad_show'),earnings,0) ),4)  earnings
         |from
         |(select "$part_date" part_date,"$part_event" part_event,"#bundle_id" bundle_id, "#user_id" user_id,"#event_time" event_time, date("#event_time") event_date,earnings_preset earnings,ecpm_preset ecpm from  ta.v_event_5  where "$part_date" between  '$startDay' AND '$endDay')  t
         |left join  (select "#user_id" as   user_id ,date("#active_time")  active_date   from   ta.v_user_5) u  on  t.user_id=u.user_id
         |group by part_date, bundle_id
         |""".stripMargin

    val sql_dwm_ta_event_day_pkg_retain_base =
      s"""
         |select
         |active_date   data_date -- 统计日期
         |,bundle_id  pkg_code  --  包名
         |,retain_day --  留存日期
         |,retain_count --  留存人数
         |,earnings --  ltv金额
         |from
         |(
         |select   u.active_date , t.bundle_id  ,date_diff('day',active_date,event_date) retain_day,count(distinct  t.user_id) retain_count,sum(if(part_event IN ('ad_show'),earnings,0))  earnings
         |from
         |(select "$part_date" part_date,"$part_event" part_event,"#bundle_id" bundle_id, "#user_id" user_id,"#event_time" event_time, date("#event_time") event_date,earnings_preset earnings,ecpm_preset ecpm from  ta.v_event_5  where "$part_date" between  '$startDay' AND '$endDay')  t
         |left join  (select "#user_id" as   user_id ,date("#active_time")  active_date   from   ta.v_user_5) u  on  t.user_id=u.user_id
         |group  by  u.active_date, t.bundle_id ,date_diff('day',active_date,event_date)
         |) where  retain_day >=0  and  bundle_id  is not null
         |""".stripMargin

    // 计划维度
    val sql_dwm_ta_event_day_pkg_plan_kpi_base =
      s"""
         |select
         |part_date   data_date -- 统计日期
         |,bundle_id  pkg_code  --  包名
         |,plan_id  --  计划
         |,count(distinct if(t.event_date=u.active_date ,t.user_id,null) )  add_user_count --  新增用户数
         |,count(if(t.event_date=u.active_date ,t.user_id,null) )  add_count --  新增次数
         |,count(distinct t.user_id)  active_user_count --  活跃用户数
         |,count(t.user_id)  active_count --  活跃次数
         |,count(distinct if(t.event_date=u.active_date and  part_event IN ('ad_show'),t.user_id,null) )  add_ad_show_user_count --  新增广告用户数
         |,count(if(t.event_date=u.active_date and  part_event IN ('ad_show'),t.user_id,null) )  add_ad_show_count --  新增广告次数
         |,count(distinct if(part_event IN ('ad_show'),t.user_id,null) )  ad_show_user_count --  广告用户数
         |,count(if(part_event IN ('ad_show'),t.user_id,null) )  ad_show_count --  广告次数
         |,count(1)   --  活跃次数
         |,ROUND(sum(if(part_event IN ('ad_show')  and ecpm>0,ecpm,0))/if(sum(if(part_event IN ('ad_show')  and ecpm>0,1,0))=0,1,sum(if(part_event IN ('ad_show') and ecpm>0,1,0))),4) ecpm
         |,ROUND(sum(if(part_event IN ('ad_show')  and ecpm>0 and t.event_date=u.active_date ,ecpm,0))/if(sum(if(part_event IN ('ad_show')  and ecpm>0 and  t.event_date=u.active_date ,1,0))=0,1,sum(if(part_event IN ('ad_show')  and ecpm>0 and  t.event_date=u.active_date ,1,0))),4) add_ecpm
         |,ROUND(sum(if(part_event IN ('ad_show'),earnings,0) ),4)  earnings
         |from
         |(select "$part_date" part_date,"$part_event" part_event,"#bundle_id" bundle_id, "#user_id" user_id,"#event_time" event_time, date("#event_time") event_date,earnings_preset earnings,ecpm_preset ecpm from  ta.v_event_5  where "$part_date" between  '$startDay' AND '$endDay')  t
         |left join  (select "#user_id" as   user_id ,date("#active_time")  active_date,plan_id_preset plan_id   from   ta.v_user_5) u  on  t.user_id=u.user_id
         |group by part_date, bundle_id,plan_id
         |""".stripMargin

    val sql_dwm_ta_event_day_pkg_plan_retain_base =
      s"""
         |select
         |active_date   data_date -- 统计日期
         |,bundle_id  pkg_code  --  包名
         |,plan_id   --  计划ID
         |,retain_day --  留存日期
         |,retain_count --  留存人数
         |,earnings --  ltv金额
         |from
         |(
         |select   u.active_date , t.bundle_id  ,u.plan_id,date_diff('day',active_date,event_date) retain_day,count(distinct  t.user_id) retain_count,sum(if(part_event IN ('ad_show'),earnings,0))  earnings
         |from
         |(select "$part_date" part_date,"$part_event" part_event,"#bundle_id" bundle_id, "#user_id" user_id,"#event_time" event_time, date("#event_time") event_date,earnings_preset earnings,ecpm_preset ecpm from  ta.v_event_5  where "$part_date" between  '$startDay' AND '$endDay')  t
         |left join  (select "#user_id" as   user_id ,date("#active_time")  active_date,plan_id_preset plan_id   from   ta.v_user_5) u  on  t.user_id=u.user_id
         |group  by  u.active_date, t.bundle_id ,u.plan_id,date_diff('day',active_date,event_date)
         |) where  retain_day >=0  and  bundle_id  is not null
         |""".stripMargin

    val conn = PrestoUtils.getConnection
    val connDorisDB = DorisDBUtils.getConnection;
    val list = DorisDBUtils.queryMap(connDorisDB,"select  * from  doris_boom.app_pkg_conf  where  ta_project_id is not null and ta_project_id<>''  order  by cast(ta_project_id as  int)");
    DorisDBUtils.close()
    list.forEach(conf => {
      val taProjectId = conf.get("ta_project_id").toString
      val isEarningsEcpm = conf.get("is_earnings_ecpm").toString
      val isThingioData = conf.get("is_thingio_data").toString

      if (taProjectId != null && (!taProjectId.equals(""))) {
        var sql_dwm_ta_event_day_pkg_kpi = sql_dwm_ta_event_day_pkg_kpi_base.replaceAll("_5", "_" + taProjectId)
        var sql_dwm_ta_event_day_pkg_retain = sql_dwm_ta_event_day_pkg_retain_base.replaceAll("_5", "_" + taProjectId)
        var sql_dwm_ta_event_day_pkg_plan_kpi = sql_dwm_ta_event_day_pkg_plan_kpi_base.replaceAll("_5", "_" + taProjectId)
        var sql_dwm_ta_event_day_pkg_plan_retain = sql_dwm_ta_event_day_pkg_plan_retain_base.replaceAll("_5", "_" + taProjectId)

        if (isEarningsEcpm.equals("0")) {
          // 没有接入回收数据
          sql_dwm_ta_event_day_pkg_kpi = sql_dwm_ta_event_day_pkg_kpi.replaceAll("earnings_preset", "0").replaceAll("ecpm_preset", "0")
          sql_dwm_ta_event_day_pkg_retain = sql_dwm_ta_event_day_pkg_retain.replaceAll("earnings_preset", "0").replaceAll("ecpm_preset", "0")
          sql_dwm_ta_event_day_pkg_plan_kpi = sql_dwm_ta_event_day_pkg_plan_kpi.replaceAll("earnings_preset", "0").replaceAll("ecpm_preset", "0")
          sql_dwm_ta_event_day_pkg_plan_retain = sql_dwm_ta_event_day_pkg_plan_retain.replaceAll("earnings_preset", "0").replaceAll("ecpm_preset", "0")
        } else {
          sql_dwm_ta_event_day_pkg_kpi = sql_dwm_ta_event_day_pkg_kpi.replaceAll("earnings_preset", "try_cast((try(earnings)) as double)").replaceAll("ecpm_preset", "try_cast((try(ecpm)) as double)")
          sql_dwm_ta_event_day_pkg_retain = sql_dwm_ta_event_day_pkg_retain.replaceAll("earnings_preset", "try_cast((try(earnings)) as double)").replaceAll("ecpm_preset", "try_cast((try(ecpm)) as double)")
          sql_dwm_ta_event_day_pkg_plan_kpi = sql_dwm_ta_event_day_pkg_plan_kpi.replaceAll("earnings_preset", "try_cast((try(earnings)) as double)").replaceAll("ecpm_preset", "try_cast((try(ecpm)) as double)")
          sql_dwm_ta_event_day_pkg_plan_retain = sql_dwm_ta_event_day_pkg_plan_retain.replaceAll("earnings_preset", "try_cast((try(earnings)) as double)").replaceAll("ecpm_preset", "try_cast((try(ecpm)) as double)")
        }

        if (isThingioData.equals("0")) {
          //  没有接入计划数据
          sql_dwm_ta_event_day_pkg_kpi = sql_dwm_ta_event_day_pkg_kpi.replaceAll("plan_id_preset", "0")
          sql_dwm_ta_event_day_pkg_retain = sql_dwm_ta_event_day_pkg_retain.replaceAll("plan_id_preset", "0")
          sql_dwm_ta_event_day_pkg_plan_kpi = sql_dwm_ta_event_day_pkg_plan_kpi.replaceAll("plan_id_preset", "0")
          sql_dwm_ta_event_day_pkg_plan_retain = sql_dwm_ta_event_day_pkg_plan_retain.replaceAll("plan_id_preset", "0")
        } else {
          sql_dwm_ta_event_day_pkg_kpi = sql_dwm_ta_event_day_pkg_kpi.replaceAll("plan_id_preset", "ry_planid")
          sql_dwm_ta_event_day_pkg_retain = sql_dwm_ta_event_day_pkg_retain.replaceAll("plan_id_preset", "ry_planid")
          sql_dwm_ta_event_day_pkg_plan_kpi = sql_dwm_ta_event_day_pkg_plan_kpi.replaceAll("plan_id_preset", "ry_planid")
          sql_dwm_ta_event_day_pkg_plan_retain = sql_dwm_ta_event_day_pkg_plan_retain.replaceAll("plan_id_preset", "ry_planid")
        }

        val listKpi = PrestoUtils.query(conn, "sql_dwm_ta_event_day_pkg_kpi", sql_dwm_ta_event_day_pkg_kpi)
        KafkaUtils.sendDataToKafka("boom_dwm_ta_event_day_pkg_kpi", listKpi)
        val listRetain = PrestoUtils.query(conn, "sql_dwm_ta_event_day_pkg_retain", sql_dwm_ta_event_day_pkg_retain)
        KafkaUtils.sendDataToKafka("boom_dwm_ta_event_day_pkg_retain", listRetain)
        val listPlanKpi = PrestoUtils.query(conn, "sql_dwm_ta_event_day_pkg_plan_kpi", sql_dwm_ta_event_day_pkg_plan_kpi)
        KafkaUtils.sendDataToKafka("boom_dwm_ta_event_day_pkg_plan_kpi", listPlanKpi)
        val listPlanRetain = PrestoUtils.query(conn, "sql_dwm_ta_event_day_pkg_plan_retain", sql_dwm_ta_event_day_pkg_plan_retain)
        KafkaUtils.sendDataToKafka("boom_dwm_ta_event_day_pkg_plan_retain", listPlanRetain)
      }
    })
    KafkaUtils.close()
  }

  def main(args: Array[String]): Unit = {
    var startDay = DateUtils.getStartDay(-10)
    var endDay = DateUtils.getEndDay()
    if (args.length >= 2) {
      startDay = args(0)
      endDay = args(1)
    }
    runData(startDay, endDay)

  }
}
