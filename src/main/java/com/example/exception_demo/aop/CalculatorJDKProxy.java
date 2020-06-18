package com.example.exception_demo.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class CalculatorJDKProxy implements InvocationHandler {

    private Object proxyTarget;

    public CalculatorJDKProxy(Object proxyTarget) {
        this.proxyTarget = proxyTarget;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("执行jdk代理前调用....");

        List<Object> objects = Arrays.asList(args);
        double sum = objects.stream().mapToDouble(e -> Double.parseDouble(e.toString().trim())).sum();
        if (sum > 50) {
            System.out.println("执行大于50");
        }
        Object invoke = method.invoke(proxyTarget, args);
        System.out.println("执行jdk代理后调用....");
        return invoke;
    }
}
