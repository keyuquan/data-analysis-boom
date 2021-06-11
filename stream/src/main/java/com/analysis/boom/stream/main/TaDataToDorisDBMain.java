package com.analysis.boom.stream.main;

import com.alibaba.fastjson.JSONObject;
import com.analysis.boom.common.utils.DateUtils;
import com.analysis.boom.stream.entity.TaDataEntity;
import com.analysis.boom.stream.sink.DataSink;
import com.analysis.boom.stream.source.DataSource;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.ProcessFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.util.Collector;
import org.apache.flink.util.OutputTag;
import org.apache.flink.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 读取数数kafka的数据，存储在DorisDB
 */
public class TaDataToDorisDBMain {

    private static Logger logger = LoggerFactory.getLogger(TaDataToDorisDBMain.class);
    public static final OutputTag<String> EVENT_DATA = new OutputTag<String>("event_data") {
    };

    public static void main(String[] args) throws Exception {
        // 获取flink 执行器
        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        // source
        FlinkKafkaConsumer<String> kafkaSource = DataSource.getKafkaSource();
        DataStream<String> dataStream = env.addSource(kafkaSource);
        // 测试方法
        //DataStream<String> dataStream = DataSource.getTestDataStream(env);

        //process  数据处理
        SingleOutputStreamOperator<String> processStream = dataStream.process(new ProcessFunction<String, String>() {
            @Override
            public void processElement(String s, Context context, Collector<String> collector) throws Exception {
                // 去除特殊字符
                String str = s.trim().replaceAll("#", "").replaceAll("\\$", "");

                TaDataEntity taDataEntity = JSONObject.parseObject(str, TaDataEntity.class);
                // 第一层数据解析
                TaDataEntity.DataObjectDTO dataObjectEntity = taDataEntity.getDataObject();
                String appId = taDataEntity.getAppid();
                if (dataObjectEntity == null || StringUtils.isNullOrWhitespaceOnly(appId)) {
                    return;
                }
                //第二层数据解析
                List<TaDataEntity.DataObjectDTO.DataDTO> data = dataObjectEntity.getData();
                if (data == null || data.size() == 0) {
                    return;
                }
                // 第三层数据解析
                for (TaDataEntity.DataObjectDTO.DataDTO dataEntity : data) {
                    String distinctId = dataEntity.getDistinctId();
                    String time = dataEntity.getTime();
                    String eventName = dataEntity.getEventName();
                    String uuid = dataEntity.getUuid();
                    if (StringUtils.isNullOrWhitespaceOnly(uuid)) {
                        uuid = "uuid";
                    }
                    if (StringUtils.isNullOrWhitespaceOnly(distinctId) || StringUtils.isNullOrWhitespaceOnly(time) | StringUtils.isNullOrWhitespaceOnly(eventName)) {
                        continue;
                    }
                    DateUtils.addDay(DateUtils.getSysDate(), 5);
                    if (DateUtils.compareDate(time, DateUtils.addDay(DateUtils.getSysDate(), 3)) > 0) {
                        // 时间大于3天后的
                        return;
                    }

                    if (eventName.equals("user_unset") || eventName.equals("user_delete")) {
                        continue;
                    }
                    if (eventName.equals("user_set") || eventName.equals("user_setOnce") || eventName.equals("user_add") || eventName.equals("user_append")) {
                        // 用户更新数据处理

                    } else {
                        // 操作数据处理
                        JSONObject properties = dataEntity.getProperties();
                        // 把properties的数据赋值在实体
                        properties.put("event_time", time.trim());
                        properties.put("app_id", appId.trim());
                        properties.put("distinct_id", distinctId.trim());
                        properties.put("event_name", eventName.trim());
                        properties.put("uuid", uuid.trim());
                        context.output(EVENT_DATA, JSONObject.toJSONString(properties));
                    }
                }
            }
        });
        // sink
        processStream.getSideOutput(EVENT_DATA).addSink(DataSink.geDorisDBSink("ta_event")).name("ta_event");
        env.execute("ShuShuDataToDorisDB");

    }
}
