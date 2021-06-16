package com.analysis.boom.jobs.pangolin.entity;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AppDataRealEntity {
    @JsonProperty("code")
    private String code;
    @JsonProperty("data")
    private DataDTO data;
    @JsonProperty("message")
    private String message;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataDTO implements Serializable {
        @JsonProperty("currency")
        private String currency;
        @JsonProperty("has_next")
        private Integer hasNext;
        @JsonProperty("total")
        private Integer total;
        @JsonProperty("report_list")
        private List<JSONObject> reportList;

        public String getCurrency() {
            return currency;
        }

        public void setCurrency(String currency) {
            this.currency = currency;
        }

        public Integer getHasNext() {
            return hasNext;
        }

        public void setHasNext(Integer hasNext) {
            this.hasNext = hasNext;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public List<JSONObject> getReportList() {
            return reportList;
        }

        public void setReportList(List<JSONObject> reportList) {
            this.reportList = reportList;
        }


    }
}
