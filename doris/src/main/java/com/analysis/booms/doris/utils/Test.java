package com.analysis.booms.doris.utils;

import com.analysis.boom.common.utils.HttpUtils;

import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("token", "jHamPtd3SQ7dKeMSl4pR6ep5ypt0yDzqD4dsttDXOemfP6lmLk53qjwAV71gVDXn");
        map.put("projectId", "33");
        map.put("createParam", "{\"mainColumn\":{\"property\":{\"columnName\":\"#vp@planday\",\"tableType\":\"user\",\"timestampJoinFormat\":null}},\"columns\":[{\"property\":{\"columnName\":\"ta_id\",\"columnDesc\":\"计划加日期2\",\"selectType\":\"string\"}},{\"property\":{\"columnName\":\"activeTime\",\"columnDesc\":\"激活时间\",\"selectType\":\"date\"}},{\"property\":{\"columnName\":\"plan_id\",\"columnDesc\":\"广告计划ID\",\"selectType\":\"number\"}},{\"property\":{\"columnName\":\"plancost\",\"columnDesc\":\"消耗金额\",\"selectType\":\"number\"}},{\"property\":{\"columnName\":\"planactive\",\"columnDesc\":\"激活数\",\"selectType\":\"number\"}},{\"property\":{\"columnName\":\"planaction\",\"columnDesc\":\"转化数\",\"selectType\":\"number\"}},{\"property\":{\"columnName\":\"pkginfo\",\"columnDesc\":\"包名\",\"selectType\":\"string\"}},{\"property\":{\"columnName\":\"adImpressions\",\"columnDesc\":\"投放位置\",\"selectType\":\"string\"}}]}");
        String s = HttpUtils.doFilePost("http://47.119.177.201:8992/open/dict-create", map, "jHamPtd3SQ7dKeMSl4pR6ep5ypt0yDzqD4dsttDXOemfP6lmLk53qjwAV71gVDXn", "./report_data/v_dws_ocean_day_pkg_plan_kpi.csv");
        System.out.println("    ----------------");

        System.out.println(s);
    }

}
