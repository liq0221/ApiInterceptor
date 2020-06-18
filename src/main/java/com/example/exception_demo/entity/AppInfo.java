package com.example.exception_demo.entity;

public class AppInfo {

    /**
     * App id
     */
    private String appId;

    /**
     * api 秘钥
     */
    private String key;


    public AppInfo(String appId, String key) {
        this.appId = appId;
        this.key = key;
    }

    public AppInfo() {
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
