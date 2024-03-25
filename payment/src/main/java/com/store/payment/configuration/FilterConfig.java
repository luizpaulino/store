package com.store.payment.configuration;

import com.store.payment.filter.AuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;

@Configuration
public class FilterConfig {

    @Autowired
    private Retrofit retrofit;

    @Bean
    public AuthFilter authFilter() {
        return new AuthFilter(retrofit);
    }

    @Bean
    public FilterRegistrationBean<AuthFilter> customFilterRegistrationBean() {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(authFilter());
        registrationBean.addUrlPatterns("*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
