package com.example.exception_demo.controller;

import com.example.exception_demo.log.SysLog;
import com.example.exception_demo.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HelloController {

    @Autowired
    private HelloService helloService;

    @SysLog("测试")
    @RequestMapping("hello")
    public String sayHello(@RequestParam("name") String name) {

        return name + "hello world!!!";
    }
}
