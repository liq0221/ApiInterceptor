package com.example.exception_demo.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 非注解方式
 * 注解方式：@WebFilter(filterName = "filterName", urlPattern= "/*")
 */
@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new HelloFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("helloFilter");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;

    }

}
