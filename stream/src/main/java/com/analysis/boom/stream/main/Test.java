package com.analysis.boom.stream.main;

import com.alibaba.fastjson.JSONObject;
import com.analysis.boom.stream.entity.TaEventEntity;

public class Test {
    public static void main(String[] args) {
        String properties = "{\"ectype_name\":\"蛮荒草原_9\",\"strength\":2154.60278,\"device_model\":\"iPhone11,2\",\"lib\":\"iOS\",\"app_version\":\"3.9.6\",\"lib_version\":\"2.1.17\",\"manufacturer\":\"Apple\",\"duration\":67,\"result\":\"fail\",\"hero_level\":21,\"screen_height\":812,\"kill_num\":9,\"platform_type\":\"iOS\",\"ectype_id\":9,\"rank\":3,\"exp\":0,\"wifi\":false,\"reyun_id\":\"8FC4E846-2B4E-47CB-9272-CFA1B716BC75\",\"screen_width\":375,\"os\":\"iOS\",\"device_id\":\"8FC4E846-2B4E-47CB-9272-CFA1B716BC75\",\"level\":10,\"os_version\":\"13.4.1\",\"app_name\":\"小样哪里跑\",\"carrier\":\"中国联通\",\"hero_name\":\"典小韦\",\"is_first_day\":true,\"bundle_id\":\"com.guohao.battle\",\"network_type\":\"4G\",\"zone_offset\":8,\"total_exp\":48}";
        TaEventEntity taEventEntity = JSONObject.parseObject(properties, TaEventEntity.class);

        System.out.println("175676a8-908f-46a9-acd2-b3a0a0b3200a".length());
    }
}
