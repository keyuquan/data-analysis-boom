package com.analysis.boom.jobs.ocean.entity;

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

        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class ListDTO implements Serializable {
            @JsonProperty("pre_convert_count")
            private Integer preConvertCount;
            @JsonProperty("play_50_feed_break")
            private Integer play50FeedBreak;
            @JsonProperty("active_register_cost")
            private Double activeRegisterCost;
            @JsonProperty("pre_convert_cost")
            private Integer preConvertCost;
            @JsonProperty("in_app_pay")
            private Integer inAppPay;
            @JsonProperty("play_100_feed_break")
            private Integer play100FeedBreak;
            @JsonProperty("game_addiction")
            private Integer gameAddiction;
            @JsonProperty("game_addiction_cost")
            private Double gameAddictionCost;
            @JsonProperty("attribution_deep_convert")
            private Integer attributionDeepConvert;
            @JsonProperty("form")
            private Integer form;
            @JsonProperty("wechat_first_pay_cost")
            private Double wechatFirstPayCost;
            @JsonProperty("register")
            private Integer register;
            @JsonProperty("wifi_play")
            private Integer wifiPlay;
            @JsonProperty("game_addiction_rate")
            private Double gameAddictionRate;
            @JsonProperty("advanced_creative_form_click")
            private Integer advancedCreativeFormClick;
            @JsonProperty("install_finish_rate")
            private Double installFinishRate;
            @JsonProperty("attribution_convert_cost")
            private Double attributionConvertCost;
            @JsonProperty("location_click")
            private Integer locationClick;
            @JsonProperty("advanced_creative_phone_click")
            private Integer advancedCreativePhoneClick;
            @JsonProperty("in_app_cart")
            private Integer inAppCart;
            @JsonProperty("attribution_wechat_pay_30d_amount")
            private Double attributionWechatPay30dAmount;
            @JsonProperty("next_day_open_rate")
            private Double nextDayOpenRate;
            @JsonProperty("click_install")
            private Integer clickInstall;
            @JsonProperty("loan_completion")
            private Integer loanCompletion;
            @JsonProperty("luban_live_comment_cnt")
            private Integer lubanLiveCommentCnt;
            @JsonProperty("follow")
            private Integer follow;
            @JsonProperty("convert_cost")
            private Double convertCost;
            @JsonProperty("advanced_creative_counsel_click")
            private Integer advancedCreativeCounselClick;
            @JsonProperty("convert")
            private Integer convert;
            @JsonProperty("attribution_wechat_login_30d_cost")
            private Double attributionWechatLogin30dCost;
            @JsonProperty("attribution_wechat_first_pay_30d_cost")
            private Double attributionWechatFirstPay30dCost;
            @JsonProperty("click_landing_page")
            private Integer clickLandingPage;
            @JsonProperty("wechat_first_pay_count")
            private Integer wechatFirstPayCount;
            @JsonProperty("attribution_next_day_open_rate")
            private Double attributionNextDayOpenRate;
            @JsonProperty("wechat_pay_amount")
            private Double wechatPayAmount;
            @JsonProperty("install_finish")
            private Integer installFinish;
            @JsonProperty("total_play")
            private Integer totalPlay;
            @JsonProperty("ies_music_click")
            private Integer iesMusicClick;
            @JsonProperty("approval_count")
            private Double approvalCount;
            @JsonProperty("in_app_uv")
            private Integer inAppUv;
            @JsonProperty("convert_rate")
            private Double convertRate;
            @JsonProperty("wechat")
            private Integer wechat;
            @JsonProperty("luban_live_share_cnt")
            private Integer lubanLiveShareCnt;
            @JsonProperty("luban_order_stat_amount")
            private Double lubanOrderStatAmount;
            @JsonProperty("play_25_feed_break")
            private Integer play25FeedBreak;
            @JsonProperty("comment")
            private Integer comment;
            @JsonProperty("first_order_count")
            private Double firstOrderCount;
            @JsonProperty("redirect")
            private Integer redirect;
            @JsonProperty("advanced_creative_form_submit")
            private Integer advancedCreativeFormSubmit;
            @JsonProperty("download_start_cost")
            private Double downloadStartCost;
            @JsonProperty("click_website")
            private Integer clickWebsite;
            @JsonProperty("ies_challenge_click")
            private Integer iesChallengeClick;
            @JsonProperty("luban_live_follow_cnt")
            private Integer lubanLiveFollowCnt;
            @JsonProperty("deep_convert_cost")
            private Double deepConvertCost;
            @JsonProperty("active")
            private Integer active;
            @JsonProperty("advanced_creative_coupon_addition")
            private Integer advancedCreativeCouponAddition;
            @JsonProperty("attribution_game_pay_7d_cost")
            private Double attributionGamePay7dCost;
            @JsonProperty("stat_datetime")
            private String statDatetime;
            @JsonProperty("valid_play_rate")
            private Double validPlayRate;
            @JsonProperty("message_action")
            private Integer messageAction;
            @JsonProperty("luban_live_gift_cnt")
            private Integer lubanLiveGiftCnt;
            @JsonProperty("pre_convert_rate")
            private Double preConvertRate;
            @JsonProperty("live_fans_club_join_cnt")
            private Integer liveFansClubJoinCnt;
            @JsonProperty("attribution_next_day_open_cnt")
            private Integer attributionNextDayOpenCnt;
            @JsonProperty("id")
            private Long id;
            @JsonProperty("attribution_wechat_first_pay_30d_count")
            private Integer attributionWechatFirstPay30dCount;
            @JsonProperty("view")
            private Integer view;
            @JsonProperty("luban_live_pay_order_count")
            private Integer lubanLivePayOrderCount;
            @JsonProperty("wechat_login_count")
            private Integer wechatLoginCount;
            @JsonProperty("home_visited")
            private Integer homeVisited;
            @JsonProperty("download_finish")
            private Integer downloadFinish;
            @JsonProperty("game_pay_cost")
            private Integer gamePayCost;
            @JsonProperty("luban_live_pay_order_stat_cost")
            private Double lubanLivePayOrderStatCost;
            @JsonProperty("attribution_next_day_open_cost")
            private Double attributionNextDayOpenCost;
            @JsonProperty("attribution_game_pay_7d_count")
            private Integer attributionGamePay7dCount;
            @JsonProperty("luban_live_click_product_cnt")
            private Integer lubanLiveClickProductCnt;
            @JsonProperty("avg_show_cost")
            private Double avgShowCost;
            @JsonProperty("deep_convert")
            private Integer deepConvert;
            @JsonProperty("shopping")
            private Integer shopping;
            @JsonProperty("coupon")
            private Integer coupon;
            @JsonProperty("click_download")
            private Integer clickDownload;
            @JsonProperty("wechat_first_pay_rate")
            private Double wechatFirstPayRate;
            @JsonProperty("show")
            private Integer show;
            @JsonProperty("luban_live_enter_cnt")
            private Integer lubanLiveEnterCnt;
            @JsonProperty("attribution_deep_convert_cost")
            private Double attributionDeepConvertCost;
            @JsonProperty("campaign_id")
            private Long campaignId;
            @JsonProperty("like")
            private Integer like;
            @JsonProperty("deep_convert_rate")
            private Double deepConvertRate;
            @JsonProperty("attribution_active_pay_7d_per_count")
            private Integer attributionActivePay7dPerCount;
            @JsonProperty("attribution_wechat_pay_30d_roi")
            private Double attributionWechatPay30dRoi;
            @JsonProperty("click_shopwindow")
            private Integer clickShopwindow;
            @JsonProperty("download_finish_cost")
            private Double downloadFinishCost;
            @JsonProperty("active_pay_rate")
            private Double activePayRate;
            @JsonProperty("active_pay_cost")
            private Double activePayCost;
            @JsonProperty("pre_loan_credit_cost")
            private Double preLoanCreditCost;
            @JsonProperty("ad_name")
            private String adName;
            @JsonProperty("coupon_single_page")
            private Integer couponSinglePage;
            @JsonProperty("pre_loan_credit")
            private Integer preLoanCredit;
            @JsonProperty("button")
            private Integer button;
            @JsonProperty("consult_effective")
            private Integer consultEffective;
            @JsonProperty("share")
            private Integer share;
            @JsonProperty("ctr")
            private Double ctr;
            @JsonProperty("active_cost")
            private Double activeCost;
            @JsonProperty("attribution_convert")
            private Integer attributionConvert;
            @JsonProperty("average_play_time_per_play")
            private Double averagePlayTimePerPlay;
            @JsonProperty("click_call_dy")
            private Integer clickCallDy;
            @JsonProperty("loan_completion_cost")
            private Double loanCompletionCost;
            @JsonProperty("phone")
            private Integer phone;
            @JsonProperty("phone_effective")
            private Integer phoneEffective;
            @JsonProperty("install_finish_cost")
            private Double installFinishCost;
            @JsonProperty("click")
            private Integer click;
            @JsonProperty("lottery")
            private Integer lottery;
            @JsonProperty("play_over_rate")
            private Double playOverRate;
            @JsonProperty("active_register_rate")
            private Double activeRegisterRate;
            @JsonProperty("download")
            private Integer download;
            @JsonProperty("attribution_wechat_login_30d_count")
            private Integer attributionWechatLogin30dCount;
            @JsonProperty("in_app_order")
            private Integer inAppOrder;
            @JsonProperty("active_rate")
            private Double activeRate;
            @JsonProperty("game_pay_count")
            private Integer gamePayCount;
            @JsonProperty("phone_connect")
            private Integer phoneConnect;
            @JsonProperty("next_day_open")
            private Integer nextDayOpen;
            @JsonProperty("valid_play")
            private Integer validPlay;
            @JsonProperty("vote")
            private Integer vote;
            @JsonProperty("luban_order_cnt")
            private Integer lubanOrderCnt;
            @JsonProperty("luban_live_slidecart_click_cnt")
            private Integer lubanLiveSlidecartClickCnt;
            @JsonProperty("map_search")
            private Integer mapSearch;
            @JsonProperty("in_app_detail_uv")
            private Integer inAppDetailUv;
            @JsonProperty("cost")
            private Double cost;
            @JsonProperty("pay_count")
            private Integer payCount;
            @JsonProperty("loan_credit")
            private Integer loanCredit;
            @JsonProperty("qq")
            private Integer qq;
            @JsonProperty("ad_id")
            private Long adId;
            @JsonProperty("consult")
            private Integer consult;
            @JsonProperty("live_watch_one_minute_count")
            private Integer liveWatchOneMinuteCount;
            @JsonProperty("download_start")
            private Integer downloadStart;
            @JsonProperty("loan_credit_rate")
            private Double loanCreditRate;
            @JsonProperty("campaign_name")
            private String campaignName;
            @JsonProperty("valid_play_cost")
            private Double validPlayCost;
            @JsonProperty("download_finish_rate")
            private Double downloadFinishRate;
            @JsonProperty("luban_order_roi")
            private Double lubanOrderRoi;
            @JsonProperty("submit_certification_count")
            private Double submitCertificationCount;
            @JsonProperty("commute_first_pay_count")
            private Double commuteFirstPayCount;
            @JsonProperty("redirect_to_shop")
            private Integer redirectToShop;
            @JsonProperty("play_75_feed_break")
            private Integer play75FeedBreak;
            @JsonProperty("card_show")
            private Integer cardShow;
            @JsonProperty("play_duration_sum")
            private Integer playDurationSum;
            @JsonProperty("phone_confirm")
            private Integer phoneConfirm;
            @JsonProperty("first_rental_order_count")
            private Double firstRentalOrderCount;
            @JsonProperty("avg_click_cost")
            private Double avgClickCost;
            @JsonProperty("loan_completion_rate")
            private Double loanCompletionRate;
            @JsonProperty("message")
            private Integer message;
            @JsonProperty("download_start_rate")
            private Double downloadStartRate;
            @JsonProperty("poi_address_click")
            private Integer poiAddressClick;
            @JsonProperty("wifi_play_rate")
            private Double wifiPlayRate;
            @JsonProperty("next_day_open_cost")
            private Double nextDayOpenCost;
            @JsonProperty("wechat_login_cost")
            private Double wechatLoginCost;
            @JsonProperty("attribution_wechat_first_pay_30d_rate")
            private Double attributionWechatFirstPay30dRate;
            @JsonProperty("luban_live_gift_amount")
            private Integer lubanLiveGiftAmount;
            @JsonProperty("poi_collect")
            private Integer poiCollect;
            @JsonProperty("loan_credit_cost")
            private Double loanCreditCost;

            public Integer getPreConvertCount() {
                return preConvertCount;
            }

            public void setPreConvertCount(Integer preConvertCount) {
                this.preConvertCount = preConvertCount;
            }

            public Integer getPlay50FeedBreak() {
                return play50FeedBreak;
            }

            public void setPlay50FeedBreak(Integer play50FeedBreak) {
                this.play50FeedBreak = play50FeedBreak;
            }

            public Double getActiveRegisterCost() {
                return activeRegisterCost;
            }

            public void setActiveRegisterCost(Double activeRegisterCost) {
                this.activeRegisterCost = activeRegisterCost;
            }

            public Integer getPreConvertCost() {
                return preConvertCost;
            }

            public void setPreConvertCost(Integer preConvertCost) {
                this.preConvertCost = preConvertCost;
            }

            public Integer getInAppPay() {
                return inAppPay;
            }

            public void setInAppPay(Integer inAppPay) {
                this.inAppPay = inAppPay;
            }

            public Integer getPlay100FeedBreak() {
                return play100FeedBreak;
            }

            public void setPlay100FeedBreak(Integer play100FeedBreak) {
                this.play100FeedBreak = play100FeedBreak;
            }

            public Integer getGameAddiction() {
                return gameAddiction;
            }

            public void setGameAddiction(Integer gameAddiction) {
                this.gameAddiction = gameAddiction;
            }

            public Double getGameAddictionCost() {
                return gameAddictionCost;
            }

            public void setGameAddictionCost(Double gameAddictionCost) {
                this.gameAddictionCost = gameAddictionCost;
            }

            public Integer getAttributionDeepConvert() {
                return attributionDeepConvert;
            }

            public void setAttributionDeepConvert(Integer attributionDeepConvert) {
                this.attributionDeepConvert = attributionDeepConvert;
            }

            public Integer getForm() {
                return form;
            }

            public void setForm(Integer form) {
                this.form = form;
            }

            public Double getWechatFirstPayCost() {
                return wechatFirstPayCost;
            }

            public void setWechatFirstPayCost(Double wechatFirstPayCost) {
                this.wechatFirstPayCost = wechatFirstPayCost;
            }

            public Integer getRegister() {
                return register;
            }

            public void setRegister(Integer register) {
                this.register = register;
            }

            public Integer getWifiPlay() {
                return wifiPlay;
            }

            public void setWifiPlay(Integer wifiPlay) {
                this.wifiPlay = wifiPlay;
            }

            public Double getGameAddictionRate() {
                return gameAddictionRate;
            }

            public void setGameAddictionRate(Double gameAddictionRate) {
                this.gameAddictionRate = gameAddictionRate;
            }

            public Integer getAdvancedCreativeFormClick() {
                return advancedCreativeFormClick;
            }

            public void setAdvancedCreativeFormClick(Integer advancedCreativeFormClick) {
                this.advancedCreativeFormClick = advancedCreativeFormClick;
            }

            public Double getInstallFinishRate() {
                return installFinishRate;
            }

            public void setInstallFinishRate(Double installFinishRate) {
                this.installFinishRate = installFinishRate;
            }

            public Double getAttributionConvertCost() {
                return attributionConvertCost;
            }

            public void setAttributionConvertCost(Double attributionConvertCost) {
                this.attributionConvertCost = attributionConvertCost;
            }

            public Integer getLocationClick() {
                return locationClick;
            }

            public void setLocationClick(Integer locationClick) {
                this.locationClick = locationClick;
            }

            public Integer getAdvancedCreativePhoneClick() {
                return advancedCreativePhoneClick;
            }

            public void setAdvancedCreativePhoneClick(Integer advancedCreativePhoneClick) {
                this.advancedCreativePhoneClick = advancedCreativePhoneClick;
            }

            public Integer getInAppCart() {
                return inAppCart;
            }

            public void setInAppCart(Integer inAppCart) {
                this.inAppCart = inAppCart;
            }

            public Double getAttributionWechatPay30dAmount() {
                return attributionWechatPay30dAmount;
            }

            public void setAttributionWechatPay30dAmount(Double attributionWechatPay30dAmount) {
                this.attributionWechatPay30dAmount = attributionWechatPay30dAmount;
            }

            public Double getNextDayOpenRate() {
                return nextDayOpenRate;
            }

            public void setNextDayOpenRate(Double nextDayOpenRate) {
                this.nextDayOpenRate = nextDayOpenRate;
            }

            public Integer getClickInstall() {
                return clickInstall;
            }

            public void setClickInstall(Integer clickInstall) {
                this.clickInstall = clickInstall;
            }

            public Integer getLoanCompletion() {
                return loanCompletion;
            }

            public void setLoanCompletion(Integer loanCompletion) {
                this.loanCompletion = loanCompletion;
            }

            public Integer getLubanLiveCommentCnt() {
                return lubanLiveCommentCnt;
            }

            public void setLubanLiveCommentCnt(Integer lubanLiveCommentCnt) {
                this.lubanLiveCommentCnt = lubanLiveCommentCnt;
            }

            public Integer getFollow() {
                return follow;
            }

            public void setFollow(Integer follow) {
                this.follow = follow;
            }

            public Double getConvertCost() {
                return convertCost;
            }

            public void setConvertCost(Double convertCost) {
                this.convertCost = convertCost;
            }

            public Integer getAdvancedCreativeCounselClick() {
                return advancedCreativeCounselClick;
            }

            public void setAdvancedCreativeCounselClick(Integer advancedCreativeCounselClick) {
                this.advancedCreativeCounselClick = advancedCreativeCounselClick;
            }

            public Integer getConvert() {
                return convert;
            }

            public void setConvert(Integer convert) {
                this.convert = convert;
            }

            public Double getAttributionWechatLogin30dCost() {
                return attributionWechatLogin30dCost;
            }

            public void setAttributionWechatLogin30dCost(Double attributionWechatLogin30dCost) {
                this.attributionWechatLogin30dCost = attributionWechatLogin30dCost;
            }

            public Double getAttributionWechatFirstPay30dCost() {
                return attributionWechatFirstPay30dCost;
            }

            public void setAttributionWechatFirstPay30dCost(Double attributionWechatFirstPay30dCost) {
                this.attributionWechatFirstPay30dCost = attributionWechatFirstPay30dCost;
            }

            public Integer getClickLandingPage() {
                return clickLandingPage;
            }

            public void setClickLandingPage(Integer clickLandingPage) {
                this.clickLandingPage = clickLandingPage;
            }

            public Integer getWechatFirstPayCount() {
                return wechatFirstPayCount;
            }

            public void setWechatFirstPayCount(Integer wechatFirstPayCount) {
                this.wechatFirstPayCount = wechatFirstPayCount;
            }

            public Double getAttributionNextDayOpenRate() {
                return attributionNextDayOpenRate;
            }

            public void setAttributionNextDayOpenRate(Double attributionNextDayOpenRate) {
                this.attributionNextDayOpenRate = attributionNextDayOpenRate;
            }

            public Double getWechatPayAmount() {
                return wechatPayAmount;
            }

            public void setWechatPayAmount(Double wechatPayAmount) {
                this.wechatPayAmount = wechatPayAmount;
            }

            public Integer getInstallFinish() {
                return installFinish;
            }

            public void setInstallFinish(Integer installFinish) {
                this.installFinish = installFinish;
            }

            public Integer getTotalPlay() {
                return totalPlay;
            }

            public void setTotalPlay(Integer totalPlay) {
                this.totalPlay = totalPlay;
            }

            public Integer getIesMusicClick() {
                return iesMusicClick;
            }

            public void setIesMusicClick(Integer iesMusicClick) {
                this.iesMusicClick = iesMusicClick;
            }

            public Double getApprovalCount() {
                return approvalCount;
            }

            public void setApprovalCount(Double approvalCount) {
                this.approvalCount = approvalCount;
            }

            public Integer getInAppUv() {
                return inAppUv;
            }

            public void setInAppUv(Integer inAppUv) {
                this.inAppUv = inAppUv;
            }

            public Double getConvertRate() {
                return convertRate;
            }

            public void setConvertRate(Double convertRate) {
                this.convertRate = convertRate;
            }

            public Integer getWechat() {
                return wechat;
            }

            public void setWechat(Integer wechat) {
                this.wechat = wechat;
            }

            public Integer getLubanLiveShareCnt() {
                return lubanLiveShareCnt;
            }

            public void setLubanLiveShareCnt(Integer lubanLiveShareCnt) {
                this.lubanLiveShareCnt = lubanLiveShareCnt;
            }

            public Double getLubanOrderStatAmount() {
                return lubanOrderStatAmount;
            }

            public void setLubanOrderStatAmount(Double lubanOrderStatAmount) {
                this.lubanOrderStatAmount = lubanOrderStatAmount;
            }

            public Integer getPlay25FeedBreak() {
                return play25FeedBreak;
            }

            public void setPlay25FeedBreak(Integer play25FeedBreak) {
                this.play25FeedBreak = play25FeedBreak;
            }

            public Integer getComment() {
                return comment;
            }

            public void setComment(Integer comment) {
                this.comment = comment;
            }

            public Double getFirstOrderCount() {
                return firstOrderCount;
            }

            public void setFirstOrderCount(Double firstOrderCount) {
                this.firstOrderCount = firstOrderCount;
            }

            public Integer getRedirect() {
                return redirect;
            }

            public void setRedirect(Integer redirect) {
                this.redirect = redirect;
            }

            public Integer getAdvancedCreativeFormSubmit() {
                return advancedCreativeFormSubmit;
            }

            public void setAdvancedCreativeFormSubmit(Integer advancedCreativeFormSubmit) {
                this.advancedCreativeFormSubmit = advancedCreativeFormSubmit;
            }

            public Double getDownloadStartCost() {
                return downloadStartCost;
            }

            public void setDownloadStartCost(Double downloadStartCost) {
                this.downloadStartCost = downloadStartCost;
            }

            public Integer getClickWebsite() {
                return clickWebsite;
            }

            public void setClickWebsite(Integer clickWebsite) {
                this.clickWebsite = clickWebsite;
            }

            public Integer getIesChallengeClick() {
                return iesChallengeClick;
            }

            public void setIesChallengeClick(Integer iesChallengeClick) {
                this.iesChallengeClick = iesChallengeClick;
            }

            public Integer getLubanLiveFollowCnt() {
                return lubanLiveFollowCnt;
            }

            public void setLubanLiveFollowCnt(Integer lubanLiveFollowCnt) {
                this.lubanLiveFollowCnt = lubanLiveFollowCnt;
            }

            public Double getDeepConvertCost() {
                return deepConvertCost;
            }

            public void setDeepConvertCost(Double deepConvertCost) {
                this.deepConvertCost = deepConvertCost;
            }

            public Integer getActive() {
                return active;
            }

            public void setActive(Integer active) {
                this.active = active;
            }

            public Integer getAdvancedCreativeCouponAddition() {
                return advancedCreativeCouponAddition;
            }

            public void setAdvancedCreativeCouponAddition(Integer advancedCreativeCouponAddition) {
                this.advancedCreativeCouponAddition = advancedCreativeCouponAddition;
            }

            public Double getAttributionGamePay7dCost() {
                return attributionGamePay7dCost;
            }

            public void setAttributionGamePay7dCost(Double attributionGamePay7dCost) {
                this.attributionGamePay7dCost = attributionGamePay7dCost;
            }

            public String getStatDatetime() {
                return statDatetime;
            }

            public void setStatDatetime(String statDatetime) {
                this.statDatetime = statDatetime;
            }

            public Double getValidPlayRate() {
                return validPlayRate;
            }

            public void setValidPlayRate(Double validPlayRate) {
                this.validPlayRate = validPlayRate;
            }

            public Integer getMessageAction() {
                return messageAction;
            }

            public void setMessageAction(Integer messageAction) {
                this.messageAction = messageAction;
            }

            public Integer getLubanLiveGiftCnt() {
                return lubanLiveGiftCnt;
            }

            public void setLubanLiveGiftCnt(Integer lubanLiveGiftCnt) {
                this.lubanLiveGiftCnt = lubanLiveGiftCnt;
            }

            public Double getPreConvertRate() {
                return preConvertRate;
            }

            public void setPreConvertRate(Double preConvertRate) {
                this.preConvertRate = preConvertRate;
            }

            public Integer getLiveFansClubJoinCnt() {
                return liveFansClubJoinCnt;
            }

            public void setLiveFansClubJoinCnt(Integer liveFansClubJoinCnt) {
                this.liveFansClubJoinCnt = liveFansClubJoinCnt;
            }

            public Integer getAttributionNextDayOpenCnt() {
                return attributionNextDayOpenCnt;
            }

            public void setAttributionNextDayOpenCnt(Integer attributionNextDayOpenCnt) {
                this.attributionNextDayOpenCnt = attributionNextDayOpenCnt;
            }

            public Long getId() {
                return id;
            }

            public void setId(Long id) {
                this.id = id;
            }

            public Integer getAttributionWechatFirstPay30dCount() {
                return attributionWechatFirstPay30dCount;
            }

            public void setAttributionWechatFirstPay30dCount(Integer attributionWechatFirstPay30dCount) {
                this.attributionWechatFirstPay30dCount = attributionWechatFirstPay30dCount;
            }

            public Integer getView() {
                return view;
            }

            public void setView(Integer view) {
                this.view = view;
            }

            public Integer getLubanLivePayOrderCount() {
                return lubanLivePayOrderCount;
            }

            public void setLubanLivePayOrderCount(Integer lubanLivePayOrderCount) {
                this.lubanLivePayOrderCount = lubanLivePayOrderCount;
            }

            public Integer getWechatLoginCount() {
                return wechatLoginCount;
            }

            public void setWechatLoginCount(Integer wechatLoginCount) {
                this.wechatLoginCount = wechatLoginCount;
            }

            public Integer getHomeVisited() {
                return homeVisited;
            }

            public void setHomeVisited(Integer homeVisited) {
                this.homeVisited = homeVisited;
            }

            public Integer getDownloadFinish() {
                return downloadFinish;
            }

            public void setDownloadFinish(Integer downloadFinish) {
                this.downloadFinish = downloadFinish;
            }

            public Integer getGamePayCost() {
                return gamePayCost;
            }

            public void setGamePayCost(Integer gamePayCost) {
                this.gamePayCost = gamePayCost;
            }

            public Double getLubanLivePayOrderStatCost() {
                return lubanLivePayOrderStatCost;
            }

            public void setLubanLivePayOrderStatCost(Double lubanLivePayOrderStatCost) {
                this.lubanLivePayOrderStatCost = lubanLivePayOrderStatCost;
            }

            public Double getAttributionNextDayOpenCost() {
                return attributionNextDayOpenCost;
            }

            public void setAttributionNextDayOpenCost(Double attributionNextDayOpenCost) {
                this.attributionNextDayOpenCost = attributionNextDayOpenCost;
            }

            public Integer getAttributionGamePay7dCount() {
                return attributionGamePay7dCount;
            }

            public void setAttributionGamePay7dCount(Integer attributionGamePay7dCount) {
                this.attributionGamePay7dCount = attributionGamePay7dCount;
            }

            public Integer getLubanLiveClickProductCnt() {
                return lubanLiveClickProductCnt;
            }

            public void setLubanLiveClickProductCnt(Integer lubanLiveClickProductCnt) {
                this.lubanLiveClickProductCnt = lubanLiveClickProductCnt;
            }

            public Double getAvgShowCost() {
                return avgShowCost;
            }

            public void setAvgShowCost(Double avgShowCost) {
                this.avgShowCost = avgShowCost;
            }

            public Integer getDeepConvert() {
                return deepConvert;
            }

            public void setDeepConvert(Integer deepConvert) {
                this.deepConvert = deepConvert;
            }

            public Integer getShopping() {
                return shopping;
            }

            public void setShopping(Integer shopping) {
                this.shopping = shopping;
            }

            public Integer getCoupon() {
                return coupon;
            }

            public void setCoupon(Integer coupon) {
                this.coupon = coupon;
            }

            public Integer getClickDownload() {
                return clickDownload;
            }

            public void setClickDownload(Integer clickDownload) {
                this.clickDownload = clickDownload;
            }

            public Double getWechatFirstPayRate() {
                return wechatFirstPayRate;
            }

            public void setWechatFirstPayRate(Double wechatFirstPayRate) {
                this.wechatFirstPayRate = wechatFirstPayRate;
            }

            public Integer getShow() {
                return show;
            }

            public void setShow(Integer show) {
                this.show = show;
            }

            public Integer getLubanLiveEnterCnt() {
                return lubanLiveEnterCnt;
            }

            public void setLubanLiveEnterCnt(Integer lubanLiveEnterCnt) {
                this.lubanLiveEnterCnt = lubanLiveEnterCnt;
            }

            public Double getAttributionDeepConvertCost() {
                return attributionDeepConvertCost;
            }

            public void setAttributionDeepConvertCost(Double attributionDeepConvertCost) {
                this.attributionDeepConvertCost = attributionDeepConvertCost;
            }

            public Long getCampaignId() {
                return campaignId;
            }

            public void setCampaignId(Long campaignId) {
                this.campaignId = campaignId;
            }

            public Integer getLike() {
                return like;
            }

            public void setLike(Integer like) {
                this.like = like;
            }

            public Double getDeepConvertRate() {
                return deepConvertRate;
            }

            public void setDeepConvertRate(Double deepConvertRate) {
                this.deepConvertRate = deepConvertRate;
            }

            public Integer getAttributionActivePay7dPerCount() {
                return attributionActivePay7dPerCount;
            }

            public void setAttributionActivePay7dPerCount(Integer attributionActivePay7dPerCount) {
                this.attributionActivePay7dPerCount = attributionActivePay7dPerCount;
            }

            public Double getAttributionWechatPay30dRoi() {
                return attributionWechatPay30dRoi;
            }

            public void setAttributionWechatPay30dRoi(Double attributionWechatPay30dRoi) {
                this.attributionWechatPay30dRoi = attributionWechatPay30dRoi;
            }

            public Integer getClickShopwindow() {
                return clickShopwindow;
            }

            public void setClickShopwindow(Integer clickShopwindow) {
                this.clickShopwindow = clickShopwindow;
            }

            public Double getDownloadFinishCost() {
                return downloadFinishCost;
            }

            public void setDownloadFinishCost(Double downloadFinishCost) {
                this.downloadFinishCost = downloadFinishCost;
            }

            public Double getActivePayRate() {
                return activePayRate;
            }

            public void setActivePayRate(Double activePayRate) {
                this.activePayRate = activePayRate;
            }

            public Double getActivePayCost() {
                return activePayCost;
            }

            public void setActivePayCost(Double activePayCost) {
                this.activePayCost = activePayCost;
            }

            public Double getPreLoanCreditCost() {
                return preLoanCreditCost;
            }

            public void setPreLoanCreditCost(Double preLoanCreditCost) {
                this.preLoanCreditCost = preLoanCreditCost;
            }

            public String getAdName() {
                return adName;
            }

            public void setAdName(String adName) {
                this.adName = adName;
            }

            public Integer getCouponSinglePage() {
                return couponSinglePage;
            }

            public void setCouponSinglePage(Integer couponSinglePage) {
                this.couponSinglePage = couponSinglePage;
            }

            public Integer getPreLoanCredit() {
                return preLoanCredit;
            }

            public void setPreLoanCredit(Integer preLoanCredit) {
                this.preLoanCredit = preLoanCredit;
            }

            public Integer getButton() {
                return button;
            }

            public void setButton(Integer button) {
                this.button = button;
            }

            public Integer getConsultEffective() {
                return consultEffective;
            }

            public void setConsultEffective(Integer consultEffective) {
                this.consultEffective = consultEffective;
            }

            public Integer getShare() {
                return share;
            }

            public void setShare(Integer share) {
                this.share = share;
            }

            public Double getCtr() {
                return ctr;
            }

            public void setCtr(Double ctr) {
                this.ctr = ctr;
            }

            public Double getActiveCost() {
                return activeCost;
            }

            public void setActiveCost(Double activeCost) {
                this.activeCost = activeCost;
            }

            public Integer getAttributionConvert() {
                return attributionConvert;
            }

            public void setAttributionConvert(Integer attributionConvert) {
                this.attributionConvert = attributionConvert;
            }

            public Double getAveragePlayTimePerPlay() {
                return averagePlayTimePerPlay;
            }

            public void setAveragePlayTimePerPlay(Double averagePlayTimePerPlay) {
                this.averagePlayTimePerPlay = averagePlayTimePerPlay;
            }

            public Integer getClickCallDy() {
                return clickCallDy;
            }

            public void setClickCallDy(Integer clickCallDy) {
                this.clickCallDy = clickCallDy;
            }

            public Double getLoanCompletionCost() {
                return loanCompletionCost;
            }

            public void setLoanCompletionCost(Double loanCompletionCost) {
                this.loanCompletionCost = loanCompletionCost;
            }

            public Integer getPhone() {
                return phone;
            }

            public void setPhone(Integer phone) {
                this.phone = phone;
            }

            public Integer getPhoneEffective() {
                return phoneEffective;
            }

            public void setPhoneEffective(Integer phoneEffective) {
                this.phoneEffective = phoneEffective;
            }

            public Double getInstallFinishCost() {
                return installFinishCost;
            }

            public void setInstallFinishCost(Double installFinishCost) {
                this.installFinishCost = installFinishCost;
            }

            public Integer getClick() {
                return click;
            }

            public void setClick(Integer click) {
                this.click = click;
            }

            public Integer getLottery() {
                return lottery;
            }

            public void setLottery(Integer lottery) {
                this.lottery = lottery;
            }

            public Double getPlayOverRate() {
                return playOverRate;
            }

            public void setPlayOverRate(Double playOverRate) {
                this.playOverRate = playOverRate;
            }

            public Double getActiveRegisterRate() {
                return activeRegisterRate;
            }

            public void setActiveRegisterRate(Double activeRegisterRate) {
                this.activeRegisterRate = activeRegisterRate;
            }

            public Integer getDownload() {
                return download;
            }

            public void setDownload(Integer download) {
                this.download = download;
            }

            public Integer getAttributionWechatLogin30dCount() {
                return attributionWechatLogin30dCount;
            }

            public void setAttributionWechatLogin30dCount(Integer attributionWechatLogin30dCount) {
                this.attributionWechatLogin30dCount = attributionWechatLogin30dCount;
            }

            public Integer getInAppOrder() {
                return inAppOrder;
            }

            public void setInAppOrder(Integer inAppOrder) {
                this.inAppOrder = inAppOrder;
            }

            public Double getActiveRate() {
                return activeRate;
            }

            public void setActiveRate(Double activeRate) {
                this.activeRate = activeRate;
            }

            public Integer getGamePayCount() {
                return gamePayCount;
            }

            public void setGamePayCount(Integer gamePayCount) {
                this.gamePayCount = gamePayCount;
            }

            public Integer getPhoneConnect() {
                return phoneConnect;
            }

            public void setPhoneConnect(Integer phoneConnect) {
                this.phoneConnect = phoneConnect;
            }

            public Integer getNextDayOpen() {
                return nextDayOpen;
            }

            public void setNextDayOpen(Integer nextDayOpen) {
                this.nextDayOpen = nextDayOpen;
            }

            public Integer getValidPlay() {
                return validPlay;
            }

            public void setValidPlay(Integer validPlay) {
                this.validPlay = validPlay;
            }

            public Integer getVote() {
                return vote;
            }

            public void setVote(Integer vote) {
                this.vote = vote;
            }

            public Integer getLubanOrderCnt() {
                return lubanOrderCnt;
            }

            public void setLubanOrderCnt(Integer lubanOrderCnt) {
                this.lubanOrderCnt = lubanOrderCnt;
            }

            public Integer getLubanLiveSlidecartClickCnt() {
                return lubanLiveSlidecartClickCnt;
            }

            public void setLubanLiveSlidecartClickCnt(Integer lubanLiveSlidecartClickCnt) {
                this.lubanLiveSlidecartClickCnt = lubanLiveSlidecartClickCnt;
            }

            public Integer getMapSearch() {
                return mapSearch;
            }

            public void setMapSearch(Integer mapSearch) {
                this.mapSearch = mapSearch;
            }

            public Integer getInAppDetailUv() {
                return inAppDetailUv;
            }

            public void setInAppDetailUv(Integer inAppDetailUv) {
                this.inAppDetailUv = inAppDetailUv;
            }

            public Double getCost() {
                return cost;
            }

            public void setCost(Double cost) {
                this.cost = cost;
            }

            public Integer getPayCount() {
                return payCount;
            }

            public void setPayCount(Integer payCount) {
                this.payCount = payCount;
            }

            public Integer getLoanCredit() {
                return loanCredit;
            }

            public void setLoanCredit(Integer loanCredit) {
                this.loanCredit = loanCredit;
            }

            public Integer getQq() {
                return qq;
            }

            public void setQq(Integer qq) {
                this.qq = qq;
            }

            public Long getAdId() {
                return adId;
            }

            public void setAdId(Long adId) {
                this.adId = adId;
            }

            public Integer getConsult() {
                return consult;
            }

            public void setConsult(Integer consult) {
                this.consult = consult;
            }

            public Integer getLiveWatchOneMinuteCount() {
                return liveWatchOneMinuteCount;
            }

            public void setLiveWatchOneMinuteCount(Integer liveWatchOneMinuteCount) {
                this.liveWatchOneMinuteCount = liveWatchOneMinuteCount;
            }

            public Integer getDownloadStart() {
                return downloadStart;
            }

            public void setDownloadStart(Integer downloadStart) {
                this.downloadStart = downloadStart;
            }

            public Double getLoanCreditRate() {
                return loanCreditRate;
            }

            public void setLoanCreditRate(Double loanCreditRate) {
                this.loanCreditRate = loanCreditRate;
            }

            public String getCampaignName() {
                return campaignName;
            }

            public void setCampaignName(String campaignName) {
                this.campaignName = campaignName;
            }

            public Double getValidPlayCost() {
                return validPlayCost;
            }

            public void setValidPlayCost(Double validPlayCost) {
                this.validPlayCost = validPlayCost;
            }

            public Double getDownloadFinishRate() {
                return downloadFinishRate;
            }

            public void setDownloadFinishRate(Double downloadFinishRate) {
                this.downloadFinishRate = downloadFinishRate;
            }

            public Double getLubanOrderRoi() {
                return lubanOrderRoi;
            }

            public void setLubanOrderRoi(Double lubanOrderRoi) {
                this.lubanOrderRoi = lubanOrderRoi;
            }

            public Double getSubmitCertificationCount() {
                return submitCertificationCount;
            }

            public void setSubmitCertificationCount(Double submitCertificationCount) {
                this.submitCertificationCount = submitCertificationCount;
            }

            public Double getCommuteFirstPayCount() {
                return commuteFirstPayCount;
            }

            public void setCommuteFirstPayCount(Double commuteFirstPayCount) {
                this.commuteFirstPayCount = commuteFirstPayCount;
            }

            public Integer getRedirectToShop() {
                return redirectToShop;
            }

            public void setRedirectToShop(Integer redirectToShop) {
                this.redirectToShop = redirectToShop;
            }

            public Integer getPlay75FeedBreak() {
                return play75FeedBreak;
            }

            public void setPlay75FeedBreak(Integer play75FeedBreak) {
                this.play75FeedBreak = play75FeedBreak;
            }

            public Integer getCardShow() {
                return cardShow;
            }

            public void setCardShow(Integer cardShow) {
                this.cardShow = cardShow;
            }

            public Integer getPlayDurationSum() {
                return playDurationSum;
            }

            public void setPlayDurationSum(Integer playDurationSum) {
                this.playDurationSum = playDurationSum;
            }

            public Integer getPhoneConfirm() {
                return phoneConfirm;
            }

            public void setPhoneConfirm(Integer phoneConfirm) {
                this.phoneConfirm = phoneConfirm;
            }

            public Double getFirstRentalOrderCount() {
                return firstRentalOrderCount;
            }

            public void setFirstRentalOrderCount(Double firstRentalOrderCount) {
                this.firstRentalOrderCount = firstRentalOrderCount;
            }

            public Double getAvgClickCost() {
                return avgClickCost;
            }

            public void setAvgClickCost(Double avgClickCost) {
                this.avgClickCost = avgClickCost;
            }

            public Double getLoanCompletionRate() {
                return loanCompletionRate;
            }

            public void setLoanCompletionRate(Double loanCompletionRate) {
                this.loanCompletionRate = loanCompletionRate;
            }

            public Integer getMessage() {
                return message;
            }

            public void setMessage(Integer message) {
                this.message = message;
            }

            public Double getDownloadStartRate() {
                return downloadStartRate;
            }

            public void setDownloadStartRate(Double downloadStartRate) {
                this.downloadStartRate = downloadStartRate;
            }

            public Integer getPoiAddressClick() {
                return poiAddressClick;
            }

            public void setPoiAddressClick(Integer poiAddressClick) {
                this.poiAddressClick = poiAddressClick;
            }

            public Double getWifiPlayRate() {
                return wifiPlayRate;
            }

            public void setWifiPlayRate(Double wifiPlayRate) {
                this.wifiPlayRate = wifiPlayRate;
            }

            public Double getNextDayOpenCost() {
                return nextDayOpenCost;
            }

            public void setNextDayOpenCost(Double nextDayOpenCost) {
                this.nextDayOpenCost = nextDayOpenCost;
            }

            public Double getWechatLoginCost() {
                return wechatLoginCost;
            }

            public void setWechatLoginCost(Double wechatLoginCost) {
                this.wechatLoginCost = wechatLoginCost;
            }

            public Double getAttributionWechatFirstPay30dRate() {
                return attributionWechatFirstPay30dRate;
            }

            public void setAttributionWechatFirstPay30dRate(Double attributionWechatFirstPay30dRate) {
                this.attributionWechatFirstPay30dRate = attributionWechatFirstPay30dRate;
            }

            public Integer getLubanLiveGiftAmount() {
                return lubanLiveGiftAmount;
            }

            public void setLubanLiveGiftAmount(Integer lubanLiveGiftAmount) {
                this.lubanLiveGiftAmount = lubanLiveGiftAmount;
            }

            public Integer getPoiCollect() {
                return poiCollect;
            }

            public void setPoiCollect(Integer poiCollect) {
                this.poiCollect = poiCollect;
            }

            public Double getLoanCreditCost() {
                return loanCreditCost;
            }

            public void setLoanCreditCost(Double loanCreditCost) {
                this.loanCreditCost = loanCreditCost;
            }
        }
    }
}
