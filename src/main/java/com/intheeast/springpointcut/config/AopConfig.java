package com.intheeast.springpointcut.config;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.intheeast.springpointcut.aspect.SimpleAdvice;
import com.intheeast.springpointcut.service.SimpleService;

@Configuration
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
    public JdkRegexpMethodPointcut jdkRegexpMethodPointcut() {
        JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
        // 이 패턴은 "set"으로 시작하는 모든 메서드에 적용됩니다.
        pointcut.setPatterns(".*get.*");  
        return pointcut;
    }
    
    @Bean
    public DefaultPointcutAdvisor defaultPointcutAdvisor() {
        return new DefaultPointcutAdvisor(jdkRegexpMethodPointcut(), simpleAdvice());
    }

    @Bean
    public RegexpMethodPointcutAdvisor settersAndAbsquatulateAdvisor() {
        RegexpMethodPointcutAdvisor advisor = new RegexpMethodPointcutAdvisor();
        advisor.setAdvice(simpleAdvice());
        // 두 가지 패턴을 설정하여, 
        // 메서드 이름에 "set"이나 "absquatulate"라는 단어가 포함된 메서드를 포인트컷으로 지정하는 것입니다. 
        // 이 포인트컷이 적용된 메서드가 호출될 때마다, 해당 어드바이스가 실행됩니다.
        // 예를 들어, setName과 absquatulate 메서드는 이 패턴과 일치하므로, 
        // 이 메서드들이 호출될 때 어드바이스가 적용됩니다. 
        // 반면, 이 패턴과 일치하지 않는 메서드(예: performTask)에는 어드바이스가 적용되지 않습니다.
        advisor.setPatterns(".*set.*", ".*absquatulate.*");
        return advisor;
    }

    @Bean
    public ProxyFactoryBean proxyFactoryBean() {
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(simpleService());
        
        // 두 어드바이저를 하나의 프록시 팩토리 빈에 추가
        proxyFactoryBean.addAdvisor(settersAndAbsquatulateAdvisor());
        proxyFactoryBean.addAdvisor(defaultPointcutAdvisor());

        return proxyFactoryBean;
    }
}
