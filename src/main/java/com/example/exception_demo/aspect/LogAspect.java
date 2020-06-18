package com.example.exception_demo.aspect;

import com.example.exception_demo.log.SysLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class LogAspect {

    @Pointcut("@annotation(com.example.exception_demo.log.SysLog)")
    public void pointcut() {}

    @Before("execution(* com.example.exception_demo.controller.*.*(..))")
    public void beforeMethod() {
        System.out.println(12313);
    }

    @Around("pointcut()")
    public Object aroundMethod(ProceedingJoinPoint pjp) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = pjp.proceed();
        long end = System.currentTimeMillis();
        MethodSignature signature = (MethodSignature)  pjp.getSignature();
        Method method = signature.getMethod();
        SysLog sysLog = method.getAnnotation(SysLog.class);
        if (sysLog != null) {
            System.out.println(sysLog.value());
        }
        String className = pjp.getTarget().getClass().getName();
        String methodName = signature.getName();
        System.out.println(className);
        System.out.println(methodName);
        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println(arg);
        }
        return result;
    }
}
