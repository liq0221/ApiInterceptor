package com.example.exception_demo.controller;

import com.example.exception_demo.annotation.NotRepeatSubmit;
import com.example.exception_demo.aop.Calculator;
import com.example.exception_demo.entity.*;
import com.example.exception_demo.utils.MD5Util;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.Appinfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("api/token")
public class TokenController {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * api获取token
     * @param appId
     * @param timestamp
     * @param sign
     */
    @PostMapping("/api_token")
    public ApiResponse<AccessToken> apiToken(String appId, @RequestHeader("timestamp") String timestamp
            ,@RequestHeader("sign") String sign) {

        Assert.isTrue(!StringUtils.isEmpty(appId) && !StringUtils.isEmpty(timestamp)
                        && !StringUtils.isEmpty(sign)
                ,ApiCodeEnum.PARAMETER_ERROR.getMsg());

        long requestInterval = System.currentTimeMillis() - Long.valueOf(timestamp);

        Assert.isTrue(requestInterval < 5  * 60 * 1000, ApiCodeEnum.REQUEST_TIMEOUT.getMsg());
        //实际业务根据appid从数据库查询appsecret
        AppInfo appInfo = new AppInfo("1","123456654321");

        //校验签名
        String signStr = timestamp + appId + appInfo.getKey();
        String signature = MD5Util.encode(signStr);
        System.out.println("signature=" + signature);
        Assert.isTrue(signature.equals(sign),ApiCodeEnum.PARAMETER_ERROR.getMsg());

        //保存token到redis
        AccessToken accessToken = this.saveToken(0,appInfo,null);
        return ApiResponse.success(accessToken);
    }

    public static void main(String[] args) {
        long timestamp = System.currentTimeMillis();
        System.out.println(timestamp);
        //a897ab0547afd0e65c73c83a41452dd1
        //a8e0fe973c98288eea00b75dbd1741c3
        String signString = timestamp + "1" + "123456654321";
        String sign = MD5Util.encode(signString);
        System.out.println(sign);
    }
    /**
     * 用户登录获取token
     * @param username
     * @param password
     * @return
     */
    @NotRepeatSubmit(5000)
    @PostMapping("/user_token")
    public ApiResponse<AccessToken> userToken(String username,String password) {

        Assert.isTrue(!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)
                ,ApiCodeEnum.PARAMETER_ERROR.getMsg());
        //根据username查询密码，和传过来的password进行比较
        UserInfo userInfo = new UserInfo(username,"81255cb0dca1a5f304328a70ac85abcd","123456");
        String pwd = password + userInfo.getSalt();
        String pwdMD5 = MD5Util.encode(pwd);
        Assert.isTrue(pwdMD5.equals(userInfo.getPassword()),ApiCodeEnum.PWD_ERROR.getMsg());

        //保存token
        AppInfo appInfo = new AppInfo("1","123456654321");
        AccessToken accessToken = this.saveToken(1, appInfo, userInfo);
        userInfo.setAccessToken(accessToken);
        return ApiResponse.success(userInfo);

    }

    /**
     * 保存token
     * @param tokenType
     * @param appInfo
     * @param userInfo
     * @return
     */
    private AccessToken saveToken(int tokenType, AppInfo appInfo, UserInfo userInfo) {

        String token = UUID.randomUUID().toString();

        //token有效期是2小时
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.SECOND,7200);
        Date expireTime = calendar.getTime();

        //保存token
        ValueOperations<String,TokenInfo> valueOperations = redisTemplate.opsForValue();
        TokenInfo tokenInfo = new TokenInfo();
        tokenInfo.setTokenType(tokenType);
        tokenInfo.setAppInfo(appInfo);

        if (tokenType == 1) {
            tokenInfo.setUserInfo(userInfo);
        }

        valueOperations.set(token,tokenInfo,7200,TimeUnit.SECONDS);

        AccessToken accessToken = new AccessToken(token,expireTime);

        return accessToken;
    }
}
