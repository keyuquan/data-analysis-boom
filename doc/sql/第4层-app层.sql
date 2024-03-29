drop table  app_pkg_conf ;
CREATE TABLE `app_pkg_conf` (
 `pkg_code` varchar(100) COMMENT '包名',
 `pkg_name` varchar(100) COMMENT '应用名',
 `pkg_operator` varchar(100) COMMENT '运营人员',
 `is_earnings_ecpm` BOOLEAN COMMENT '数数是否接了变现ecpm',
 `is_thingio_data` BOOLEAN COMMENT '数数是否接了热云渠道的信息',
 `pangle_subject_id` varchar(100) COMMENT '穿山甲主体ID',
 `pangle_app_id` varchar(100) COMMENT '穿山甲APP_ID',
 `pangle_ad_unit_id` varchar(100) COMMENT '穿山甲广告位ID',
 `thingio_app_key` varchar(100) COMMENT '热云APPKEY',
 `ta_app_id` varchar(100) COMMENT '数数 APP_ID',
 `ta_project_id` varchar(100) COMMENT '数数项目id'
) ENGINE=OLAP
UNIQUE KEY(pkg_code)
COMMENT "包名跨平台配置"
DISTRIBUTED BY HASH(`pkg_code`) BUCKETS 3
PROPERTIES (
"replication_num" = "1",
"in_memory" = "false",
"storage_format" = "DEFAULT"
);


drop table  app_inventory_conf ;
CREATE TABLE `app_inventory_conf` (
 `inventory` varchar(100) COMMENT '投放广告位',
 `inventory_des` varchar(100) COMMENT '说明',
 `classify` int(10) COMMENT '分类: 1 通投,2穿山甲,站内'
) ENGINE=OLAP
UNIQUE KEY(inventory)
COMMENT "投放广告位跨平台配置"
DISTRIBUTED BY HASH(`inventory`) BUCKETS 3
PROPERTIES (
"replication_num" = "1",
"in_memory" = "false",
"storage_format" = "DEFAULT"
);


drop table  app_day_pkg_kpi ;
CREATE TABLE `app_day_pkg_kpi` (
  `data_date` date COMMENT '数据统计时间',
  `pkg_code` varchar(100) COMMENT '包名',
  `pkg_name` varchar(100) COMMENT '应用名',
  `add_user_count` bigint(20) COMMENT '新增用户数|从此开始数据来源为数数',
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
  `req_cnt` bigint(20) COMMENT '预估广告请求次数|从此开始数据来源为穿山甲',
  `api_req_cnt` bigint(20) COMMENT '广告请求Api次数',
  `ret_cnt` bigint(20) COMMENT '预估广告返回次数',
  `api_ret_cnt` bigint(20) COMMENT '广告返回Api次数',
  `imp_cnt` bigint(20) COMMENT '预估展示次数',
  `api_imp_cnt` bigint(20) COMMENT '广告展示Api次数',
  `clk_cnt` bigint(20) COMMENT '预估点击次数',
  `api_clk_cnt` bigint(20) COMMENT '点击api次数',
  `pangle_ecpm` decimal(20,4) COMMENT '预估pangle_ecpm',
  `api_ecpm` decimal(20,4) COMMENT 'api ecpm',
  `revenue` decimal(20,4) COMMENT '预估revenue',
  `api_revenue` decimal(20,4) COMMENT 'api revenue',
  `api_revenue_all` decimal(20,4) COMMENT '累计 revenue',
  `show_count` bigint(20) COMMENT '展示数|从此开始数据来源为巨量',
  `cost` decimal(20,4) COMMENT '总花费',
  `active` bigint(20) COMMENT '激活数',
  `ttzx_show_count` bigint(20) COMMENT '展示数:通投智选',
  `ttzx_cost` decimal(20,4) COMMENT '总花费:通投智选',
  `ttzx_active` bigint(20) COMMENT '激活数:通投智选',
  `pangle_show_count` bigint(20) COMMENT '展示数:穿山甲',
  `pangle_cost` decimal(20,4) COMMENT '总花费:穿山甲',
  `pangle_active` bigint(20) COMMENT '激活数:穿山甲',
  `site_show_count` bigint(20) COMMENT '展示数:站内',
  `site_cost` decimal(20,4) COMMENT '总花费:站内',
  `site_active` bigint(20) COMMENT '激活数:站内',
  `show_all_count` bigint(20) COMMENT '累计展示数',
  `cost_all` decimal(20,4) COMMENT '累计总花费',
  `active_all` bigint(20) COMMENT '累计激活数',
  `ttzx_show_all_count` bigint(20) COMMENT '累计展示数:通投智选',
  `ttzx_cost_all` decimal(20,4) COMMENT '累计总花费:通投智选',
  `ttzx_active_all` bigint(20) COMMENT '累计激活数:通投智选',
  `pangle_show_all_count` bigint(20) COMMENT '累计展示数:穿山甲',
  `pangle_cost_all` decimal(20,4) COMMENT '累计总花费:穿山甲',
  `pangle_active_all` bigint(20) COMMENT '累计激活数:穿山甲',
  `site_show_all_count` bigint(20) COMMENT '累计展示数:站内',
  `site_cost_all` decimal(20,4) COMMENT '累计总花费:站内',
  `site_active_all` bigint(20) COMMENT '累计激活数:站内',
  `update_time` datetime COMMENT '数据更新时间'
) ENGINE=OLAP
UNIQUE KEY(`data_date`, `pkg_code`)
COMMENT "聚合大表: day,pkg 维度 "
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
