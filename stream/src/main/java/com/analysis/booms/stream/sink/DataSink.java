package com.analysis.booms.stream.sink;

import com.analysis.booms.common.conf.Config;
import com.dorisdb.connector.flink.DorisSink;
import com.dorisdb.connector.flink.table.DorisSinkOptions;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;

public class DataSink {

    public static SinkFunction<String> geDorisDBSink(String tableName) {
        return  DorisSink.sink(
                DorisSinkOptions.builder()
                        .withProperty("jdbc-url", Config.DORIS_URL)
                        .withProperty("load-url", Config.DORIS_LOAD_URL)
                        .withProperty("username", Config.DORIS_USER)
                        .withProperty("password", Config.DORIS_PASSWORD)
                        .withProperty("table-name", tableName)
                        .withProperty("database-name", "doris_ods")
                        .withProperty("sink.properties.format", "json")
                        .withProperty("sink.properties.strip_outer_array", "true")
                        .withProperty("sink.buffer-flush.interval-ms", "60000")
                        .withProperty("sink.buffer-flush.max-rows", "5000")
                        .build()
        );

    }
}
