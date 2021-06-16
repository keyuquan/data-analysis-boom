package com.analysis.boom.jobs.ocean.entity;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdPlanDataEntity {
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("message")
    private String message;
    @JsonProperty("request_id")
    private String requestId;
    @JsonProperty("data")
    private DataDTO data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public DataDTO getData() {
        return data;
    }

    public void setData(DataDTO data) {
        this.data = data;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataDTO implements Serializable {
        @JsonProperty("page_info")
        private PageInfoDTO pageInfo;
        @JsonProperty("list")
        private List<JSONObject> list;

        public PageInfoDTO getPageInfo() {
            return pageInfo;
        }

        public void setPageInfo(PageInfoDTO pageInfo) {
            this.pageInfo = pageInfo;
        }

        public List<JSONObject> getList() {
            return list;
        }

        public void setList(List<JSONObject> list) {
            this.list = list;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class PageInfoDTO implements Serializable {
            @JsonProperty("page_size")
            private Integer pageSize;
            @JsonProperty("total_number")
            private Integer totalNumber;
            @JsonProperty("page")
            private Integer page;
            @JsonProperty("total_page")
            private Integer totalPage;

            public Integer getPageSize() {
                return pageSize;
            }

            public void setPageSize(Integer pageSize) {
                this.pageSize = pageSize;
            }

            public Integer getTotalNumber() {
                return totalNumber;
            }

            public void setTotalNumber(Integer totalNumber) {
                this.totalNumber = totalNumber;
            }

            public Integer getPage() {
                return page;
            }

            public void setPage(Integer page) {
                this.page = page;
            }

            public Integer getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(Integer totalPage) {
                this.totalPage = totalPage;
            }
        }
    }
}
