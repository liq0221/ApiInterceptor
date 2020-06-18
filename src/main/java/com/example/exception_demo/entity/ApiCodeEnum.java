package com.example.exception_demo.entity;

public enum ApiCodeEnum {

    /**
     * success
     */
    SUCCESS("10000", "success"),
    /**
     *未知错误
     */
    UNKNOW_ERROR("ERR0001","未知错误"),
    /**
     *参数错误
     */
    PARAMETER_ERROR("ERR0002","参数错误"),
    /**
     *认证过期
     */
    TOKEN_EXPIRE("ERR0003","认证过期"),
    /**
     *请求超时
     */
    REQUEST_TIMEOUT("ERR0004","请求超时"),
    /**
     *签名错误
     */
    SIGN_ERROR("ERR0005","签名错误"),
    /**
     *请不要频繁操作
     */
    REPEAT_SUBMIT("ERR0006","请不要频繁操作"),
    /**
     *密码错误
     */
    PWD_ERROR("ERR0007","密码错误"),
    /**
     *TOKEN错误
     */
    TOEKN_ERROR("ERR0008","TOKEN错误"),
    ;


    /** 代码 */
    private String code;


    /** 结果 */
    private String msg;


    ApiCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public String getCode() {
        return code;
    }


    public String getMsg() {
        return msg;
    }
}
