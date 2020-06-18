package com.example.exception_demo.interceptor;

import com.example.exception_demo.annotation.NotRepeatSubmit;
import com.example.exception_demo.entity.ApiCodeEnum;
import com.example.exception_demo.entity.TokenInfo;
import com.example.exception_demo.utils.ApiUtil;
import com.example.exception_demo.utils.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String token = request.getHeader("token");
        String timestamp = request.getHeader("timestamp");
        String nonce = request.getHeader("nonce");
        String sign = request.getHeader("sign");
        Assert.isTrue(!StringUtils.isEmpty(token) && !StringUtils.isEmpty(timestamp)
                            && !StringUtils.isEmpty(sign),ApiCodeEnum.PARAMETER_ERROR.getMsg());

        //获取超时时间
        NotRepeatSubmit notRepeatSubmit = ApiUtil.getNotRepeatSubmit(handler);
        long expireTime = notRepeatSubmit == null ? 5 * 60 * 1000 : notRepeatSubmit.value();

        //请求间隔时间
        long requestInterval = System.currentTimeMillis() - Long.valueOf(timestamp);
        Assert.isTrue(requestInterval < expireTime,ApiCodeEnum.REQUEST_TIMEOUT.getMsg());

        //校验token是否存在
        ValueOperations<String,TokenInfo> valueOperations = redisTemplate.opsForValue();
        TokenInfo tokenInfo = valueOperations.get(token);
        Assert.notNull(tokenInfo,ApiCodeEnum.TOEKN_ERROR.getMsg());

        //校验签名
        String signStr = ApiUtil.concatSignString(request) + tokenInfo.getAppInfo().getKey() + token + timestamp + nonce;
        String signature = MD5Util.encode(signStr);
        Assert.isTrue(signature.equals(sign),ApiCodeEnum.SIGN_ERROR.getMsg());

        //拒绝重复提交
        if (notRepeatSubmit != null) {
            ValueOperations<String,Integer> operations = redisTemplate.opsForValue();
            Boolean isExist = redisTemplate.hasKey(sign);
            Assert.isTrue(isExist,ApiCodeEnum.REPEAT_SUBMIT.getMsg());
            operations.set(sign, 0, expireTime, TimeUnit.SECONDS);
        }


        return super.preHandle(request, response, handler);
    }
}
