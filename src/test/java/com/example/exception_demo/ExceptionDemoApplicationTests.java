package com.example.exception_demo;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
class ExceptionDemoApplicationTests {

    @Value("${name}")
    private String name;

    @Test
    void contextLoads() {
        System.out.println(name);
    }

}
