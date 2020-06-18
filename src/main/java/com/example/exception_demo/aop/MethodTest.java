package com.example.exception_demo.aop;

import com.fasterxml.jackson.datatype.jsr310.DecimalUtils;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;

public class MethodTest {

    public String sayHello(String str) {

        System.out.println("执行了sayHello()方法...");
        return str;

    }

    public String sayHello2(String str,String name) {
        System.out.println("执行了sayHello2()方法...");
        return "hello," + name + str;
    }

    public static void main(String[] args) {
        String str = "【华夏银行】蒋科联先生/女士，针对您目前华夏银行信用卡账户欠款金额，现有多种信用卡协商还款方案供您选择。协商还款方案包括减免息费、个性化分期及本金协商等方案。如有疑问，可致电#phone#咨询。如已还款，无需理会，退订回T";
        System.out.println(str.contains("#phone#"));
    }

}
