package com.intheeast.pointcutapi.config;

import java.util.List;

import org.springframework.aop.Advisor;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.intheeast.pointcutapi.advice.ExceptionHandlingAdvice;
import com.intheeast.pointcutapi.advice.ExecutionTimeAdvice;
import com.intheeast.pointcutapi.advice.LoggingAdvice;
import com.intheeast.pointcutapi.service.AnotherService;
import com.intheeast.pointcutapi.service.MyService;

@Configuration
public class AppConfig {

    @Bean
    public Pointcut customPointcut() {
        return new CustomPointcut();
    }

    @Bean
    public Pointcut aspectJPointcut() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression("execution(* com.intheeast.pointcutapi.service.MyService.myMethod(..))");
        return pointcut;
    }

    @Lazy
    @Bean
    public DefaultPointcutAdvisor loggingAdvisor(@Qualifier("customPointcut") Pointcut pointcut) {
        return new DefaultPointcutAdvisor(pointcut, new LoggingAdvice());
    }

    @Lazy
    @Bean
    public DefaultPointcutAdvisor executionTimeAdvisor(@Qualifier("customPointcut") Pointcut pointcut) {
        return new DefaultPointcutAdvisor(pointcut, new ExecutionTimeAdvice());
    }

    @Lazy
    @Bean
    public DefaultPointcutAdvisor exceptionHandlingAdvisor(@Qualifier("customPointcut") Pointcut pointcut) {
        return new DefaultPointcutAdvisor(pointcut, new ExceptionHandlingAdvice());
    }

    @Lazy
    @Bean
    public DefaultPointcutAdvisor aspectJLoggingAdvisor(@Qualifier("aspectJPointcut") Pointcut pointcut) {
        return new DefaultPointcutAdvisor(pointcut, new LoggingAdvice());
    }

    @Lazy
    @Bean
    public ProxyFactoryBean myServiceProxy(
            MyService myService,
            @Qualifier("loggingAdvisor") DefaultPointcutAdvisor loggingAdvisor,
            @Qualifier("executionTimeAdvisor") DefaultPointcutAdvisor executionTimeAdvisor,
            @Qualifier("exceptionHandlingAdvisor") DefaultPointcutAdvisor exceptionHandlingAdvisor,
            @Qualifier("aspectJLoggingAdvisor") DefaultPointcutAdvisor aspectJLoggingAdvisor) {

        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(myService);
        proxyFactoryBean.setInterceptorNames("loggingAdvisor", "executionTimeAdvisor", "exceptionHandlingAdvisor", "aspectJLoggingAdvisor");
        return proxyFactoryBean;
    }

    @Lazy
    @Bean
    public ProxyFactoryBean anotherServiceProxy(
            AnotherService anotherService,
            @Qualifier("loggingAdvisor") DefaultPointcutAdvisor loggingAdvisor,
            @Qualifier("executionTimeAdvisor") DefaultPointcutAdvisor executionTimeAdvisor,
            @Qualifier("exceptionHandlingAdvisor") DefaultPointcutAdvisor exceptionHandlingAdvisor) {

        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(anotherService);
        proxyFactoryBean.setInterceptorNames("loggingAdvisor", "executionTimeAdvisor", "exceptionHandlingAdvisor");
        return proxyFactoryBean;
    }

    @Bean
    public MyService myService() {
        return new MyService();
    }

    @Bean
    public AnotherService anotherService() {
        return new AnotherService();
    }

}