package com.example.exception_demo.aop;


import sun.applet.Main;

import java.lang.reflect.Proxy;

public class JDKProxyTest {

    public static void main(String[] args) {
        Calculator calculator = new CalculatorImpl();
        Calculator c = (Calculator)Proxy.newProxyInstance(calculator.getClass().getClassLoader(), calculator.getClass().getInterfaces(), new CalculatorJDKProxy(calculator));
        Double result = c.divide(250.0, 50.0);
        System.out.println("结果为：" + result);

    }
}
