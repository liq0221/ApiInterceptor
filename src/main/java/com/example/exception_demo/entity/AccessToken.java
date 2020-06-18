package com.example.exception_demo.entity;

import java.util.Date;

public class AccessToken {

    /**
     * token
     */
    private String token;

    /**
     * 过期时间
     */
    private Date expireTime;

    public AccessToken(String token, Date expireTime) {
        this.token = token;
        this.expireTime = expireTime;
    }

    public AccessToken() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }
}
