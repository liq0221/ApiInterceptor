package com.example.exception_demo.service;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public void save() {
        System.out.println("service.save()");
    }
}
