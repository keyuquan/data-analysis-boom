package com.analysis.boom.jobs.utils;

import com.analysis.boom.common.conf.Config;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.List;
import java.util.Properties;

public class KafkaUtils {
    /**
     * 把数据发送到 kafka
     *
     * @param topic
     * @param key
     * @param dataList
     */
    public static void sendDataToKafka(String topic, String key, List<String> dataList) {
        Properties props = new Properties();
        props.put("bootstrap.servers", Config.KAFKA_SERVERS);
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
        for (String str : dataList) {
            producer.send(new ProducerRecord<String, String>(topic, key, str));
        }
        producer.close();
    }
}
