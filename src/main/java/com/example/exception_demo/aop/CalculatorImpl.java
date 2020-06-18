package com.example.exception_demo.aop;

public class CalculatorImpl implements Calculator {

    @Override
    public Double divide(Double a, Double b) {
        return a / b;
    }
}
