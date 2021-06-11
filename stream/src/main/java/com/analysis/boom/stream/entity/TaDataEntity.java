package com.analysis.boom.stream.entity;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TaDataEntity {

    @JsonProperty("appid")
    private String appid;
    @JsonProperty("client_ip")
    private String clientIp;
    @JsonProperty("data_object")
    private DataObjectDTO dataObject;
    @JsonProperty("receive_time")
    private String receiveTime;
    @JsonProperty("source")
    private String source;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public DataObjectDTO getDataObject() {
        return dataObject;
    }

    public void setDataObject(DataObjectDTO dataObject) {
        this.dataObject = dataObject;
    }

    public String getReceiveTime() {
        return receiveTime;
    }

    public void setReceiveTime(String receiveTime) {
        this.receiveTime = receiveTime;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataObjectDTO implements Serializable {
        @JsonProperty("app_id")
        private String appId;
        @JsonProperty("data")
        private List<DataDTO> data;

        public String getAppId() {
            return appId;
        }

        public void setAppId(String appId) {
            this.appId = appId;
        }

        public List<DataDTO> getData() {
            return data;
        }

        public void setData(List<DataDTO> data) {
            this.data = data;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class DataDTO implements Serializable {
            @JsonProperty("time")
            private String time;
            @JsonProperty("distinct_id")
            private String distinctId;
            @JsonProperty("uuid")
            private String uuid;
            @JsonProperty("type")
            private String type;
            @JsonProperty("event_name")
            private String eventName;
            @JsonProperty("properties")
            private JSONObject properties;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getDistinctId() {
                return distinctId;
            }

            public void setDistinctId(String distinctId) {
                this.distinctId = distinctId;
            }

            public String getUuid() {
                return uuid;
            }

            public void setUuid(String uuid) {
                this.uuid = uuid;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getEventName() {
                return eventName;
            }

            public void setEventName(String eventName) {
                this.eventName = eventName;
            }

            public JSONObject getProperties() {
                return properties;
            }

            public void setProperties(JSONObject properties) {
                this.properties = properties;
            }
        }
    }
}
