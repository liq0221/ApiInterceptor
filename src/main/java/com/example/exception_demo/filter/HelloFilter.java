package com.example.exception_demo.filter;

import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.FilterConfig;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;


public class HelloFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        long start = System.currentTimeMillis();

        filterChain.doFilter(servletRequest,servletResponse);

        System.out.println("过滤耗时：" + (System.currentTimeMillis() - start ));
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("过滤器名字：" + filterConfig.getFilterName());

    }

    @Override
    public void destroy() {
        System.out.println("过滤结束 over。。。");
    }
}
