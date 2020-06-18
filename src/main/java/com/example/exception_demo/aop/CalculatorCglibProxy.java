package com.example.exception_demo.aop;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

public class CalculatorCglibProxy implements MethodInterceptor {

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("执行cglib前调用....");
        Object invokeSuper = methodProxy.invokeSuper(o, objects);
        List<Object> list = Arrays.asList(objects);
        list.stream().map(e -> e.toString()).forEach(System.out :: println);

        System.out.println("执行cglib后调用....");
        return invokeSuper;
    }
}
