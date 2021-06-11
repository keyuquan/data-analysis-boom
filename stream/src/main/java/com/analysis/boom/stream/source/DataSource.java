package com.analysis.boom.stream.source;

import com.analysis.boom.common.conf.Config;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

public class DataSource {
    public static FlinkKafkaConsumer<String> getKafkaSource() {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", Config.KAFKA_SERVERS);
        properties.setProperty("group.id", "flink_" + Config.ENV_ACTIVE);
        FlinkKafkaConsumer<String> kafkaSource = new FlinkKafkaConsumer<>("ta-data", new SimpleStringSchema(), properties);
        //kafkaSource.setStartFromEarliest() ; // 最早偏移量
        //kafkaSource.setStartFromGroupOffsets() ; // 本组上次消费的偏移量
        //kafkaSource.setStartFromTimestamp() ; //  时间搓(数据到达kafka的时间)
        kafkaSource.setStartFromLatest(); // 最新偏移量
        return kafkaSource;
    }


    public static DataStream<String>  getTestDataStream(StreamExecutionEnvironment env) {
        DataStream<String> dataStream = env.fromElements(new String[]{
                "{\"appid\":\"5e2efeff1c824692ae3f57a5efad3b17\",\"client_ip\":\"1.86.157.15\",\"data_object\":{\"#app_id\":\"5e2efeff1c824692ae3f57a5efad3b17\",\"data\":[{\"#time\":\"2021-06-10 15:14:01.186\",\"#distinct_id\":\"58357308b6955960\",\"#uuid\":\"c1e5e89f-1535-4527-a688-a3963326e462\",\"#type\":\"track\",\"#event_name\":\"cost_coins\",\"properties\":{\"#lib_version\":\"2.1.4\",\"coin_type\":\"金币\",\"#os\":\"Android\",\"#zone_offset\":8,\"#screen_height\":1506,\"#device_model\":\"HRY-AL00a\",\"#system_language\":\"zh\",\"#network_type\":\"4G\",\"#lib\":\"Unity\",\"platform_type\":\"Android\",\"coin_num\":437,\"coin_left\":31170,\"is_wechat_launch\":false,\"game_version\":\"1.0.2\",\"#carrier\":\"中国电信\",\"reyun_id\":\"7de77b93-fe6f-f1db-fcff-fed24dda09cb\",\"method\":\"小兵升级\",\"level\":2,\"method_type\":\"小兵变强\",\"#device_id\":\"58357308b6955960\",\"#bundle_id\":\"com.playfun.battle\",\"#screen_width\":720,\"app_name\":\"一起来打架\",\"#manufacturer\":\"HUAWEI\",\"#os_version\":\"10\",\"#app_version\":\"1.0.2\"}},{\"#time\":\"2021-06-10 15:14:01.202\",\"#distinct_id\":\"58357308b6955960\",\"#uuid\":\"d1a7de3c-c895-4d20-b08b-6576300fe2cf\",\"#type\":\"track\",\"#event_name\":\"role_levelup\",\"properties\":{\"#lib_version\":\"2.1.4\",\"#os\":\"Android\",\"#zone_offset\":8,\"role_level\":56,\"#screen_height\":1506,\"#device_model\":\"HRY-AL00a\",\"#system_language\":\"zh\",\"#network_type\":\"4G\",\"#lib\":\"Unity\",\"platform_type\":\"Android\",\"role_id\":1,\"levelup_method\":\"金币\",\"is_wechat_launch\":false,\"game_version\":\"1.0.2\",\"#carrier\":\"中国电信\",\"reyun_id\":\"7de77b93-fe6f-f1db-fcff-fed24dda09cb\",\"role_type\":\"小兵\",\"level\":2,\"#device_id\":\"58357308b6955960\",\"#bundle_id\":\"com.playfun.battle\",\"#screen_width\":720,\"role_name\":\"弓箭手\",\"app_name\":\"一起来打架\",\"#manufacturer\":\"HUAWEI\",\"#os_version\":\"10\",\"#app_version\":\"1.0.2\"}}]},\"receive_time\":\"2021-06-10 15:14:01.427\",\"source\":\"client\"}"
        });
        return dataStream;
    }

}
