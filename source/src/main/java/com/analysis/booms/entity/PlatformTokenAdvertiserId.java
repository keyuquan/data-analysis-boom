package com.analysis.booms.entity;

/**
 * 广告主ID 实体类
 */
public class PlatformTokenAdvertiserId {
    private int platform;
    private String accessToken;
    private String refreshToken;
    private int mainId;
    private String advertiserId; // 广告主ID

    public PlatformTokenAdvertiserId() {
    }

    public PlatformTokenAdvertiserId(int platform, String accessToken, String refreshToken, int mainId, String advertiserId) {
        this.platform = platform;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.mainId = mainId;
        this.advertiserId = advertiserId;
    }


    public int getPlatform() {
        return platform;
    }

    public void setPlatform(int platform) {
        this.platform = platform;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public int getMainId() {
        return mainId;
    }

    public void setMainId(int mainId) {
        this.mainId = mainId;
    }

    public String getAdvertiserId() {
        return advertiserId;
    }

    public void setAdvertiserId(String advertiserId) {
        this.advertiserId = advertiserId;
    }

    @Override
    public String toString() {
        return "PlatformTokenAdvertiserId{" +
                "platform=" + platform +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", mainId=" + mainId +
                ", advertiserId=" + advertiserId +
                '}';
    }
}
