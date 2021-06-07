package com.analysis.booms.source.ocean.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

/**
 * 巨量平台数据同步工具类
 */
public class OceanSourceUtils {

    public static String open_api_url_prefix = "https://ad.oceanengine.com/open_api/";

    /**
     * 获取广告计划配置
     *
     * @return
     */
    public static JSONObject getAdPlanConf(String access_token, String advertiser_id, String ad_create_time, int page) {
        // 拉取的字段
        final String[] fields = new String[]{"id", "name", "budget", "budget_mode", "status", "opt_status", "open_url", "modify_time", "start_time", "end_time", "bid", "advertiser_id", "pricing", "flow_control_mode", "download_url", "quick_app_url", "schedule_type", "app_type", "cpa_bid", "cpa_skip_first_phrase", "external_url", "package", "campaign_id", "ad_modify_time", "ad_create_time", "audit_reject_reason", "retargeting_type", "retargeting_tags", "convert_id", "interest_tags", "hide_if_converted", "external_actions", "device_type", "auto_extend_enabled", "auto_extend_targets", "dpa_lbs", "dpa_city", "dpa_province", "dpa_recommend_type", "roi_goal", "subscribe_url", "form_id", "form_index", "app_desc", "app_thumbnails", "feed_delivery_search", "intelligent_flow_switch"}; //查询字段集合
        // 请求参数
        final Map<String, Object> filtering = new HashMap() {
            {
                put("ad_create_time", ad_create_time);
            }
        };
        // 分页
        final int page_size = 1000;
        // 请求地址
        String uri = "2/ad/get/";
        Map<String, Object> data = new HashMap() {
            {
                put("advertiser_id", advertiser_id);
                put("page", page);
                put("page_size", page_size);
                put("filtering", null);
                put("fields", fields);
            }
        };
        // 构造请求
        HttpEntityEnclosingRequestBase httpEntity = new HttpEntityEnclosingRequestBase() {
            @Override
            public String getMethod() {
                return "GET";
            }
        };
        httpEntity.setHeader("Access-Token", access_token);
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        try {
            client = HttpClientBuilder.create().build();
            httpEntity.setURI(URI.create(open_api_url_prefix + uri));
            httpEntity.setEntity(new StringEntity(JSONObject.toJSONString(data), ContentType.APPLICATION_JSON));
            response = client.execute(httpEntity);
            if (response != null & response.getStatusLine().getStatusCode() == 200) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer result = new StringBuffer();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }
                bufferedReader.close();
                return JSONObject.parseObject(result.toString());
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}