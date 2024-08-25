package com.intheeast.conciseproxydefinitions.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionProxyFactoryBean;

import com.intheeast.conciseproxydefinitions.service.MyServiceImpl;
import com.intheeast.conciseproxydefinitions.service.MySpecialServiceImpl;
import com.intheeast.conciseproxydefinitions.transaction.SimpleTransactionManager;

@Configuration
@EnableTransactionManagement
public class AppConfig {

    // 트랜잭션 관리자 빈 정의
    @Bean
    public PlatformTransactionManager transactionManager() {
        return new SimpleTransactionManager();
    }


    // 자식 빈 정의를 위한 템플릿 메서드
    private TransactionProxyFactoryBean createProxy(
    		PlatformTransactionManager transactionManager, 
    		Object target, 
    		Properties transactionAttributes) {
        TransactionProxyFactoryBean proxyFactoryBean = new TransactionProxyFactoryBean();
        proxyFactoryBean.setTransactionManager(transactionManager);
        proxyFactoryBean.setTarget(target);
        proxyFactoryBean.setTransactionAttributes(transactionAttributes);
        proxyFactoryBean.setProxyTargetClass(true);
        return proxyFactoryBean;
    }

    // myService 빈 정의
    @Bean
    public TransactionProxyFactoryBean myService(PlatformTransactionManager transactionManager) {
        Properties transactionAttributes = new Properties();
        transactionAttributes.setProperty("*", "PROPAGATION_REQUIRED");
        return createProxy(transactionManager, 
        		new MyServiceImpl(), 
        		transactionAttributes);
    }

    // mySpecialService 빈 정의
    @Bean
    public TransactionProxyFactoryBean mySpecialService(PlatformTransactionManager transactionManager) {
        Properties transactionAttributes = new Properties();
        transactionAttributes.setProperty("get*", "PROPAGATION_REQUIRED,readOnly");
        transactionAttributes.setProperty("find*", "PROPAGATION_REQUIRED,readOnly");
        transactionAttributes.setProperty("load*", "PROPAGATION_REQUIRED,readOnly");
        transactionAttributes.setProperty("store*", "PROPAGATION_REQUIRED");
        return createProxy(transactionManager, 
        		new MySpecialServiceImpl(), 
        		transactionAttributes);
    }

}