package com.intheeast.jbproperties.config;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.NameMatchMethodPointcut;
import org.springframework.beans.BeansException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.intheeast.jbproperties.advice.LoggingBeforeAdvice;
import com.intheeast.jbproperties.service.SimpleService;
import com.intheeast.jbproperties.service.SimpleServiceImpl;

@Configuration
public class AppConfig {

    // 타겟 빈 정의
    @Bean
    public SimpleService simpleService() {
        return new SimpleServiceImpl();
    }

    // 어드바이스 빈 정의
    @Bean
    public LoggingBeforeAdvice loggingBeforeAdvice() {
        return new LoggingBeforeAdvice();
    }

    // 어드바이저 빈 정의
    @Bean
    public DefaultPointcutAdvisor loggingAdvisor() {
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("doSomething");

        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor();
        advisor.setPointcut(pointcut);
        advisor.setAdvice(loggingBeforeAdvice());
        return advisor;
    }

    // ProxyFactoryBean 정의
    @Bean
    public ProxyFactoryBean proxyFactoryBean() throws ClassNotFoundException {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(simpleService());

        // CGLIB 프록시를 강제로 사용하도록 설정
        proxyFactoryBean.setProxyTargetClass(true);

        // 공격적인 최적화를 적용 (CGLIB 프록시만 해당)
        proxyFactoryBean.setOptimize(true);

        // 프록시 구성을 동결하여 더 이상의 변경을 방지
        proxyFactoryBean.setFrozen(false);

        // 현재 프록시를 AopContext를 통해 노출
        proxyFactoryBean.setExposeProxy(true);

        // 특정 인터페이스를 프록시로 설정
        proxyFactoryBean.setProxyInterfaces(new Class<?>[]{SimpleService.class});

        // 적용할 인터셉터(어드바이저) 설정
        proxyFactoryBean.setInterceptorNames("loggingAdvisor");

        // singleton 속성 설정
        proxyFactoryBean.setSingleton(true);

        return proxyFactoryBean;
    }

    // 실제 사용할 서비스 빈을 정의합니다.
    @Bean
    public SimpleService proxySimpleService() throws BeansException, ClassNotFoundException {
        return (SimpleService) proxyFactoryBean().getObject();
    }
}