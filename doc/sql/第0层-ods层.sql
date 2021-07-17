-- 热云数据回传
drop table  ods_trackingio_active ;
CREATE TABLE `ods_trackingio_active` (
  `active_time` datetime COMMENT '激活时间:根据activetime转换',
  `appkey` varchar(512) COMMENT '热云应用唯一标识',
  `deviceid` varchar(512)  COMMENT '设备ID',
  `spreadurl` varchar(512)  COMMENT '推广活动短链id',
  `spreadname` varchar(512)  COMMENT ' 推广活动名称',
  `channel` varchar(512)  COMMENT ' 渠道名称',
  `accountid` varchar(512)  COMMENT '广告主id',
  `ry_adgroup_id` varchar(512)  COMMENT '广告组id',
  `ry_adgroup_name` varchar(512)  COMMENT '广告组名称',
  `ry_adplan_id` varchar(512)  COMMENT ' 广告计划id',
  `ry_adplan_name` varchar(512)  COMMENT '广告计划名称',
  `ry_adcreative_id` varchar(512)  COMMENT ' 广告创意id',
  `ry_adcreative_name` varchar(512)  COMMENT '广告创意名称',
  `activetime` bigint(20) COMMENT '激活接收毫秒时间戳',
  `clicktime`  bigint(20)  COMMENT '点击接收毫秒时间戳',
  `uip` varchar(512)  COMMENT '点击IP',
  `osversion` varchar(512)  COMMENT '系统版本',
  `ryos` varchar(512)  COMMENT '操作系统',
  `devicetype` varchar(512)  COMMENT '设备类型',
  `idfa` varchar(512)  COMMENT 'iOS IDFA',
  `imei` varchar(512)  COMMENT 'Android IMEI',
  `oaid` varchar(512)  COMMENT 'Android OAID',
  `androidid` varchar(512) COMMENT 'Android AndroidID',
  `aip` varchar(512)  COMMENT '激活IP',
  `ua` varchar(512)  COMMENT 'ua',
  `skey` varchar(512)  COMMENT '安全密钥',
  `update_time` datetime COMMENT '数据更新时间'
) ENGINE=OLAP
UNIQUE KEY(`active_time`, `appkey`, `deviceid`)
COMMENT "OLAP"
PARTITION BY RANGE(`active_time`)
(PARTITION p20210601 VALUES [('1001-01-01 00:00:00'), ('2021-06-02 00:00:00')),
PARTITION p20210602 VALUES [('2021-06-02 00:00:00'), ('2021-06-03 00:00:00')),
PARTITION p20210603 VALUES [('2021-06-03 00:00:00'), ('2021-06-04 00:00:00')),
PARTITION p20210604 VALUES [('2021-06-04 00:00:00'), ('2021-06-05 00:00:00')),
PARTITION p20210605 VALUES [('2021-06-05 00:00:00'), ('2021-06-06 00:00:00')),
PARTITION p20210606 VALUES [('2021-06-06 00:00:00'), ('2021-06-07 00:00:00')),
PARTITION p20210607 VALUES [('2021-06-07 00:00:00'), ('2021-06-08 00:00:00')),
PARTITION p20210608 VALUES [('2021-06-08 00:00:00'), ('2021-06-09 00:00:00')),
PARTITION p20210609 VALUES [('2021-06-09 00:00:00'), ('2021-06-10 00:00:00')),
PARTITION p20210610 VALUES [('2021-06-10 00:00:00'), ('2021-06-11 00:00:00')),
PARTITION p20210611 VALUES [('2021-06-11 00:00:00'), ('2021-06-12 00:00:00')),
PARTITION p20210612 VALUES [('2021-06-12 00:00:00'), ('2021-06-13 00:00:00')),
PARTITION p20210613 VALUES [('2021-06-13 00:00:00'), ('2021-06-14 00:00:00')),
PARTITION p20210614 VALUES [('2021-06-14 00:00:00'), ('2021-06-15 00:00:00')),
PARTITION p20210615 VALUES [('2021-06-15 00:00:00'), ('2021-06-16 00:00:00')),
PARTITION p20210616 VALUES [('2021-06-16 00:00:00'), ('2021-06-17 00:00:00')),
PARTITION p20210617 VALUES [('2021-06-17 00:00:00'), ('2021-06-18 00:00:00')),
PARTITION p20210618 VALUES [('2021-06-18 00:00:00'), ('2021-06-19 00:00:00')),
PARTITION p20210619 VALUES [('2021-06-19 00:00:00'), ('2021-06-20 00:00:00')),
PARTITION p20210620 VALUES [('2021-06-20 00:00:00'), ('2021-06-21 00:00:00')),
PARTITION p20210621 VALUES [('2021-06-21 00:00:00'), ('2021-06-22 00:00:00')),
PARTITION p20210622 VALUES [('2021-06-22 00:00:00'), ('2021-06-23 00:00:00')),
PARTITION p20210623 VALUES [('2021-06-23 00:00:00'), ('2021-06-24 00:00:00')),
PARTITION p20210624 VALUES [('2021-06-24 00:00:00'), ('2021-06-25 00:00:00')),
PARTITION p20210625 VALUES [('2021-06-25 00:00:00'), ('2021-06-26 00:00:00')),
PARTITION p20210626 VALUES [('2021-06-26 00:00:00'), ('2021-06-27 00:00:00')),
PARTITION p20210627 VALUES [('2021-06-27 00:00:00'), ('2021-06-28 00:00:00')),
PARTITION p20210628 VALUES [('2021-06-28 00:00:00'), ('2021-06-29 00:00:00')),
PARTITION p20210629 VALUES [('2021-06-29 00:00:00'), ('2021-06-30 00:00:00')),
PARTITION p20210630 VALUES [('2021-06-30 00:00:00'), ('2021-07-01 00:00:00')),
PARTITION p20210701 VALUES [('2021-07-01 00:00:00'), ('2021-07-02 00:00:00')),
PARTITION p20210702 VALUES [('2021-07-02 00:00:00'), ('2021-07-03 00:00:00')),
PARTITION p20210703 VALUES [('2021-07-03 00:00:00'), ('2021-07-04 00:00:00')),
PARTITION p20210704 VALUES [('2021-07-04 00:00:00'), ('2021-07-05 00:00:00')),
PARTITION p20210705 VALUES [('2021-07-05 00:00:00'), ('2021-07-06 00:00:00')),
PARTITION p20210706 VALUES [('2021-07-06 00:00:00'), ('2021-07-07 00:00:00')),
PARTITION p20210707 VALUES [('2021-07-07 00:00:00'), ('2021-07-08 00:00:00')),
PARTITION p20210708 VALUES [('2021-07-08 00:00:00'), ('2021-07-09 00:00:00')),
PARTITION p20210709 VALUES [('2021-07-09 00:00:00'), ('2021-07-10 00:00:00')),
PARTITION p20210710 VALUES [('2021-07-10 00:00:00'), ('2021-07-11 00:00:00')),
PARTITION p20210711 VALUES [('2021-07-11 00:00:00'), ('2021-07-12 00:00:00')),
PARTITION p20210712 VALUES [('2021-07-12 00:00:00'), ('2021-07-13 00:00:00')),
PARTITION p20210713 VALUES [('2021-07-13 00:00:00'), ('2021-07-14 00:00:00')),
PARTITION p20210714 VALUES [('2021-07-14 00:00:00'), ('2021-07-15 00:00:00')),
PARTITION p20210715 VALUES [('2021-07-15 00:00:00'), ('2021-07-16 00:00:00')),
PARTITION p20210716 VALUES [('2021-07-16 00:00:00'), ('2021-07-17 00:00:00')),
PARTITION p20210717 VALUES [('2021-07-17 00:00:00'), ('2021-07-18 00:00:00')),
PARTITION p20210718 VALUES [('2021-07-18 00:00:00'), ('2021-07-19 00:00:00')))
DISTRIBUTED BY HASH(`deviceid`) BUCKETS 3
PROPERTIES (
"replication_num" = "1",
"dynamic_partition.enable" = "true",
"dynamic_partition.time_unit" = "DAY",
"dynamic_partition.time_zone" = "Asia/Shanghai",
"dynamic_partition.start" = "-3650",
"dynamic_partition.end" = "30",
"dynamic_partition.prefix" = "p",
"dynamic_partition.buckets" = "3",
"in_memory" = "false",
"storage_format" = "DEFAULT"
);

drop table  ods_ocean_ad_plan_conf ;
CREATE TABLE `ods_ocean_ad_plan_conf` (
  `advertiser_id` varchar(100) COMMENT '广告主id',
  `ad_id` varchar(100) COMMENT '计划id',
  `campaign_id` varchar(100) COMMENT '广告组id',
  `name` varchar(512) COMMENT '计划名字',
  `package` varchar(100) COMMENT '应用包名',
  `app_type` varchar(100) COMMENT '应用类型',
  `status` varchar(100) COMMENT '广告状态',
  `download_type` varchar(1024) COMMENT '下载方式',
  `download_url` varchar(1024) COMMENT '下载链接',
  `download_mode` varchar(1024) COMMENT '下载应用优先级',
  `inventory_type` varchar(1024) COMMENT '投放位置',
  `modify_time` varchar(100) COMMENT '计划上次修改时间标识',
  `ad_create_time` datetime COMMENT '计划创建时间',
  `ad_modify_time` datetime COMMENT '计划修改时间',
  `update_time` datetime COMMENT '数据更新时间'
) ENGINE=OLAP
UNIQUE KEY(`advertiser_id`, `ad_id`)
COMMENT "巨量广告配置表"
DISTRIBUTED BY HASH(`ad_id`) BUCKETS 3
PROPERTIES (
"replication_num" = "1",
"in_memory" = "false",
"storage_format" = "DEFAULT"
);
