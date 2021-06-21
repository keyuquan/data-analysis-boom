package com.analysis.boom.jobs.ta.dws

import com.analysis.boom.common.utils.DateUtils
import com.analysis.boom.jobs.utils.{KafkaUtils, PrestoUtils}

/**
 * ta 数据 dws 层统计
 */
object DwsTa {
  def runData(startDay: String, endDay: String): Unit = {
    val part_date = "$part_date";
    val part_event = "$part_event";

    val startDayRetain = DateUtils.addDay(startDay, -31);
    val sql_dws_ta_event_day_pkg_kpi =
      s"""
         |select
         |"$part_date"   data_date -- 统计日期
         |,"#bundle_id"  pkg_code  --  包名
         |,count(distinct if("#is_first_day" ,"#distinct_id",null) )  add_user_count --  新增用户数
         |,count(if("#is_first_day" ,"#distinct_id",null) )  add_count --  新增次数
         |,count(distinct "#distinct_id")  active_user_count --  活跃用户数
         |,count("#distinct_id")  active_count --  活跃次数
         |,count(distinct if("#is_first_day" and  "$part_event" IN ( 'ad_show' ),"#distinct_id",null) )  add_ad_show_user_count --  新增广告用户数
         |,count(if("#is_first_day" and  "$part_event" IN ( 'ad_show' ),"#distinct_id",null) )  add_ad_show_count --  新增广告次数
         |,count(distinct if("$part_event" IN ( 'ad_show' ),"#distinct_id",null) )  ad_show_user_count --  广告用户数
         |,count(if("$part_event" IN ( 'ad_show' ),"#distinct_id",null) )  ad_show_count --  广告次数
         |,count(1)   --  活跃次数
         |,ROUND(sum(if("$part_event" IN ( 'ad_show' ) ,ecpm,0))/if(sum(if("$part_event" IN ( 'ad_show' ) ,1,0))=0,1,sum(if("$part_event" IN ( 'ad_show' ),1,0))),2) ecpm
         |,ROUND(sum(if("$part_event" IN ( 'ad_show' ) and "#is_first_day" ,ecpm,0))/if(sum(if("$part_event" IN ( 'ad_show' ) and  "#is_first_day" ,1,0))=0,1,sum(if("$part_event" IN ( 'ad_show' ) and  "#is_first_day" ,1,0))),2) add_ecpm
         |,ROUND(sum(if("#is_first_day" and  "$part_event" IN ( 'ad_show' ),earnings,0) ),2)  earnings
         |from
         |ta.ta_event_5  where "$part_date" between  '$startDayRetain' AND '$endDay'
         |group by "$part_date", "#bundle_id"
         |""".stripMargin
    val sql_dws_ta_event_day_pkg_retain =
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
         |select   t_u."$part_date" , t_u."#bundle_id"  ,date_diff('day',cast(t_u."$part_date" as date),cast(t_a."$part_date" as date)) retain_day,count(distinct  t_a."#distinct_id") retain_count,sum(earnings)  earnings
         |from
         |(select      "$part_date" ,"#bundle_id" ,"#distinct_id" ,sum(if("$part_event" IN ( 'ad_show' ),coalesce(earnings,0) ,0)) earnings from  ta.ta_event_5  where    "$part_date" between  '$startDay' AND '$endDay' group by  "$part_date" ,"#bundle_id" ,"#distinct_id" ) t_a
         |join  (select    distinct  "$part_date" ,"#bundle_id" ,"#distinct_id" from  ta.ta_event_5  where    "$part_date" between  '$startDayRetain' AND '$endDay' and "#is_first_day"=true   ) t_u
         |on  t_u."#bundle_id" =t_a."#bundle_id" and  t_u."#distinct_id" =t_a."#distinct_id"
         |group  by  t_u."$part_date" , t_u."#bundle_id" ,date_diff('day',cast(t_u."$part_date" as date),cast(t_a."$part_date" as date))
         |) where  retain_day >=0
         |group  by  "$part_date","#bundle_id"
         |""".stripMargin

    val conn = PrestoUtils.getConnection;
    val listKpi = PrestoUtils.query(conn, "sql_dws_ta_event_day_pkg_kpi", sql_dws_ta_event_day_pkg_kpi)
    KafkaUtils.sendDataToKafka("boom_dws_ta_event_day_pkg_kpi", listKpi);
    val listRetain = PrestoUtils.query(conn, "sql_dws_ta_event_day_pkg_retain", sql_dws_ta_event_day_pkg_retain)
    KafkaUtils.sendDataToKafka("boom_dws_ta_event_day_pkg_retain", listRetain);
  }

  def main(args: Array[String]): Unit = {
    var startDay = DateUtils.getStartDay(10)
    var endDay = DateUtils.getEndDay()
    if (args.length >= 2) {
      startDay = args(0)
      endDay = args(1)
    }
    runData(startDay, endDay);

  }
}
