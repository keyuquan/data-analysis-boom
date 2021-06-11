package com.analysis.booms.stream.entity;


public class TaDataEntity {
    private String appId;
    private String clientIp;
    private DataObjectEntity dataObject;

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    public DataObjectEntity getDataObject() {
        return dataObject;
    }

    public void setDataObject(DataObjectEntity dataObject) {
        this.dataObject = dataObject;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}
