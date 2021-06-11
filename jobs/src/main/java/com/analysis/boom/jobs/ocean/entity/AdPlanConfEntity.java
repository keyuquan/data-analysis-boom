package com.analysis.boom.jobs.ocean.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdPlanConfEntity {
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("data")
    private DataDTO data;
    @JsonProperty("message")
    private String message;
    @JsonProperty("request_id")
    private String requestId;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataDTO implements Serializable {
        @JsonProperty("page_info")
        private PageInfoDTO pageInfo;
        @JsonProperty("list")
        private List<ListDTO> list;

        public PageInfoDTO getPageInfo() {
            return pageInfo;
        }

        public void setPageInfo(PageInfoDTO pageInfo) {
            this.pageInfo = pageInfo;
        }

        public List<ListDTO> getList() {
            return list;
        }

        public void setList(List<ListDTO> list) {
            this.list = list;
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class PageInfoDTO implements Serializable {
            @JsonProperty("total_number")
            private Integer totalNumber;
            @JsonProperty("total_page")
            private Integer totalPage;
            @JsonProperty("page")
            private Integer page;
            @JsonProperty("page_size")
            private Integer pageSize;

            public Integer getTotalNumber() {
                return totalNumber;
            }

            public void setTotalNumber(Integer totalNumber) {
                this.totalNumber = totalNumber;
            }

            public Integer getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(Integer totalPage) {
                this.totalPage = totalPage;
            }

            public Integer getPage() {
                return page;
            }

            public void setPage(Integer page) {
                this.page = page;
            }

            public Integer getPageSize() {
                return pageSize;
            }

            public void setPageSize(Integer pageSize) {
                this.pageSize = pageSize;
            }
        }

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class ListDTO extends AdPlanConfEntity implements Serializable {
            @JsonProperty("app_type")
            private String appType;
            @JsonProperty("modify_time")
            private String modifyTime;
            @JsonProperty("ad_modify_time")
            private String adModifyTime;
            @JsonProperty("advertiser_id")
            private Long advertiserId;
            @JsonProperty("download_url")
            private String downloadUrl;
            @JsonProperty("intelligent_flow_switch")
            private String intelligentFlowSwitch;
            @JsonProperty("id")
            private Long id;
            @JsonProperty("hide_if_converted")
            private String hideIfConverted;
            @JsonProperty("campaign_id")
            private Long campaignId;
            @JsonProperty("budget")
            private Double budget;
            @JsonProperty("schedule_type")
            private String scheduleType;
            @JsonProperty("package")
            private String packageX;
            @JsonProperty("flow_control_mode")
            private String flowControlMode;
            @JsonProperty("end_time")
            private String endTime;
            @JsonProperty("ad_create_time")
            private String adCreateTime;
            @JsonProperty("convert_id")
            private Long convertId;
            @JsonProperty("opt_status")
            private String optStatus;
            @JsonProperty("start_time")
            private String startTime;
            @JsonProperty("name")
            private String name;
            @JsonProperty("cpa_bid")
            private Double cpaBid;
            @JsonProperty("budget_mode")
            private String budgetMode;
            @JsonProperty("bid")
            private Double bid;
            @JsonProperty("pricing")
            private String pricing;
            @JsonProperty("feed_delivery_search")
            private String feedDeliverySearch;
            @JsonProperty("status")
            private String status;

            public String getAppType() {
                return appType;
            }

            public void setAppType(String appType) {
                this.appType = appType;
            }

            public String getModifyTime() {
                return modifyTime;
            }

            public void setModifyTime(String modifyTime) {
                this.modifyTime = modifyTime;
            }

            public String getAdModifyTime() {
                return adModifyTime;
            }

            public void setAdModifyTime(String adModifyTime) {
                this.adModifyTime = adModifyTime;
            }

            public Long getAdvertiserId() {
                return advertiserId;
            }

            public void setAdvertiserId(Long advertiserId) {
                this.advertiserId = advertiserId;
            }

            public String getDownloadUrl() {
                return downloadUrl;
            }

            public void setDownloadUrl(String downloadUrl) {
                this.downloadUrl = downloadUrl;
            }

            public String getIntelligentFlowSwitch() {
                return intelligentFlowSwitch;
            }

            public void setIntelligentFlowSwitch(String intelligentFlowSwitch) {
                this.intelligentFlowSwitch = intelligentFlowSwitch;
            }

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public String getHideIfConverted() {
                return hideIfConverted;
            }

            public void setHideIfConverted(String hideIfConverted) {
                this.hideIfConverted = hideIfConverted;
            }

            public Long getCampaignId() {
                return campaignId;
            }

            public void setCampaignId(Long campaignId) {
                this.campaignId = campaignId;
            }

            public Double getBudget() {
                return budget;
            }

            public void setBudget(Double budget) {
                this.budget = budget;
            }

            public String getScheduleType() {
                return scheduleType;
            }

            public void setScheduleType(String scheduleType) {
                this.scheduleType = scheduleType;
            }

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public String getFlowControlMode() {
                return flowControlMode;
            }

            public void setFlowControlMode(String flowControlMode) {
                this.flowControlMode = flowControlMode;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getAdCreateTime() {
                return adCreateTime;
            }

            public void setAdCreateTime(String adCreateTime) {
                this.adCreateTime = adCreateTime;
            }

            public Long getConvertId() {
                return convertId;
            }

            public void setConvertId(Long convertId) {
                this.convertId = convertId;
            }

            public String getOptStatus() {
                return optStatus;
            }

            public void setOptStatus(String optStatus) {
                this.optStatus = optStatus;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public Double getCpaBid() {
                return cpaBid;
            }

            public void setCpaBid(Double cpaBid) {
                this.cpaBid = cpaBid;
            }

            public String getBudgetMode() {
                return budgetMode;
            }

            public void setBudgetMode(String budgetMode) {
                this.budgetMode = budgetMode;
            }

            public Double getBid() {
                return bid;
            }

            public void setBid(Double bid) {
                this.bid = bid;
            }

            public String getPricing() {
                return pricing;
            }

            public void setPricing(String pricing) {
                this.pricing = pricing;
            }

            public String getFeedDeliverySearch() {
                return feedDeliverySearch;
            }

            public void setFeedDeliverySearch(String feedDeliverySearch) {
                this.feedDeliverySearch = feedDeliverySearch;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }
        }
    }
}
