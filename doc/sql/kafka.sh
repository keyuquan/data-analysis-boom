# 查看topic
bin/kafka-topics.sh --zookeeper ta1:2181,ta2:2181,ta3:2181 --list
bin/kafka-topics.sh --describe --zookeeper ta1:2181,ta2:2181,ta3:2181 --topic ta-data
# 创建topic
bin/kafka-topics.sh --zookeeper ta1:2181,ta2:2181,ta3:2181 --create --topic boom_dwm_ta_event_day_pkg_kpi  --replication-factor 2 --partitions 3
# 生产/消费数据
bin/kafka-console-producer.sh --broker-list ta1:9092,ta2:9092,ta3:9092 --topic boom_trackingio_active
bin/kafka-console-consumer.sh --bootstrap-server ta1:9092,ta2:9092,ta3:9092 --topic boom_dwm_ta_event_day_pkg_kpi
# 创建topic
bin/kafka-topics.sh --zookeeper ta1:2181,ta2:2181,ta3:2181 --delete  --topic boom_dwm_ta_event_day_pkg_retain
bin/kafka-topics.sh --zookeeper ta1:2181,ta2:2181,ta3:2181 --create --topic boom_trackingio_active  --replication-factor 2 --partitions 3
bin/kafka-topics.sh --zookeeper ta1:2181,ta2:2181,ta3:2181 --create --topic boom_dwm_ta_event_day_pkg_kpi  --replication-factor 2 --partitions 3
bin/kafka-topics.sh --zookeeper ta1:2181,ta2:2181,ta3:2181 --create --topic boom_dwm_ta_event_day_pkg_plan_kpi  --replication-factor 2 --partitions 3
bin/kafka-topics.sh --zookeeper ta1:2181,ta2:2181,ta3:2181 --create --topic boom_dwm_ta_event_day_pkg_plan_retain  --replication-factor 2 --partitions 3
bin/kafka-topics.sh --zookeeper ta1:2181,ta2:2181,ta3:2181 --create --topic boom_dwm_ta_event_day_pkg_retain  --replication-factor 2 --partitions 3
bin/kafka-topics.sh --zookeeper ta1:2181,ta2:2181,ta3:2181 --create --topic boom_dwm_pangle_day_site_ad_unit_kpi  --replication-factor 2 --partitions 3
bin/kafka-topics.sh --zookeeper ta1:2181,ta2:2181,ta3:2181 --create --topic boom_ods_ocean_ad_plan_conf  --replication-factor 2 --partitions 3
bin/kafka-topics.sh --zookeeper ta1:2181,ta2:2181,ta3:2181 --create --topic boom_dwm_ocean_day_ad_plan_kpi  --replication-factor 2 --partitions 3
bin/kafka-topics.sh --zookeeper ta1:2181,ta2:2181,ta3:2181 --create --topic boom_dwm_ocean_day_ad_plan_inventory_kpi  --replication-factor 2 --partitions 3