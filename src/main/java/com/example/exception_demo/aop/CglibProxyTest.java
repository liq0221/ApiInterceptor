package com.example.exception_demo.aop;

import org.springframework.cglib.proxy.Enhancer;

public class CglibProxyTest {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MethodTest.class);
        enhancer.setCallback(new CalculatorCglibProxy());
        MethodTest methodTest = (MethodTest)enhancer.create();
        String result1 = methodTest.sayHello("zhaoxin");
        System.out.println("结果为：" + result1);
        String result2 = methodTest.sayHello2("kazike", "qingtingdianshui");
        System.out.println("结果为：" + result2);

    }
}
