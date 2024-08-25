package com.intheeast.springadvices.config;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.intheeast.springadvices.advices.CountingAfterReturningAdvice;
import com.intheeast.springadvices.advices.CountingBeforeAdvice;
import com.intheeast.springadvices.advices.DebugInterceptor;
import com.intheeast.springadvices.advices.SimpleThrowsAdvice;
import com.intheeast.springadvices.service.SimpleService;

@Configuration
public class AopConfig {

    @Bean
    public SimpleService simpleService() {
        return new SimpleService();
    }

    @Bean
    public DebugInterceptor debugInterceptor() {
        return new DebugInterceptor();
    }

    @Bean
    public CountingBeforeAdvice countingBeforeAdvice() {
        return new CountingBeforeAdvice();
    }

    @Bean
    public CountingAfterReturningAdvice countingAfterReturningAdvice() {
        return new CountingAfterReturningAdvice();
    }

    @Bean
    public SimpleThrowsAdvice simpleThrowsAdvice() {
        return new SimpleThrowsAdvice();
    }

    @Bean
    public ProxyFactoryBean proxyFactoryBean() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(simpleService());

        proxyFactoryBean.addAdvisor(new DefaultPointcutAdvisor(countingBeforeAdvice()));
        proxyFactoryBean.addAdvisor(new DefaultPointcutAdvisor(debugInterceptor()));
        proxyFactoryBean.addAdvisor(new DefaultPointcutAdvisor(countingAfterReturningAdvice()));
        proxyFactoryBean.addAdvisor(new DefaultPointcutAdvisor(simpleThrowsAdvice()));

        return proxyFactoryBean;
    }
}
