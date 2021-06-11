package com.analysis.booms.stream.entity;

import java.util.ArrayList;

public class DataObjectEntity {

    private String appId;

    private ArrayList<DataEntity>  data;


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public ArrayList<DataEntity> getData() {
        return data;
    }

    public void setData(ArrayList<DataEntity> data) {
        this.data = data;
    }
}
