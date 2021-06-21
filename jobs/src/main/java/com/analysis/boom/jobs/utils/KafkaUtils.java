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
     * @param dataList
     */
    public static void sendDataToKafka(String topic, List<String> dataList) {
        Properties props = new Properties();
        props.put("bootstrap.servers", Config.KAFKA_SERVERS);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        for (String str : dataList) {
            producer.send(new ProducerRecord<>(topic, str));
        }
        producer.close();
    }
}

