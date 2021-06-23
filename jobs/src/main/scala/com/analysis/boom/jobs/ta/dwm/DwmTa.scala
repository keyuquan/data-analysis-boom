package com.analysis.boom.jobs.ta.dwm

import com.analysis.boom.common.utils.DateUtils
import com.analysis.boom.jobs.utils.{KafkaUtils, PrestoUtils}

/**
 * ta 数据 dws 层统计
 */
object DwmTa {
  def runData(startDay: String, endDay: String): Unit = {
    val part_date = "$part_date"
    val part_event = "$part_event"

    val startDayRetain = DateUtils.addDay(startDay, -31)

    // 游戏维度
    val sql_dwm_ta_event_day_pkg_kpi =
      s"""
         |select
         |"$part_date"   data_date -- 统计日期
         |,"#bundle_id"  pkg_code  --  包名
         |,count(distinct if("#is_first_day" ,"#user_id",null) )  add_user_count --  新增用户数
         |,count(if("#is_first_day" ,"#user_id",null) )  add_count --  新增次数
         |,count(distinct "#user_id")  active_user_count --  活跃用户数
         |,count("#user_id")  active_count --  活跃次数
         |,count(distinct if("#is_first_day" and  "$part_event" IN ( 'ad_show' ),"#user_id",null) )  add_ad_show_user_count --  新增广告用户数
         |,count(if("#is_first_day" and  "$part_event" IN ( 'ad_show' ),"#user_id",null) )  add_ad_show_count --  新增广告次数
         |,count(distinct if("$part_event" IN ( 'ad_show' ),"#user_id",null) )  ad_show_user_count --  广告用户数
         |,count(if("$part_event" IN ( 'ad_show' ),"#user_id",null) )  ad_show_count --  广告次数
         |,count(1)   --  活跃次数
         |,ROUND(sum(if("$part_event" IN ( 'ad_show' ) ,ecpm,0))/if(sum(if("$part_event" IN ( 'ad_show' ) ,1,0))=0,1,sum(if("$part_event" IN ( 'ad_show' ),1,0))),2) ecpm
         |,ROUND(sum(if("$part_event" IN ( 'ad_show' ) and "#is_first_day" ,ecpm,0))/if(sum(if("$part_event" IN ( 'ad_show' ) and  "#is_first_day" ,1,0))=0,1,sum(if("$part_event" IN ( 'ad_show' ) and  "#is_first_day" ,1,0))),2) add_ecpm
         |,ROUND(sum(if("#is_first_day" and  "$part_event" IN ( 'ad_show' ),earnings,0) ),2)  earnings
         |from
         |ta.ta_event_5  where "$part_date" between  '$startDay' AND '$endDay'
         |group by "$part_date", "#bundle_id"
         |""".stripMargin

    val sql_dwm_ta_event_day_pkg_retain =
      s"""
         |select
         |"$part_date"   data_date -- 统计日期
         |,"#bundle_id"  pkg_code  --  包名
         |,max(if(retain_day=1,retain_count,0))  retain_1
         |,max(if(retain_day=2,retain_count,0))  retain_2
         |,max(if(retain_day=3,retain_count,0))  retain_3
         |,max(if(retain_day=4,retain_count,0))  retain_4
         |,max(if(retain_day=5,retain_count,0))  retain_5
         |,max(if(retain_day=6,retain_count,0))  retain_6
         |,max(if(retain_day=7,retain_count,0))  retain_7
         |,max(if(retain_day=15,retain_count,0))  retain_15
         |,max(if(retain_day=30,retain_count,0))  retain_30
         |,sum(if(retain_day<=0,coalesce(earnings,0),0))  ltv_0
         |,sum(if(retain_day<=1,coalesce(earnings,0),0))  ltv_1
         |,ROUND(sum(if(retain_day<=2,coalesce(earnings,0),0)),2)  ltv_2
         |,ROUND(sum(if(retain_day<=3,coalesce(earnings,0),0)),2)  ltv_3
         |,ROUND(sum(if(retain_day<=4,coalesce(earnings,0),0)),2)  ltv_4
         |,ROUND(sum(if(retain_day<=5,coalesce(earnings,0),0)),2)  ltv_5
         |,ROUND(sum(if(retain_day<=6,coalesce(earnings,0),0)),2)  ltv_6
         |,ROUND(sum(if(retain_day<=7,coalesce(earnings,0),0)),2)  ltv_7
         |,ROUND(sum(if(retain_day<=15,coalesce(earnings,0),0)),2)  ltv_15
         |,ROUND(sum(if(retain_day<=30,coalesce(earnings,0),0)),2)  ltv_30
         |from
         |(
         |select   t_u."$part_date" , t_u."#bundle_id"  ,date_diff('day',cast(t_u."$part_date" as date),cast(t_a."$part_date" as date)) retain_day,count(distinct  t_a."#user_id") retain_count,sum(earnings)  earnings
         |from
         |(select      "$part_date" ,"#bundle_id" ,"#user_id" ,sum(if("$part_event" IN ( 'ad_show' ),coalesce(earnings,0) ,0)) earnings from  ta.ta_event_5  where    "$part_date" between  '$startDay' AND '$endDay' group by  "$part_date" ,"#bundle_id" ,"#user_id" ) t_a
         |join  (select    distinct  "$part_date" ,"#bundle_id" ,"#user_id" from  ta.ta_event_5  where    "$part_date" between  '$startDayRetain' AND '$endDay' and "#is_first_day"=true   ) t_u
         |on  t_u."#bundle_id" =t_a."#bundle_id" and  t_u."#user_id" =t_a."#user_id"
         |group  by  t_u."$part_date" , t_u."#bundle_id" ,date_diff('day',cast(t_u."$part_date" as date),cast(t_a."$part_date" as date))
         |) where  retain_day >=0  and  "#bundle_id"  is not null
         |group  by  "$part_date","#bundle_id"
         |""".stripMargin

    // 计划维度
    val sql_dwm_ta_event_day_pkg_plan_kpi =
      s"""
         |select
         |"$part_date"   data_date -- 统计日期
         |,"#bundle_id"  pkg_code  --  包名
         |,t_u.ry_planid plan_id   -- 计划ID
         |,count(distinct if("#is_first_day" ,"#user_id",null) )  add_user_count --  新增用户数
         |,count(if("#is_first_day" ,"#user_id",null) )  add_count --  新增次数
         |,count(distinct "#user_id")  active_user_count --  活跃用户数
         |,count("#user_id")  active_count --  活跃次数
         |,count(distinct if("#is_first_day" and  "$part_event" IN ( 'ad_show' ),"#user_id",null) )  add_ad_show_user_count --  新增广告用户数
         |,count(if("#is_first_day" and  "$part_event" IN ( 'ad_show' ),"#user_id",null) )  add_ad_show_count --  新增广告次数
         |,count(distinct if("$part_event" IN ( 'ad_show' ),"#user_id",null) )  ad_show_user_count --  广告用户数
         |,count(if("$part_event" IN ( 'ad_show' ),"#user_id",null) )  ad_show_count --  广告次数
         |,count(1)   --  活跃次数
         |,ROUND(sum(if("$part_event" IN ( 'ad_show' ) ,ecpm,0))/if(sum(if("$part_event" IN ( 'ad_show' ) ,1,0))=0,1,sum(if("$part_event" IN ( 'ad_show' ),1,0))),2) ecpm
         |,ROUND(sum(if("$part_event" IN ( 'ad_show' ) and "#is_first_day" ,ecpm,0))/if(sum(if("$part_event" IN ( 'ad_show' ) and  "#is_first_day" ,1,0))=0,1,sum(if("$part_event" IN ( 'ad_show' ) and  "#is_first_day" ,1,0))),2) add_ecpm
         |,ROUND(sum(if("#is_first_day" and  "$part_event" IN ( 'ad_show' ),earnings,0) ),2)  earnings
         |from
         |(select *  from  ta.ta_event_5   where "$part_date" between  '$startDay' AND '$endDay') t
         |join  (select distinct ry_planid,"#user_id" from  ta.v_user_5 where ry_planid is  not  null  ) t_u  on  t."#user_id"=t_u."#user_id"
         |group by "$part_date", "#bundle_id", "#bundle_id",t_u.ry_planid
         |""".stripMargin

    val sql_dwm_ta_event_day_pkg_plan_retain =
      s"""
         |select
         |"$part_date"   data_date -- 统计日期
         |,"#bundle_id"  pkg_code  --  包名
         |,ry_planid  plan_id -- 计划ID
         |,max(if(retain_day=1,retain_count,0))  retain_1
         |,max(if(retain_day=2,retain_count,0))  retain_2
         |,max(if(retain_day=3,retain_count,0))  retain_3
         |,max(if(retain_day=4,retain_count,0))  retain_4
         |,max(if(retain_day=5,retain_count,0))  retain_5
         |,max(if(retain_day=6,retain_count,0))  retain_6
         |,max(if(retain_day=7,retain_count,0))  retain_7
         |,max(if(retain_day=15,retain_count,0))  retain_15
         |,max(if(retain_day=30,retain_count,0))  retain_30
         |,sum(if(retain_day<=0,coalesce(earnings,0),0))  ltv_0
         |,sum(if(retain_day<=1,coalesce(earnings,0),0))  ltv_1
         |,ROUND(sum(if(retain_day<=2,coalesce(earnings,0),0)),2)  ltv_2
         |,ROUND(sum(if(retain_day<=3,coalesce(earnings,0),0)),2)  ltv_3
         |,ROUND(sum(if(retain_day<=4,coalesce(earnings,0),0)),2)  ltv_4
         |,ROUND(sum(if(retain_day<=5,coalesce(earnings,0),0)),2)  ltv_5
         |,ROUND(sum(if(retain_day<=6,coalesce(earnings,0),0)),2)  ltv_6
         |,ROUND(sum(if(retain_day<=7,coalesce(earnings,0),0)),2)  ltv_7
         |,ROUND(sum(if(retain_day<=15,coalesce(earnings,0),0)),2)  ltv_15
         |,ROUND(sum(if(retain_day<=30,coalesce(earnings,0),0)),2)  ltv_30
         |from
         |(
         |select   t_u."$part_date" , t_u."#bundle_id"  ,t_u_2.ry_planid,date_diff('day',cast(t_u."$part_date" as date),cast(t_a."$part_date" as date)) retain_day,count(distinct  t_a."#user_id") retain_count,sum(earnings)  earnings
         |from
         |(select      "$part_date" ,"#bundle_id" ,"#user_id" ,sum(if("$part_event" IN ( 'ad_show' ),coalesce(earnings,0) ,0)) earnings from  ta.ta_event_5  where    "$part_date" between  '$startDay' AND '$endDay' group by  "$part_date" ,"#bundle_id" ,"#user_id" ) t_a
         |join  (
         |select    distinct  "$part_date" ,"#bundle_id" ,"#user_id" from  ta.ta_event_5  where    "$part_date" between  '$startDayRetain' AND '$endDay' and "#is_first_day"=true   
         |) t_u on  t_u."#bundle_id" =t_a."#bundle_id" and  t_u."#user_id" =t_a."#user_id"
         |join  (select distinct ry_planid,"#user_id" from  ta.v_user_5 where ry_planid is  not  null  ) t_u_2  on  t_a."#user_id"=t_u_2."#user_id"
         |group  by  t_u."$part_date" , t_u."#bundle_id" ,t_u_2.ry_planid ,date_diff('day',cast(t_u."$part_date" as date),cast(t_a."$part_date" as date))
         |) where  retain_day >=0  and  "#bundle_id"  is not null
         |group  by  "$part_date","#bundle_id","#bundle_id",ry_planid
         |""".stripMargin

    val conn = PrestoUtils.getConnection
    val listKpi = PrestoUtils.query(conn, "sql_dwm_ta_event_day_pkg_kpi", sql_dwm_ta_event_day_pkg_kpi)
    KafkaUtils.sendDataToKafka("boom_dwm_ta_event_day_pkg_kpi", listKpi)
    val listRetain = PrestoUtils.query(conn, "sql_dwm_ta_event_day_pkg_retain", sql_dwm_ta_event_day_pkg_retain)
    KafkaUtils.sendDataToKafka("boom_dwm_ta_event_day_pkg_retain", listRetain)
    val listPlanKpi = PrestoUtils.query(conn, "sql_dwm_ta_event_day_pkg_plan_kpi", sql_dwm_ta_event_day_pkg_plan_kpi)
    KafkaUtils.sendDataToKafka("boom_dwm_ta_event_day_pkg_plan_kpi", listPlanKpi)
    val listPlanRetain = PrestoUtils.query(conn, "sql_dwm_ta_event_day_pkg_plan_retain", sql_dwm_ta_event_day_pkg_plan_retain)
    KafkaUtils.sendDataToKafka("boom_dwm_ta_event_day_pkg_plan_retain", listPlanRetain)
    KafkaUtils.close()
  }

  def main(args: Array[String]): Unit = {
    var startDay = DateUtils.getStartDay(10)
    var endDay = DateUtils.getEndDay()
    if (args.length >= 2) {
      startDay = args(0)
      endDay = args(1)
    }
    runData(startDay, endDay)

  }
}
