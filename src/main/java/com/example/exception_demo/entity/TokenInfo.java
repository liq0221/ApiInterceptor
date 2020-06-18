package com.example.exception_demo.entity;

public class TokenInfo {


    /** token类型: api:0 、user:1 */
    private Integer tokenType;


    /** App 信息 */
    private AppInfo appInfo;


    /** 用户其他数据 */
    private UserInfo userInfo;

    public TokenInfo(Integer tokenType, AppInfo appInfo, UserInfo userInfo) {
        this.tokenType = tokenType;
        this.appInfo = appInfo;
        this.userInfo = userInfo;
    }

    public TokenInfo() {
    }

    public Integer getTokenType() {
        return tokenType;
    }

    public void setTokenType(Integer tokenType) {
        this.tokenType = tokenType;
    }

    public AppInfo getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(AppInfo appInfo) {
        this.appInfo = appInfo;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }
}
