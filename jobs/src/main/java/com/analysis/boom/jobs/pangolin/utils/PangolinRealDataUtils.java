package com.analysis.boom.jobs.pangolin.utils;

import com.facebook.presto.jdbc.internal.apache.http.client.utils.URIBuilder;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * 穿山甲数据实时获取方法
 */
public class PangolinRealDataUtils {
    public static String report_app = "https://www.pangle.cn/union_pangle/open/api/mediation/get_daily_income_report_data";

    /**
     * 获取广告计划配置
     *
     * @return
     */
    public static String getAppDataFromPangolin(String startDate, String endDate, String userId, String roleId, String secureKey, int offset) {
        // 构造请求
        HttpEntityEnclosingRequestBase httpEntity = new HttpEntityEnclosingRequestBase() {
            @Override
            public String getMethod() {
                return "GET";
            }
        };
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        String timestamp = (new Date().getTime()) / 1000 + "";
        try {
            client = HttpClientBuilder.create().build();
            String dimensions = "date,site_id";
            String metric = "ret_cnt,req_cnt";
            String signType = "MD5";
            String version = "2.0";

            Map<String, String> map = new TreeMap();
            map.put("start_date", startDate);
            map.put("end_date", endDate);
            map.put("dimensions", dimensions);
            map.put("limit", "500");
            map.put("offset", offset + "");
//            map.put("metric", metric);
            map.put("sign_type", signType);
            map.put("timestamp", timestamp);
            map.put("user_id", userId);
            map.put("role_id", roleId);
            map.put("version", version);
            // 对参数进行排序
            Map<String, String> sortMap = sortMapByKey(map);
            URIBuilder uriBuilder = new URIBuilder(report_app);
            for (String key : sortMap.keySet()) {
                uriBuilder.setParameter(key, sortMap.get(key));
            }
            uriBuilder.setParameter("sign", signatureGen(sortMap, secureKey));
            httpEntity.setURI(uriBuilder.build());
            response = client.execute(httpEntity);
            if (response != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer result = new StringBuffer();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }
                bufferedReader.close();
                return result.toString();
            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (client != null) {
                    client.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String signatureGen(Map<String, String> sortMap, String secureKey) {
        String strSign = "";
        for (String key : sortMap.keySet()) {
            strSign = strSign + key + "=" + sortMap.get(key) + "&";
        }
        return HashUtils.MD5(strSign.substring(0, strSign.length() - 1) + secureKey);
    }

    public static Map<String, String> sortMapByKey(Map<String, String> map) {
        if (map == null || map.isEmpty()) {
            return null;
        }
        Map<String, String> sortMap = new TreeMap<String, String>(
                new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                });
        sortMap.putAll(map);
        return sortMap;
    }

    public static void main(String[] args) {
        String appDataFromPangolin = getAppDataFromPangolin("2021-06-01", "2021-06-16", "30773", "30773", "375daf3527bb7b9f2516cdb9879c4fe9",0);
        System.out.println(appDataFromPangolin);
    }

}