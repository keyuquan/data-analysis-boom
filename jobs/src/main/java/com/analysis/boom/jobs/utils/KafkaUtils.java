package com.analysis.boom.jobs.utils;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.List;
import java.util.Properties;

public class KafkaUtils {

    private static KafkaProducer<String, String> instance = null;
    public static KafkaProducer<String, String> getInstance() {
        if (null == instance) {
            synchronized (KafkaProducer.class) {
                if (null == instance) {
                    Properties props = new Properties();
                    props.put("bootstrap.servers", "ta1:9092,ta2:9092,ta3:9092");
                    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
                    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
                    instance = new KafkaProducer<>(props);
                }

            }
        }
        return instance;
    }

    public static void sendDataToKafka(String topic, List<String> dataList) {
        if (dataList != null && dataList.size() > 0) {
            instance = getInstance();
            for (String str : dataList) {
                instance.send(new ProducerRecord<>(topic, str));
            }
        }
    }
    public static void close() {
        if (instance != null) {
            instance.close();
        }
    }
}
