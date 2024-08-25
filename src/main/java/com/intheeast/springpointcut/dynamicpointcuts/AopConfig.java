package com.intheeast.springpointcut.dynamicpointcuts;

import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AopConfig {

    @Bean
    public SimpleService simpleService() {
        return new SimpleService();
    }

    @Bean
    public TaskCaller taskCaller() {
        return new TaskCaller(simpleService());
    }

    @Bean
    public SimpleAdvice simpleAdvice() {
        return new SimpleAdvice();
    }

    @Bean
    public Pointcut controlFlowPointcut() {
        return new ControlFlowPointcut(TaskCaller.class, "callTask");
    }

    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor() {
        return new DefaultPointcutAdvisor(controlFlowPointcut(), simpleAdvice());
    }

    @Bean
    public ProxyFactoryBean proxyFactoryBean() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(simpleService());
        proxyFactoryBean.addAdvisor(defaultPointcutAdvisor());
        return proxyFactoryBean;
    }
}