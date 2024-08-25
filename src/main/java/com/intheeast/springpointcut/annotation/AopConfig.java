package com.intheeast.springpointcut.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;

@Configuration
@EnableAspectJAutoProxy
public class AopConfig {

    @Bean
    public SimpleService simpleService() {
        return new SimpleService();
    }

    @Bean
    public SimpleAdvice simpleAdvice() {
        return new SimpleAdvice();
    }

    @Bean
    public AnnotationMatchingPointcut logExecutionTimePointcut() {
        return new AnnotationMatchingPointcut(null, com.intheeast.springpointcut.annotation.LogExecutionTime.class);
    }

// @EnableAspectJAutoProxy를 적용함으로써 두 빈은 필요없습니다
//    @Bean
//    public DefaultPointcutAdvisor defaultPointcutAdvisor() {
//        return new DefaultPointcutAdvisor(logExecutionTimePointcut(), simpleAdvice());
//    }
//
//    @Bean
//    public static DefaultAdvisorAutoProxyCreator autoProxyCreator() {
//        return new DefaultAdvisorAutoProxyCreator();
//    }
}
