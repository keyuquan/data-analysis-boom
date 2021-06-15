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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * 巨量平台数据同步工具类
 */
public class PangolinDataUtils {

    public static String report_user = "https://partner.oceanengine.com/union/media/open/api/report/user";

    /**
     * 获取广告计划配置
     *
     * @return
     */
    public static String getDataFromPangolin() {
        // 构造请求
        HttpEntityEnclosingRequestBase httpEntity = new HttpEntityEnclosingRequestBase() {
            @Override
            public String getMethod() {
                return "GET";
            }
        };
        CloseableHttpResponse response = null;
        CloseableHttpClient client = null;
        String time = new Date().getTime() + "";
        try {
            client = HttpClientBuilder.create().build();
            URI uri = new URIBuilder(report_user)
                    .setParameter("user_id", "30773")
                    .setParameter("timestamp", time)
                    .setParameter("nonce", "9999")
                    .setParameter("sign", signatureGen(time, "9999"))
                    .setParameter("start_date", "2021-06-14")
                    .setParameter("end_date", "2021-06-14")
                    .build();
            httpEntity.setURI(uri);

            response = client.execute(httpEntity);
            if (response != null) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
                StringBuffer result = new StringBuffer();
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    result.append(line);
                }
                bufferedReader.close();
                System.out.println();
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

    public static String signatureGen(String timestamp, String nonce) {
        List list = new ArrayList() {
        };
        list.add("375daf3527bb7b9f2516cdb9879c4fe9");
        list.add(timestamp);
        list.add(nonce);
        Collections.sort(list);
        return HashUtils.sha1(StringUtils.join(list, ""));
    }


    public static void main(String[] args) {
        System.out.println(getDataFromPangolin());
    }

}