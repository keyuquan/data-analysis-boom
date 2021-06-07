package com.analysis.booms.source.ocean.entity;

import java.io.Serializable;
import java.util.List;

@com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
public class AdPlanConfEntity {

    @com.fasterxml.jackson.annotation.JsonProperty("code")
    private Integer code;
    @com.fasterxml.jackson.annotation.JsonProperty("message")
    private String message;
    @com.fasterxml.jackson.annotation.JsonProperty("request_id")
    private String requestId;
    @com.fasterxml.jackson.annotation.JsonProperty("data")
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

    @com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
    public static class DataDTO implements Serializable {
        @com.fasterxml.jackson.annotation.JsonProperty("page_info")
        private PageInfoDTO pageInfo;
        @com.fasterxml.jackson.annotation.JsonProperty("list")
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

        @com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
        public static class PageInfoDTO implements Serializable {
            @com.fasterxml.jackson.annotation.JsonProperty("page")
            private Integer page;
            @com.fasterxml.jackson.annotation.JsonProperty("page_size")
            private Integer pageSize;
            @com.fasterxml.jackson.annotation.JsonProperty("total_page")
            private Integer totalPage;
            @com.fasterxml.jackson.annotation.JsonProperty("total_number")
            private Integer totalNumber;

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

            public Integer getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(Integer totalPage) {
                this.totalPage = totalPage;
            }

            public Integer getTotalNumber() {
                return totalNumber;
            }

            public void setTotalNumber(Integer totalNumber) {
                this.totalNumber = totalNumber;
            }
        }

        @com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
        public static class ListDTO implements Serializable {
            @com.fasterxml.jackson.annotation.JsonProperty("feed_delivery_search")
            private Object feedDeliverySearch;
            @com.fasterxml.jackson.annotation.JsonProperty("app_desc")
            private Object appDesc;
            @com.fasterxml.jackson.annotation.JsonProperty("quick_app_url")
            private Object quickAppUrl;
            @com.fasterxml.jackson.annotation.JsonProperty("audience")
            private AudienceDTO audience;
            @com.fasterxml.jackson.annotation.JsonProperty("package")
            private String packageX;
            @com.fasterxml.jackson.annotation.JsonProperty("app_thumbnails")
            private Object appThumbnails;
            @com.fasterxml.jackson.annotation.JsonProperty("end_time")
            private String endTime;
            @com.fasterxml.jackson.annotation.JsonProperty("dpa_province")
            private Object dpaProvince;
            @com.fasterxml.jackson.annotation.JsonProperty("flow_control_mode")
            private String flowControlMode;
            @com.fasterxml.jackson.annotation.JsonProperty("campaign_id")
            private Long campaignId;
            @com.fasterxml.jackson.annotation.JsonProperty("ad_create_time")
            private String adCreateTime;
            @com.fasterxml.jackson.annotation.JsonProperty("download_url")
            private String downloadUrl;
            @com.fasterxml.jackson.annotation.JsonProperty("dpa_lbs")
            private Object dpaLbs;
            @com.fasterxml.jackson.annotation.JsonProperty("opt_status")
            private String optStatus;
            @com.fasterxml.jackson.annotation.JsonProperty("app_type")
            private String appType;
            @com.fasterxml.jackson.annotation.JsonProperty("convert_id")
            private Long convertId;
            @com.fasterxml.jackson.annotation.JsonProperty("dpa_city")
            private Object dpaCity;
            @com.fasterxml.jackson.annotation.JsonProperty("form_id")
            private Object formId;
            @com.fasterxml.jackson.annotation.JsonProperty("name")
            private String name;
            @com.fasterxml.jackson.annotation.JsonProperty("status")
            private String status;
            @com.fasterxml.jackson.annotation.JsonProperty("modify_time")
            private String modifyTime;
            @com.fasterxml.jackson.annotation.JsonProperty("external_actions")
            private Object externalActions;
            @com.fasterxml.jackson.annotation.JsonProperty("dpa_recommend_type")
            private Object dpaRecommendType;
            @com.fasterxml.jackson.annotation.JsonProperty("ad_modify_time")
            private String adModifyTime;
            @com.fasterxml.jackson.annotation.JsonProperty("bid")
            private Integer bid;
            @com.fasterxml.jackson.annotation.JsonProperty("budget_mode")
            private String budgetMode;
            @com.fasterxml.jackson.annotation.JsonProperty("schedule_type")
            private String scheduleType;
            @com.fasterxml.jackson.annotation.JsonProperty("id")
            private Long id;
            @com.fasterxml.jackson.annotation.JsonProperty("start_time")
            private String startTime;
            @com.fasterxml.jackson.annotation.JsonProperty("form_index")
            private Object formIndex;
            @com.fasterxml.jackson.annotation.JsonProperty("advertiser_id")
            private Long advertiserId;
            @com.fasterxml.jackson.annotation.JsonProperty("audit_reject_reason")
            private Object auditRejectReason;
            @com.fasterxml.jackson.annotation.JsonProperty("open_url")
            private String openUrl;
            @com.fasterxml.jackson.annotation.JsonProperty("intelligent_flow_switch")
            private Object intelligentFlowSwitch;
            @com.fasterxml.jackson.annotation.JsonProperty("external_url")
            private Object externalUrl;
            @com.fasterxml.jackson.annotation.JsonProperty("cpa_bid")
            private Integer cpaBid;
            @com.fasterxml.jackson.annotation.JsonProperty("budget")
            private Integer budget;
            @com.fasterxml.jackson.annotation.JsonProperty("hide_if_converted")
            private String hideIfConverted;
            @com.fasterxml.jackson.annotation.JsonProperty("pricing")
            private String pricing;
            @com.fasterxml.jackson.annotation.JsonProperty("subscribe_url")
            private Object subscribeUrl;
            @com.fasterxml.jackson.annotation.JsonProperty("roi_goal")
            private Double roiGoal;
            @com.fasterxml.jackson.annotation.JsonProperty("inventory_type")
            private List<String> inventoryType;

            public Object getFeedDeliverySearch() {
                return feedDeliverySearch;
            }

            public void setFeedDeliverySearch(Object feedDeliverySearch) {
                this.feedDeliverySearch = feedDeliverySearch;
            }

            public Object getAppDesc() {
                return appDesc;
            }

            public void setAppDesc(Object appDesc) {
                this.appDesc = appDesc;
            }

            public Object getQuickAppUrl() {
                return quickAppUrl;
            }

            public void setQuickAppUrl(Object quickAppUrl) {
                this.quickAppUrl = quickAppUrl;
            }

            public AudienceDTO getAudience() {
                return audience;
            }

            public void setAudience(AudienceDTO audience) {
                this.audience = audience;
            }

            public String getPackageX() {
                return packageX;
            }

            public void setPackageX(String packageX) {
                this.packageX = packageX;
            }

            public Object getAppThumbnails() {
                return appThumbnails;
            }

            public void setAppThumbnails(Object appThumbnails) {
                this.appThumbnails = appThumbnails;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public Object getDpaProvince() {
                return dpaProvince;
            }

            public void setDpaProvince(Object dpaProvince) {
                this.dpaProvince = dpaProvince;
            }

            public String getFlowControlMode() {
                return flowControlMode;
            }

            public void setFlowControlMode(String flowControlMode) {
                this.flowControlMode = flowControlMode;
            }

            public Long getCampaignId() {
                return campaignId;
            }

            public void setCampaignId(Long campaignId) {
                this.campaignId = campaignId;
            }

            public String getAdCreateTime() {
                return adCreateTime;
            }

            public void setAdCreateTime(String adCreateTime) {
                this.adCreateTime = adCreateTime;
            }

            public String getDownloadUrl() {
                return downloadUrl;
            }

            public void setDownloadUrl(String downloadUrl) {
                this.downloadUrl = downloadUrl;
            }

            public Object getDpaLbs() {
                return dpaLbs;
            }

            public void setDpaLbs(Object dpaLbs) {
                this.dpaLbs = dpaLbs;
            }

            public String getOptStatus() {
                return optStatus;
            }

            public void setOptStatus(String optStatus) {
                this.optStatus = optStatus;
            }

            public String getAppType() {
                return appType;
            }

            public void setAppType(String appType) {
                this.appType = appType;
            }

            public Long getConvertId() {
                return convertId;
            }

            public void setConvertId(Long convertId) {
                this.convertId = convertId;
            }

            public Object getDpaCity() {
                return dpaCity;
            }

            public void setDpaCity(Object dpaCity) {
                this.dpaCity = dpaCity;
            }

            public Object getFormId() {
                return formId;
            }

            public void setFormId(Object formId) {
                this.formId = formId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getModifyTime() {
                return modifyTime;
            }

            public void setModifyTime(String modifyTime) {
                this.modifyTime = modifyTime;
            }

            public Object getExternalActions() {
                return externalActions;
            }

            public void setExternalActions(Object externalActions) {
                this.externalActions = externalActions;
            }

            public Object getDpaRecommendType() {
                return dpaRecommendType;
            }

            public void setDpaRecommendType(Object dpaRecommendType) {
                this.dpaRecommendType = dpaRecommendType;
            }

            public String getAdModifyTime() {
                return adModifyTime;
            }

            public void setAdModifyTime(String adModifyTime) {
                this.adModifyTime = adModifyTime;
            }

            public Integer getBid() {
                return bid;
            }

            public void setBid(Integer bid) {
                this.bid = bid;
            }

            public String getBudgetMode() {
                return budgetMode;
            }

            public void setBudgetMode(String budgetMode) {
                this.budgetMode = budgetMode;
            }

            public String getScheduleType() {
                return scheduleType;
            }

            public void setScheduleType(String scheduleType) {
                this.scheduleType = scheduleType;
            }

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public Object getFormIndex() {
                return formIndex;
            }

            public void setFormIndex(Object formIndex) {
                this.formIndex = formIndex;
            }

            public Long getAdvertiserId() {
                return advertiserId;
            }

            public void setAdvertiserId(Long advertiserId) {
                this.advertiserId = advertiserId;
            }

            public Object getAuditRejectReason() {
                return auditRejectReason;
            }

            public void setAuditRejectReason(Object auditRejectReason) {
                this.auditRejectReason = auditRejectReason;
            }

            public String getOpenUrl() {
                return openUrl;
            }

            public void setOpenUrl(String openUrl) {
                this.openUrl = openUrl;
            }

            public Object getIntelligentFlowSwitch() {
                return intelligentFlowSwitch;
            }

            public void setIntelligentFlowSwitch(Object intelligentFlowSwitch) {
                this.intelligentFlowSwitch = intelligentFlowSwitch;
            }

            public Object getExternalUrl() {
                return externalUrl;
            }

            public void setExternalUrl(Object externalUrl) {
                this.externalUrl = externalUrl;
            }

            public Integer getCpaBid() {
                return cpaBid;
            }

            public void setCpaBid(Integer cpaBid) {
                this.cpaBid = cpaBid;
            }

            public Integer getBudget() {
                return budget;
            }

            public void setBudget(Integer budget) {
                this.budget = budget;
            }

            public String getHideIfConverted() {
                return hideIfConverted;
            }

            public void setHideIfConverted(String hideIfConverted) {
                this.hideIfConverted = hideIfConverted;
            }

            public String getPricing() {
                return pricing;
            }

            public void setPricing(String pricing) {
                this.pricing = pricing;
            }

            public Object getSubscribeUrl() {
                return subscribeUrl;
            }

            public void setSubscribeUrl(Object subscribeUrl) {
                this.subscribeUrl = subscribeUrl;
            }

            public Double getRoiGoal() {
                return roiGoal;
            }

            public void setRoiGoal(Double roiGoal) {
                this.roiGoal = roiGoal;
            }

            public List<String> getInventoryType() {
                return inventoryType;
            }

            public void setInventoryType(List<String> inventoryType) {
                this.inventoryType = inventoryType;
            }

            @com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
            public static class AudienceDTO implements Serializable {
                @com.fasterxml.jackson.annotation.JsonProperty("own_aweme_number")
                private Object ownAwemeNumber;
                @com.fasterxml.jackson.annotation.JsonProperty("action")
                private ActionDTO action;
                @com.fasterxml.jackson.annotation.JsonProperty("filter_aweme_abnormal_active")
                private Object filterAwemeAbnormalActive;
                @com.fasterxml.jackson.annotation.JsonProperty("filter_aweme_fans_count")
                private Object filterAwemeFansCount;
                @com.fasterxml.jackson.annotation.JsonProperty("android_osv")
                private String androidOsv;
                @com.fasterxml.jackson.annotation.JsonProperty("dpa_local_audience")
                private Object dpaLocalAudience;
                @com.fasterxml.jackson.annotation.JsonProperty("district")
                private String district;
                @com.fasterxml.jackson.annotation.JsonProperty("location_type")
                private String locationType;
                @com.fasterxml.jackson.annotation.JsonProperty("app_category")
                private Object appCategory;
                @com.fasterxml.jackson.annotation.JsonProperty("interest_action_mode")
                private String interestActionMode;
                @com.fasterxml.jackson.annotation.JsonProperty("auto_extend_targets")
                private Object autoExtendTargets;
                @com.fasterxml.jackson.annotation.JsonProperty("gender")
                private String gender;
                @com.fasterxml.jackson.annotation.JsonProperty("ios_osv")
                private String iosOsv;
                @com.fasterxml.jackson.annotation.JsonProperty("auto_extend_enabled")
                private Integer autoExtendEnabled;
                @com.fasterxml.jackson.annotation.JsonProperty("app_behavior_target")
                private Object appBehaviorTarget;
                @com.fasterxml.jackson.annotation.JsonProperty("superior_popularity_type")
                private String superiorPopularityType;
                @com.fasterxml.jackson.annotation.JsonProperty("filter_own_aweme_fans")
                private Object filterOwnAwemeFans;
                @com.fasterxml.jackson.annotation.JsonProperty("device_type")
                private List<String> deviceType;
                @com.fasterxml.jackson.annotation.JsonProperty("platform")
                private List<String> platform;
                @com.fasterxml.jackson.annotation.JsonProperty("retargeting_tags_include")
                private List<?> retargetingTagsInclude;
                @com.fasterxml.jackson.annotation.JsonProperty("business_ids")
                private List<?> businessIds;
                @com.fasterxml.jackson.annotation.JsonProperty("interest_tags")
                private List<?> interestTags;
                @com.fasterxml.jackson.annotation.JsonProperty("aweme_fans_numbers")
                private List<?> awemeFansNumbers;
                @com.fasterxml.jackson.annotation.JsonProperty("aweme_fan_behaviors")
                private List<?> awemeFanBehaviors;
                @com.fasterxml.jackson.annotation.JsonProperty("activate_type")
                private List<?> activateType;
                @com.fasterxml.jackson.annotation.JsonProperty("interest_categories")
                private List<?> interestCategories;
                @com.fasterxml.jackson.annotation.JsonProperty("retargeting_tags_exclude")
                private List<?> retargetingTagsExclude;
                @com.fasterxml.jackson.annotation.JsonProperty("ad_tag")
                private List<?> adTag;
                @com.fasterxml.jackson.annotation.JsonProperty("interest_words")
                private List<?> interestWords;
                @com.fasterxml.jackson.annotation.JsonProperty("app_ids")
                private List<?> appIds;
                @com.fasterxml.jackson.annotation.JsonProperty("include_custom_actions")
                private List<?> includeCustomActions;
                @com.fasterxml.jackson.annotation.JsonProperty("device_brand")
                private List<?> deviceBrand;
                @com.fasterxml.jackson.annotation.JsonProperty("flow_package")
                private List<?> flowPackage;
                @com.fasterxml.jackson.annotation.JsonProperty("aweme_fan_accounts")
                private List<?> awemeFanAccounts;
                @com.fasterxml.jackson.annotation.JsonProperty("geolocation")
                private List<?> geolocation;
                @com.fasterxml.jackson.annotation.JsonProperty("exclude_flow_package")
                private List<?> excludeFlowPackage;
                @com.fasterxml.jackson.annotation.JsonProperty("aweme_fan_categories")
                private List<?> awemeFanCategories;
                @com.fasterxml.jackson.annotation.JsonProperty("user_type")
                private List<?> userType;
                @com.fasterxml.jackson.annotation.JsonProperty("launch_price")
                private List<?> launchPrice;
                @com.fasterxml.jackson.annotation.JsonProperty("city")
                private List<?> city;
                @com.fasterxml.jackson.annotation.JsonProperty("ac")
                private List<String> ac;
                @com.fasterxml.jackson.annotation.JsonProperty("article_category")
                private List<?> articleCategory;
                @com.fasterxml.jackson.annotation.JsonProperty("exclude_custom_actions")
                private List<?> excludeCustomActions;
                @com.fasterxml.jackson.annotation.JsonProperty("carrier")
                private List<?> carrier;
                @com.fasterxml.jackson.annotation.JsonProperty("age")
                private List<?> age;

                public Object getOwnAwemeNumber() {
                    return ownAwemeNumber;
                }

                public void setOwnAwemeNumber(Object ownAwemeNumber) {
                    this.ownAwemeNumber = ownAwemeNumber;
                }

                public ActionDTO getAction() {
                    return action;
                }

                public void setAction(ActionDTO action) {
                    this.action = action;
                }

                public Object getFilterAwemeAbnormalActive() {
                    return filterAwemeAbnormalActive;
                }

                public void setFilterAwemeAbnormalActive(Object filterAwemeAbnormalActive) {
                    this.filterAwemeAbnormalActive = filterAwemeAbnormalActive;
                }

                public Object getFilterAwemeFansCount() {
                    return filterAwemeFansCount;
                }

                public void setFilterAwemeFansCount(Object filterAwemeFansCount) {
                    this.filterAwemeFansCount = filterAwemeFansCount;
                }

                public String getAndroidOsv() {
                    return androidOsv;
                }

                public void setAndroidOsv(String androidOsv) {
                    this.androidOsv = androidOsv;
                }

                public Object getDpaLocalAudience() {
                    return dpaLocalAudience;
                }

                public void setDpaLocalAudience(Object dpaLocalAudience) {
                    this.dpaLocalAudience = dpaLocalAudience;
                }

                public String getDistrict() {
                    return district;
                }

                public void setDistrict(String district) {
                    this.district = district;
                }

                public String getLocationType() {
                    return locationType;
                }

                public void setLocationType(String locationType) {
                    this.locationType = locationType;
                }

                public Object getAppCategory() {
                    return appCategory;
                }

                public void setAppCategory(Object appCategory) {
                    this.appCategory = appCategory;
                }

                public String getInterestActionMode() {
                    return interestActionMode;
                }

                public void setInterestActionMode(String interestActionMode) {
                    this.interestActionMode = interestActionMode;
                }

                public Object getAutoExtendTargets() {
                    return autoExtendTargets;
                }

                public void setAutoExtendTargets(Object autoExtendTargets) {
                    this.autoExtendTargets = autoExtendTargets;
                }

                public String getGender() {
                    return gender;
                }

                public void setGender(String gender) {
                    this.gender = gender;
                }

                public String getIosOsv() {
                    return iosOsv;
                }

                public void setIosOsv(String iosOsv) {
                    this.iosOsv = iosOsv;
                }

                public Integer getAutoExtendEnabled() {
                    return autoExtendEnabled;
                }

                public void setAutoExtendEnabled(Integer autoExtendEnabled) {
                    this.autoExtendEnabled = autoExtendEnabled;
                }

                public Object getAppBehaviorTarget() {
                    return appBehaviorTarget;
                }

                public void setAppBehaviorTarget(Object appBehaviorTarget) {
                    this.appBehaviorTarget = appBehaviorTarget;
                }

                public String getSuperiorPopularityType() {
                    return superiorPopularityType;
                }

                public void setSuperiorPopularityType(String superiorPopularityType) {
                    this.superiorPopularityType = superiorPopularityType;
                }

                public Object getFilterOwnAwemeFans() {
                    return filterOwnAwemeFans;
                }

                public void setFilterOwnAwemeFans(Object filterOwnAwemeFans) {
                    this.filterOwnAwemeFans = filterOwnAwemeFans;
                }

                public List<String> getDeviceType() {
                    return deviceType;
                }

                public void setDeviceType(List<String> deviceType) {
                    this.deviceType = deviceType;
                }

                public List<String> getPlatform() {
                    return platform;
                }

                public void setPlatform(List<String> platform) {
                    this.platform = platform;
                }

                public List<?> getRetargetingTagsInclude() {
                    return retargetingTagsInclude;
                }

                public void setRetargetingTagsInclude(List<?> retargetingTagsInclude) {
                    this.retargetingTagsInclude = retargetingTagsInclude;
                }

                public List<?> getBusinessIds() {
                    return businessIds;
                }

                public void setBusinessIds(List<?> businessIds) {
                    this.businessIds = businessIds;
                }

                public List<?> getInterestTags() {
                    return interestTags;
                }

                public void setInterestTags(List<?> interestTags) {
                    this.interestTags = interestTags;
                }

                public List<?> getAwemeFansNumbers() {
                    return awemeFansNumbers;
                }

                public void setAwemeFansNumbers(List<?> awemeFansNumbers) {
                    this.awemeFansNumbers = awemeFansNumbers;
                }

                public List<?> getAwemeFanBehaviors() {
                    return awemeFanBehaviors;
                }

                public void setAwemeFanBehaviors(List<?> awemeFanBehaviors) {
                    this.awemeFanBehaviors = awemeFanBehaviors;
                }

                public List<?> getActivateType() {
                    return activateType;
                }

                public void setActivateType(List<?> activateType) {
                    this.activateType = activateType;
                }

                public List<?> getInterestCategories() {
                    return interestCategories;
                }

                public void setInterestCategories(List<?> interestCategories) {
                    this.interestCategories = interestCategories;
                }

                public List<?> getRetargetingTagsExclude() {
                    return retargetingTagsExclude;
                }

                public void setRetargetingTagsExclude(List<?> retargetingTagsExclude) {
                    this.retargetingTagsExclude = retargetingTagsExclude;
                }

                public List<?> getAdTag() {
                    return adTag;
                }

                public void setAdTag(List<?> adTag) {
                    this.adTag = adTag;
                }

                public List<?> getInterestWords() {
                    return interestWords;
                }

                public void setInterestWords(List<?> interestWords) {
                    this.interestWords = interestWords;
                }

                public List<?> getAppIds() {
                    return appIds;
                }

                public void setAppIds(List<?> appIds) {
                    this.appIds = appIds;
                }

                public List<?> getIncludeCustomActions() {
                    return includeCustomActions;
                }

                public void setIncludeCustomActions(List<?> includeCustomActions) {
                    this.includeCustomActions = includeCustomActions;
                }

                public List<?> getDeviceBrand() {
                    return deviceBrand;
                }

                public void setDeviceBrand(List<?> deviceBrand) {
                    this.deviceBrand = deviceBrand;
                }

                public List<?> getFlowPackage() {
                    return flowPackage;
                }

                public void setFlowPackage(List<?> flowPackage) {
                    this.flowPackage = flowPackage;
                }

                public List<?> getAwemeFanAccounts() {
                    return awemeFanAccounts;
                }

                public void setAwemeFanAccounts(List<?> awemeFanAccounts) {
                    this.awemeFanAccounts = awemeFanAccounts;
                }

                public List<?> getGeolocation() {
                    return geolocation;
                }

                public void setGeolocation(List<?> geolocation) {
                    this.geolocation = geolocation;
                }

                public List<?> getExcludeFlowPackage() {
                    return excludeFlowPackage;
                }

                public void setExcludeFlowPackage(List<?> excludeFlowPackage) {
                    this.excludeFlowPackage = excludeFlowPackage;
                }

                public List<?> getAwemeFanCategories() {
                    return awemeFanCategories;
                }

                public void setAwemeFanCategories(List<?> awemeFanCategories) {
                    this.awemeFanCategories = awemeFanCategories;
                }

                public List<?> getUserType() {
                    return userType;
                }

                public void setUserType(List<?> userType) {
                    this.userType = userType;
                }

                public List<?> getLaunchPrice() {
                    return launchPrice;
                }

                public void setLaunchPrice(List<?> launchPrice) {
                    this.launchPrice = launchPrice;
                }

                public List<?> getCity() {
                    return city;
                }

                public void setCity(List<?> city) {
                    this.city = city;
                }

                public List<String> getAc() {
                    return ac;
                }

                public void setAc(List<String> ac) {
                    this.ac = ac;
                }

                public List<?> getArticleCategory() {
                    return articleCategory;
                }

                public void setArticleCategory(List<?> articleCategory) {
                    this.articleCategory = articleCategory;
                }

                public List<?> getExcludeCustomActions() {
                    return excludeCustomActions;
                }

                public void setExcludeCustomActions(List<?> excludeCustomActions) {
                    this.excludeCustomActions = excludeCustomActions;
                }

                public List<?> getCarrier() {
                    return carrier;
                }

                public void setCarrier(List<?> carrier) {
                    this.carrier = carrier;
                }

                public List<?> getAge() {
                    return age;
                }

                public void setAge(List<?> age) {
                    this.age = age;
                }

                @com.fasterxml.jackson.annotation.JsonIgnoreProperties(ignoreUnknown = true)
                public static class ActionDTO implements Serializable {
                    @com.fasterxml.jackson.annotation.JsonProperty("action_days")
                    private Object actionDays;
                    @com.fasterxml.jackson.annotation.JsonProperty("action_words")
                    private Object actionWords;
                    @com.fasterxml.jackson.annotation.JsonProperty("action_scene")
                    private Object actionScene;
                    @com.fasterxml.jackson.annotation.JsonProperty("action_categories")
                    private Object actionCategories;

                    public Object getActionDays() {
                        return actionDays;
                    }

                    public void setActionDays(Object actionDays) {
                        this.actionDays = actionDays;
                    }

                    public Object getActionWords() {
                        return actionWords;
                    }

                    public void setActionWords(Object actionWords) {
                        this.actionWords = actionWords;
                    }

                    public Object getActionScene() {
                        return actionScene;
                    }

                    public void setActionScene(Object actionScene) {
                        this.actionScene = actionScene;
                    }

                    public Object getActionCategories() {
                        return actionCategories;
                    }

                    public void setActionCategories(Object actionCategories) {
                        this.actionCategories = actionCategories;
                    }
                }
            }
        }
    }
}
