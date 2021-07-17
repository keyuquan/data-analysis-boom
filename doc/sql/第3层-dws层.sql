drop table  dwm_ta_event_day_pkg_kpi ;
CREATE TABLE `dwm_ta_event_day_pkg_kpi` (
  `data_date` date COMMENT '数据统计时间',
  `pkg_code` varchar(100) COMMENT '包名',
  `add_user_count` bigint(20) COMMENT '新增用户数',
  `add_count` bigint(20) COMMENT '新增次数',
  `active_user_count` bigint(20) COMMENT '用户数',
  `active_count` bigint(20) COMMENT '次数',  
  `add_ad_show_user_count` bigint(20) COMMENT '新增广告用户数',
  `add_ad_show_count` bigint(20) COMMENT '新增广告次数',
  `ad_show_user_count` bigint(20) COMMENT '广告用户数',
  `ad_show_count` bigint(20) COMMENT '广告次数',
  `ecpm` decimal(20,4) COMMENT '千次展示获得的广告收入',
  `add_ecpm` decimal(20,4) COMMENT '新增用户千次展示获得的广告收入',
  `earnings` decimal(20,4) COMMENT '回收金额',
  `update_time` datetime COMMENT '数据更新时间'
) ENGINE=OLAP
UNIQUE KEY(`data_date`, `pkg_code`)
COMMENT "数数小表聚合：ta_event,day,pkg 维度 "
PARTITION BY RANGE(`data_date`)
(PARTITION p202106 VALUES [('1001-01-01'), ('2021-07-01')),
PARTITION p202107 VALUES [('2021-07-01'), ('2021-08-01')),
PARTITION p202108 VALUES [('2021-08-01'), ('2021-09-01')),
PARTITION p202109 VALUES [('2021-09-01'), ('2021-10-01')))
DISTRIBUTED BY HASH(`pkg_code`) BUCKETS 3
PROPERTIES (
"replication_num" = "1",
"dynamic_partition.enable" = "true",
"dynamic_partition.time_unit" = "MONTH",
"dynamic_partition.start" = "-120",
"dynamic_partition.end" = "30",
"dynamic_partition.prefix" = "p",
"dynamic_partition.buckets" = "3",
"in_memory" = "false",
"storage_format" = "DEFAULT"
);

drop table  dwm_ta_event_day_pkg_retain ;
CREATE TABLE `dwm_ta_event_day_pkg_retain` (
  `data_date` date COMMENT '数据统计时间',
  `pkg_code` varchar(100) COMMENT '包名',
  `retain_day` bigint(20) COMMENT '留存日期',
  `retain_count` bigint(20) COMMENT '留存人数',
  `earnings` decimal(20,4) COMMENT 'ltv金额'
) ENGINE=OLAP
UNIQUE KEY(`data_date`, `pkg_code`, `retain_day`)
COMMENT "数数小表聚合：ta_event,day,pkg 维度 "
PARTITION BY RANGE(`data_date`)
(PARTITION p202106 VALUES [('1001-01-01'), ('2021-07-01')),
PARTITION p202107 VALUES [('2021-07-01'), ('2021-08-01')),
PARTITION p202108 VALUES [('2021-08-01'), ('2021-09-01')),
PARTITION p202109 VALUES [('2021-09-01'), ('2021-10-01')))
DISTRIBUTED BY HASH(`pkg_code`) BUCKETS 3
PROPERTIES (
"replication_num" = "1",
"dynamic_partition.enable" = "true",
"dynamic_partition.time_unit" = "MONTH",
"dynamic_partition.start" = "-120",
"dynamic_partition.end" = "30",
"dynamic_partition.prefix" = "p",
"dynamic_partition.buckets" = "3",
"in_memory" = "false",
"storage_format" = "DEFAULT"
);

drop table  dwm_ta_event_day_pkg_plan_kpi ;
CREATE TABLE `dwm_ta_event_day_pkg_plan_kpi` (
  `data_date` date COMMENT '数据统计时间',
  `pkg_code` varchar(100) COMMENT '包名',
  `plan_id` varchar(100) COMMENT '计划ID',
  `add_user_count` bigint(20) COMMENT '新增用户数',
  `add_count` bigint(20) COMMENT '新增次数',
  `active_user_count` bigint(20) COMMENT '用户数',
  `active_count` bigint(20) COMMENT '次数',  
  `add_ad_show_user_count` bigint(20) COMMENT '新增广告用户数',
  `add_ad_show_count` bigint(20) COMMENT '新增广告次数',
  `ad_show_user_count` bigint(20) COMMENT '广告用户数',
  `ad_show_count` bigint(20) COMMENT '广告次数',
  `ecpm` decimal(20,4) COMMENT '千次展示获得的广告收入',
  `add_ecpm` decimal(20,4) COMMENT '新增用户千次展示获得的广告收入',
  `earnings` decimal(20,4) COMMENT '回收金额',
  `update_time` datetime COMMENT '数据更新时间'
) ENGINE=OLAP
UNIQUE KEY(`data_date`, `pkg_code`, `plan_id`)
COMMENT "数数小表聚合：ta_event,day,pkg,plan_id 维度 "
PARTITION BY RANGE(`data_date`)
(PARTITION p202106 VALUES [('1001-01-01'), ('2021-07-01')),
PARTITION p202107 VALUES [('2021-07-01'), ('2021-08-01')),
PARTITION p202108 VALUES [('2021-08-01'), ('2021-09-01')),
PARTITION p202109 VALUES [('2021-09-01'), ('2021-10-01')))
DISTRIBUTED BY HASH(`pkg_code`) BUCKETS 3
PROPERTIES (
"replication_num" = "1",
"dynamic_partition.enable" = "true",
"dynamic_partition.time_unit" = "MONTH",
"dynamic_partition.start" = "-120",
"dynamic_partition.end" = "30",
"dynamic_partition.prefix" = "p",
"dynamic_partition.buckets" = "3",
"in_memory" = "false",
"storage_format" = "DEFAULT"
);

drop table  dwm_ta_event_day_pkg_plan_retain ;
CREATE TABLE `dwm_ta_event_day_pkg_plan_retain` (
  `data_date` date COMMENT '数据统计时间',
  `pkg_code` varchar(100) COMMENT '包名',
  `plan_id` varchar(100) COMMENT '计划ID',
  `retain_day` bigint(20) COMMENT '留存日期',
  `retain_count` bigint(20) COMMENT '留存人数',
  `earnings` decimal(20,4) COMMENT 'ltv金额'
) ENGINE=OLAP
UNIQUE KEY(`data_date`, `pkg_code`,`plan_id`, `retain_day`)
COMMENT "数数小表聚合：ta_event,day,pkg 维度 "
PARTITION BY RANGE(`data_date`)
(PARTITION p202106 VALUES [('1001-01-01'), ('2021-07-01')),
PARTITION p202107 VALUES [('2021-07-01'), ('2021-08-01')),
PARTITION p202108 VALUES [('2021-08-01'), ('2021-09-01')),
PARTITION p202109 VALUES [('2021-09-01'), ('2021-10-01')))
DISTRIBUTED BY HASH(`pkg_code`) BUCKETS 3
PROPERTIES (
"replication_num" = "1",
"dynamic_partition.enable" = "true",
"dynamic_partition.time_unit" = "MONTH",
"dynamic_partition.start" = "-120",
"dynamic_partition.end" = "30",
"dynamic_partition.prefix" = "p",
"dynamic_partition.buckets" = "3",
"in_memory" = "false",
"storage_format" = "DEFAULT"
);


drop table  dws_ta_day_pkg_plan_kpi ;
CREATE TABLE `dws_ta_day_pkg_plan_kpi` (
  `data_date` date COMMENT '数据统计时间',
  `pkg_code` varchar(100) COMMENT '包名',
  `plan_id` varchar(100) COMMENT '计划ID',
  `classify` int(10) COMMENT '分类: 1 通投,2穿山甲,站内',
  `add_user_count` bigint(20) COMMENT '新增用户数',
  `add_count` bigint(20) COMMENT '新增次数',
  `active_user_count` bigint(20) COMMENT '用户数',
  `active_count` bigint(20) COMMENT '次数',  
  `add_ad_show_user_count` bigint(20) COMMENT '新增广告用户数',
  `add_ad_show_count` bigint(20) COMMENT '新增广告次数',
  `ad_show_user_count` bigint(20) COMMENT '广告用户数',
  `ad_show_count` bigint(20) COMMENT '广告次数',
  `ecpm` decimal(20,4) COMMENT '千次展示获得的广告收入',
  `add_ecpm` decimal(20,4) COMMENT '新增用户千次展示获得的广告收入',
  `earnings` decimal(20,4) COMMENT '回收金额',
  `earnings_all` decimal(20,4) COMMENT '累计回收金额',
  `retain_1` bigint(20) COMMENT '1日用户留存',
  `retain_2` bigint(20) COMMENT '2日用户留存',
  `retain_3` bigint(20) COMMENT '3日用户留存',
  `retain_4` bigint(20) COMMENT '4日用户留存',
  `retain_5` bigint(20) COMMENT '5日用户留存',
  `retain_6` bigint(20) COMMENT '6日用户留存',
  `retain_7` bigint(20) COMMENT '7日用户留存',
  `retain_15` bigint(20) COMMENT '15日用户留存',
  `retain_30` bigint(20) COMMENT '30日用户留存',  
  `ltv_0` decimal(20,4) COMMENT '首日用户ltv:arpu',
  `ltv_1` decimal(20,4) COMMENT '次日用户ltv',
  `ltv_2` decimal(20,4) COMMENT '2日用户ltv',
  `ltv_3` decimal(20,4) COMMENT '3日用户ltv',
  `ltv_4` decimal(20,4) COMMENT '4日用户ltv',
  `ltv_5` decimal(20,4) COMMENT '5日用户ltv',
  `ltv_6` decimal(20,4) COMMENT '6日用户ltv',
  `ltv_7` decimal(20,4) COMMENT '7日用户ltv',
  `ltv_15` decimal(20,4) COMMENT '15日用户ltv',
  `ltv_30` decimal(20,4) COMMENT '30日用户ltv',
  `update_time` datetime COMMENT '数据更新时间'
) ENGINE=OLAP
UNIQUE KEY(`data_date`, `pkg_code`,`plan_id`)
COMMENT "数数维度大表: day,pkg 维度 "
PARTITION BY RANGE(`data_date`)
(PARTITION p202106 VALUES [('1001-01-01'), ('2021-07-01')),
PARTITION p202107 VALUES [('2021-07-01'), ('2021-08-01')),
PARTITION p202108 VALUES [('2021-08-01'), ('2021-09-01')),
PARTITION p202109 VALUES [('2021-09-01'), ('2021-10-01')))
DISTRIBUTED BY HASH(`pkg_code`) BUCKETS 3
PROPERTIES (
"replication_num" = "1",
"dynamic_partition.enable" = "true",
"dynamic_partition.time_unit" = "MONTH",
"dynamic_partition.start" = "-120",
"dynamic_partition.end" = "30",
"dynamic_partition.prefix" = "p",
"dynamic_partition.buckets" = "3",
"in_memory" = "false",
"storage_format" = "DEFAULT"
);


drop table  dws_ta_day_pkg_kpi ;
CREATE TABLE `dws_ta_day_pkg_kpi` (
  `data_date` date COMMENT '数据统计时间',
  `pkg_code` varchar(100) COMMENT '包名',
  `add_user_count` bigint(20) COMMENT '新增用户数',
  `add_count` bigint(20) COMMENT '新增次数',
  `active_user_count` bigint(20) COMMENT '用户数',
  `active_count` bigint(20) COMMENT '次数',  
  `add_ad_show_user_count` bigint(20) COMMENT '新增广告用户数',
  `add_ad_show_count` bigint(20) COMMENT '新增广告次数',
  `ad_show_user_count` bigint(20) COMMENT '广告用户数',
  `ad_show_count` bigint(20) COMMENT '广告次数',
  `ecpm` decimal(20,4) COMMENT '千次展示获得的广告收入',
  `add_ecpm` decimal(20,4) COMMENT '新增用户千次展示获得的广告收入',
  `earnings` decimal(20,4) COMMENT '回收金额',
  `ttzx_earnings` decimal(20,4) COMMENT '回收金额:通投智选',
  `pangle_earnings` decimal(20,4) COMMENT '回收金额:穿山甲',
  `site_earnings` decimal(20,4) COMMENT '回收金额:站内',
  `earnings_all` decimal(20,4) COMMENT '累计回收金额',
  `ttzx_earnings_all` decimal(20,4) COMMENT '累计回收金额:通投智选',
  `pangle_earnings_all` decimal(20,4) COMMENT '累计回收金额:穿山甲',
  `site_earnings_all` decimal(20,4) COMMENT '累计回收金额:站内',
  `retain_1` bigint(20) COMMENT '1日用户留存',
  `retain_2` bigint(20) COMMENT '2日用户留存',
  `retain_3` bigint(20) COMMENT '3日用户留存',
  `retain_4` bigint(20) COMMENT '4日用户留存',
  `retain_5` bigint(20) COMMENT '5日用户留存',
  `retain_6` bigint(20) COMMENT '6日用户留存',
  `retain_7` bigint(20) COMMENT '7日用户留存',
  `retain_15` bigint(20) COMMENT '15日用户留存',
  `retain_30` bigint(20) COMMENT '30日用户留存',  
  `ltv_0` decimal(20,4) COMMENT '首日用户ltv:arpu',
  `ltv_1` decimal(20,4) COMMENT '次日用户ltv',
  `ltv_2` decimal(20,4) COMMENT '2日用户ltv',
  `ltv_3` decimal(20,4) COMMENT '3日用户ltv',
  `ltv_4` decimal(20,4) COMMENT '4日用户ltv',
  `ltv_5` decimal(20,4) COMMENT '5日用户ltv',
  `ltv_6` decimal(20,4) COMMENT '6日用户ltv',
  `ltv_7` decimal(20,4) COMMENT '7日用户ltv',
  `ltv_15` decimal(20,4) COMMENT '15日用户ltv',
  `ltv_30` decimal(20,4) COMMENT '30日用户ltv',
  `ttzx_retain_1` bigint(20) COMMENT '1日用户留存:通投智选',
  `ttzx_retain_2` bigint(20) COMMENT '2日用户留存:通投智选',
  `ttzx_retain_3` bigint(20) COMMENT '3日用户留存:通投智选',
  `ttzx_retain_4` bigint(20) COMMENT '4日用户留存:通投智选',
  `ttzx_retain_5` bigint(20) COMMENT '5日用户留存:通投智选',
  `ttzx_retain_6` bigint(20) COMMENT '6日用户留存:通投智选',
  `ttzx_retain_7` bigint(20) COMMENT '7日用户留存:通投智选',
  `ttzx_retain_15` bigint(20) COMMENT '15日用户留存:通投智选',
  `ttzx_retain_30` bigint(20) COMMENT '30日用户留存:通投智选',  
  `ttzx_ltv_0` decimal(20,4) COMMENT '首日用户ltv:arpu:通投智选',
  `ttzx_ltv_1` decimal(20,4) COMMENT '次日用户ltv:通投智选',
  `ttzx_ltv_2` decimal(20,4) COMMENT '2日用户ltv:通投智选',
  `ttzx_ltv_3` decimal(20,4) COMMENT '3日用户ltv:通投智选',
  `ttzx_ltv_4` decimal(20,4) COMMENT '4日用户ltv:通投智选',
  `ttzx_ltv_5` decimal(20,4) COMMENT '5日用户ltv:通投智选',
  `ttzx_ltv_6` decimal(20,4) COMMENT '6日用户ltv:通投智选',
  `ttzx_ltv_7` decimal(20,4) COMMENT '7日用户ltv:通投智选',
  `ttzx_ltv_15` decimal(20,4) COMMENT '15日用户ltv:通投智选',
  `ttzx_ltv_30` decimal(20,4) COMMENT '30日用户ltv:通投智选',
  `pangle_retain_1` bigint(20) COMMENT '1日用户留存:穿山甲',
  `pangle_retain_2` bigint(20) COMMENT '2日用户留存:穿山甲',
  `pangle_retain_3` bigint(20) COMMENT '3日用户留存:穿山甲',
  `pangle_retain_4` bigint(20) COMMENT '4日用户留存:穿山甲',
  `pangle_retain_5` bigint(20) COMMENT '5日用户留存:穿山甲',
  `pangle_retain_6` bigint(20) COMMENT '6日用户留存:穿山甲',
  `pangle_retain_7` bigint(20) COMMENT '7日用户留存:穿山甲',
  `pangle_retain_15` bigint(20) COMMENT '15日用户留存:穿山甲',
  `pangle_retain_30` bigint(20) COMMENT '30日用户留存:穿山甲',  
  `pangle_ltv_0` decimal(20,4) COMMENT '首日用户ltv:arpu:穿山甲',
  `pangle_ltv_1` decimal(20,4) COMMENT '次日用户ltv:穿山甲',
  `pangle_ltv_2` decimal(20,4) COMMENT '2日用户ltv:穿山甲',
  `pangle_ltv_3` decimal(20,4) COMMENT '3日用户ltv:穿山甲',
  `pangle_ltv_4` decimal(20,4) COMMENT '4日用户ltv:穿山甲',
  `pangle_ltv_5` decimal(20,4) COMMENT '5日用户ltv:穿山甲',
  `pangle_ltv_6` decimal(20,4) COMMENT '6日用户ltv:穿山甲',
  `pangle_ltv_7` decimal(20,4) COMMENT '7日用户ltv:穿山甲',
  `pangle_ltv_15` decimal(20,4) COMMENT '15日用户ltv:穿山甲',
  `pangle_ltv_30` decimal(20,4) COMMENT '30日用户ltv:穿山甲',
  `site_retain_1` bigint(20) COMMENT '1日用户留存:站内',
  `site_retain_2` bigint(20) COMMENT '2日用户留存:站内',
  `site_retain_3` bigint(20) COMMENT '3日用户留存:站内',
  `site_retain_4` bigint(20) COMMENT '4日用户留存:站内',
  `site_retain_5` bigint(20) COMMENT '5日用户留存:站内',
  `site_retain_6` bigint(20) COMMENT '6日用户留存:站内',
  `site_retain_7` bigint(20) COMMENT '7日用户留存:站内',
  `site_retain_15` bigint(20) COMMENT '15日用户留存:站内',
  `site_retain_30` bigint(20) COMMENT '30日用户留存:站内',  
  `site_ltv_0` decimal(20,4) COMMENT '首日用户ltv:arpu:站内',
  `site_ltv_1` decimal(20,4) COMMENT '次日用户ltv:站内',
  `site_ltv_2` decimal(20,4) COMMENT '2日用户ltv:站内',
  `site_ltv_3` decimal(20,4) COMMENT '3日用户ltv:站内',
  `site_ltv_4` decimal(20,4) COMMENT '4日用户ltv:站内',
  `site_ltv_5` decimal(20,4) COMMENT '5日用户ltv:站内',
  `site_ltv_6` decimal(20,4) COMMENT '6日用户ltv:站内',
  `site_ltv_7` decimal(20,4) COMMENT '7日用户ltv:站内',
  `site_ltv_15` decimal(20,4) COMMENT '15日用户ltv:站内',
  `site_ltv_30` decimal(20,4) COMMENT '30日用户ltv:站内',
  `update_time` datetime COMMENT '数据更新时间'
) ENGINE=OLAP
UNIQUE KEY(`data_date`, `pkg_code`)
COMMENT "数数维度大表: day,pkg 维度 "
PARTITION BY RANGE(`data_date`)
(PARTITION p202106 VALUES [('1001-01-01'), ('2021-07-01')),
PARTITION p202107 VALUES [('2021-07-01'), ('2021-08-01')),
PARTITION p202108 VALUES [('2021-08-01'), ('2021-09-01')),
PARTITION p202109 VALUES [('2021-09-01'), ('2021-10-01')))
DISTRIBUTED BY HASH(`pkg_code`) BUCKETS 3
PROPERTIES (
"replication_num" = "1",
"dynamic_partition.enable" = "true",
"dynamic_partition.time_unit" = "MONTH",
"dynamic_partition.start" = "-120",
"dynamic_partition.end" = "30",
"dynamic_partition.prefix" = "p",
"dynamic_partition.buckets" = "3",
"in_memory" = "false",
"storage_format" = "DEFAULT"
);




drop table  dwm_pangle_day_site_ad_unit_kpi ;
CREATE TABLE `dwm_pangle_day_site_ad_unit_kpi` (
  `start_date` date COMMENT '数据统计时间',
  `subject_id` varchar(100) COMMENT '穿山甲项目ID',
  `site_id` varchar(100) COMMENT '穿山甲app_id',
  `ad_unit_id` varchar(100) COMMENT '穿山甲广告位ID',
  `req_cnt` bigint(20) COMMENT '预估广告请求次数',
  `api_req_cnt` bigint(20) COMMENT '广告请求Api次数',
  `ret_cnt` bigint(20) COMMENT '预估广告返回次数',
  `api_ret_cnt` bigint(20) COMMENT '广告返回Api次数',
  `imp_cnt` bigint(20) COMMENT '预估展示次数',
  `api_imp_cnt` bigint(20) COMMENT '广告展示Api次数',
  `clk_cnt` bigint(20) COMMENT '预估点击次数',
  `api_clk_cnt` bigint(20) COMMENT '点击api次数',
  `ecpm` decimal(20,4) COMMENT '预估ecpm',
  `api_ecpm` decimal(20,4) COMMENT 'api ecpm',
  `revenue` decimal(20,4) COMMENT '预估revenue',
  `api_revenue` decimal(20,4) COMMENT 'api revenue',
  `update_time` datetime COMMENT '数据更新时间'
) ENGINE=OLAP
UNIQUE KEY(`start_date`, `subject_id`,  `site_id`, `ad_unit_id`)
COMMENT "穿山甲小表聚合：day,pkg,ad_unit_id 维度 "
PARTITION BY RANGE(`start_date`)
(PARTITION p202106 VALUES [('1001-01-01'), ('2021-07-01')),
PARTITION p202107 VALUES [('2021-07-01'), ('2021-08-01')),
PARTITION p202108 VALUES [('2021-08-01'), ('2021-09-01')),
PARTITION p202109 VALUES [('2021-09-01'), ('2021-10-01')))
DISTRIBUTED BY HASH(`site_id`) BUCKETS 3
PROPERTIES (
"replication_num" = "1",
"dynamic_partition.enable" = "true",
"dynamic_partition.time_unit" = "MONTH",
"dynamic_partition.start" = "-120",
"dynamic_partition.end" = "30",
"dynamic_partition.prefix" = "p",
"dynamic_partition.buckets" = "3",
"in_memory" = "false",
"storage_format" = "DEFAULT"
);

drop table  dws_pangle_day_pkg_kpi ;
CREATE TABLE `dws_pangle_day_pkg_kpi` (
  `data_date` date COMMENT '数据统计时间',
  `pkg_code` varchar(100) COMMENT '穿山甲广告位ID',
  `req_cnt` bigint(20) COMMENT '预估广告请求次数',
  `api_req_cnt` bigint(20) COMMENT '广告请求Api次数',
  `ret_cnt` bigint(20) COMMENT '预估广告返回次数',
  `api_ret_cnt` bigint(20) COMMENT '广告返回Api次数',
  `imp_cnt` bigint(20) COMMENT '预估展示次数',
  `api_imp_cnt` bigint(20) COMMENT '广告展示Api次数',
  `clk_cnt` bigint(20) COMMENT '预估点击次数',
  `api_clk_cnt` bigint(20) COMMENT '点击api次数',
  `ecpm` decimal(20,4) COMMENT '预估ecpm',
  `api_ecpm` decimal(20,4) COMMENT 'api ecpm',
  `revenue` decimal(20,4) COMMENT '预估revenue',
  `api_revenue` decimal(20,4) COMMENT 'api revenue',
  `api_revenue_all` decimal(20,4) COMMENT '累计 revenue',
  `update_time` datetime COMMENT '数据更新时间'
) ENGINE=OLAP
UNIQUE KEY(`data_date`, `pkg_code`)
COMMENT "穿山甲大表聚合：day,pkg_code 维度 "
PARTITION BY RANGE(`data_date`)
(PARTITION p202106 VALUES [('1001-01-01'), ('2021-07-01')),
PARTITION p202107 VALUES [('2021-07-01'), ('2021-08-01')),
PARTITION p202108 VALUES [('2021-08-01'), ('2021-09-01')),
PARTITION p202109 VALUES [('2021-09-01'), ('2021-10-01')))
DISTRIBUTED BY HASH(`pkg_code`) BUCKETS 3
PROPERTIES (
"replication_num" = "1",
"dynamic_partition.enable" = "true",
"dynamic_partition.time_unit" = "MONTH",
"dynamic_partition.start" = "-120",
"dynamic_partition.end" = "30",
"dynamic_partition.prefix" = "p",
"dynamic_partition.buckets" = "3",
"in_memory" = "false",
"storage_format" = "DEFAULT"
);


drop table  dwm_ocean_day_ad_plan_kpi ;
CREATE TABLE `dwm_ocean_day_ad_plan_kpi` (
  `stat_datetime` date COMMENT '数据统计时间',
  `ad_id` varchar(100) COMMENT '计划id',
  `ad_name` varchar(512) COMMENT '计划name',
  `cost` decimal(20,4) COMMENT '展现数据-总花费',
  `show` bigint(20) COMMENT '展现数据-展示数',
  `avg_show_cost` decimal(20,4) COMMENT '展现数据-平均千次展现费用',
  `click` bigint(20) COMMENT '展现数据-点击数',
  `avg_click_cost` decimal(20,4) COMMENT '展现数据-平均点击单价',
  `ctr` decimal(20,4) COMMENT '展现数据-点击率',
  `convert` bigint(20) COMMENT '转化数据-转化数',
  `convert_cost` decimal(20,4) COMMENT '转化数据-转化成本',
  `convert_rate` decimal(20,4) COMMENT '转化数据-转化率',
  `deep_convert` bigint(20)  COMMENT '转化数据-深度转化数',
  `deep_convert_cost` decimal(20,4) COMMENT '转化数据-深度转化成本',
  `deep_convert_rate` decimal(20,4) COMMENT '转化数据-深度转化率',
  `attribution_convert` bigint(20)  COMMENT '转化数据（计费时间）-转化数（计费时间）',
  `attribution_convert_cost` decimal(20,4)  COMMENT '转化数据（计费时间）-转化成本（计费时间）',
  `attribution_deep_convert` bigint(20)  COMMENT '转化数据（计费时间）-深度转化数（计费时间）',
  `attribution_deep_convert_cost` decimal(20,4)  COMMENT '转化数据（计费时间）-深度转化成本（计费时间）',
  `download_start` bigint(20) COMMENT '应用下载广告数据-安卓下载开始数',
  `download_start_cost` decimal(20,4) COMMENT '应用下载广告数据-安卓下载开始成本',
  `download_start_rate` decimal(20,4) COMMENT '应用下载广告数据-安卓下载开始率',
  `download_finish` bigint(20) COMMENT '应用下载广告数据-安卓下载完成数',
  `download_finish_cost` decimal(20,4) COMMENT '应用下载广告数据-安卓下载完成成本',
  `download_finish_rate` decimal(20,4) COMMENT '应用下载广告数据-安卓下载完成率',
  `click_install` bigint(20) COMMENT '应用下载广告数据-点击安装数',
  `install_finish` bigint(20) COMMENT '应用下载广告数据-安卓安装完成数',
  `install_finish_cost` decimal(20,4) COMMENT '应用下载广告数据-安卓安装完成成本',
  `install_finish_rate` decimal(20,4) COMMENT '应用下载广告数据-安卓安装完成率',
  `active` bigint(20) COMMENT '应用下载广告数据-激活数',
  `active_cost` decimal(20,4) COMMENT '应用下载广告数据-激活成本',
  `active_rate` decimal(20,4) COMMENT '应用下载广告数据-激活率',
  `register` bigint(20) COMMENT '应用下载广告数据-注册数',
  `active_register_cost` decimal(20,4) COMMENT '应用下载广告数据-注册成本',
  `active_register_rate` decimal(20,4) COMMENT '应用下载广告数据-注册率',
  `update_time` datetime COMMENT '数据更新时间'
) ENGINE=OLAP
UNIQUE KEY(`stat_datetime`, `ad_id`)
COMMENT "巨量小表聚合：day,ad_plan 维度 "
PARTITION BY RANGE(`stat_datetime`)
(PARTITION p202106 VALUES [('1001-01-01'), ('2021-07-01')),
PARTITION p202107 VALUES [('2021-07-01'), ('2021-08-01')),
PARTITION p202108 VALUES [('2021-08-01'), ('2021-09-01')),
PARTITION p202109 VALUES [('2021-09-01'), ('2021-10-01')))
DISTRIBUTED BY HASH(`ad_id`) BUCKETS 3
PROPERTIES (
"replication_num" = "1",
"dynamic_partition.enable" = "true",
"dynamic_partition.time_unit" = "MONTH",
"dynamic_partition.start" = "-120",
"dynamic_partition.end" = "30",
"dynamic_partition.prefix" = "p",
"dynamic_partition.buckets" = "3",
"in_memory" = "false",
"storage_format" = "DEFAULT"
);


drop table  dws_ocean_day_pkg_kpi ;
CREATE TABLE `dws_ocean_day_pkg_kpi` (
  `data_date` date COMMENT '数据统计时间',
  `pkg_code` varchar(100) COMMENT '包名',
  `show_count` bigint(20) COMMENT '展示数',
  `cost` decimal(20,4) COMMENT '总花费',
  `active` bigint(20) COMMENT '激活数',
  `active_cost` decimal(20,4) COMMENT '激活成本',
  `ttzx_show_count` bigint(20) COMMENT '展示数:通投智选',
  `ttzx_cost` decimal(20,4) COMMENT '总花费:通投智选',
  `ttzx_active` bigint(20) COMMENT '激活数:通投智选',
  `ttzx_active_cost` decimal(20,4) COMMENT '激活成本:通投智选',
  `pangle_show_count` bigint(20) COMMENT '展示数:穿山甲',
  `pangle_cost` decimal(20,4) COMMENT '总花费:穿山甲',
  `pangle_active` bigint(20) COMMENT '激活数:穿山甲',
  `pangle_active_cost` decimal(20,4) COMMENT '激活成本:穿山甲',
  `site_show_count` bigint(20) COMMENT '展示数:站内',
  `site_cost` decimal(20,4) COMMENT '总花费:站内',
  `site_active` bigint(20) COMMENT '激活数:站内',
  `site_active_cost` decimal(20,4) COMMENT '激活成本:站内',
  `show_all_count` bigint(20) COMMENT '累计展示数',
  `cost_all` decimal(20,4) COMMENT '累计总花费',
  `active_all` bigint(20) COMMENT '累计激活数',
  `active_cost_all` decimal(20,4) COMMENT '累计激活成本',
  `ttzx_show_all_count` bigint(20) COMMENT '累计展示数:通投智选',
  `ttzx_cost_all` decimal(20,4) COMMENT '累计总花费:通投智选',
  `ttzx_active_all` bigint(20) COMMENT '累计激活数:通投智选',
  `ttzx_active_cost_all` decimal(20,4) COMMENT '累计激活成本:通投智选',
  `pangle_show_all_count` bigint(20) COMMENT '累计展示数:穿山甲',
  `pangle_cost_all` decimal(20,4) COMMENT '累计总花费:穿山甲',
  `pangle_active_all` bigint(20) COMMENT '累计激活数:穿山甲',
  `pangle_active_cost_all` decimal(20,4) COMMENT '累计激活成本:穿山甲',
  `site_show_all_count` bigint(20) COMMENT '累计展示数:站内',
  `site_cost_all` decimal(20,4) COMMENT '累计总花费:站内',
  `site_active_all` bigint(20) COMMENT '累计激活数:站内',
  `site_active_cost_all` decimal(20,4) COMMENT '累计激活成本:站内',
  `update_time` datetime COMMENT '数据更新时间'
) ENGINE=OLAP
UNIQUE KEY(`data_date`, `pkg_code`)
COMMENT "巨量小表聚合：day,ad_plan 维度 "
PARTITION BY RANGE(`data_date`)
(PARTITION p202106 VALUES [('1001-01-01'), ('2021-07-01')),
PARTITION p202107 VALUES [('2021-07-01'), ('2021-08-01')),
PARTITION p202108 VALUES [('2021-08-01'), ('2021-09-01')),
PARTITION p202109 VALUES [('2021-09-01'), ('2021-10-01')))
DISTRIBUTED BY HASH(`pkg_code`) BUCKETS 3
PROPERTIES (
"replication_num" = "1",
"dynamic_partition.enable" = "true",
"dynamic_partition.time_unit" = "MONTH",
"dynamic_partition.start" = "-120",
"dynamic_partition.end" = "30",
"dynamic_partition.prefix" = "p",
"dynamic_partition.buckets" = "3",
"in_memory" = "false",
"storage_format" = "DEFAULT"
);

select *  from  dws_ocean_day_pkg_kpi where  data_date='2021-06-09' and pkg_code ='com.game.wyqtjandroid.zibao1' ;



