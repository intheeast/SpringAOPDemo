package com.intheeast.usingtheautoproxyfacility.config;

import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.interceptor.SimpleTraceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.AnnotationTransactionAttributeSource;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionAttributeSourceAdvisor;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.intheeast.conciseproxydefinitions.transaction.SimpleTransactionManager;
import com.intheeast.usingtheautoproxyfacility.bean.BusinessObject1;
import com.intheeast.usingtheautoproxyfacility.bean.BusinessObject2;
import com.intheeast.usingtheautoproxyfacility.bean.MyBean;

@Configuration
@EnableTransactionManagement
// 만약 BusinessObject1/2가 특정 인터페이스를 구현하지 않는다면, proxyTargetClass 속성을 true로 설정해야 합니다
//@EnableTransactionManagement(proxyTargetClass = true)
public class AppConfig {

	// BeanNameAutoProxyCreator 정의 (우선순위 설정)
    @Bean
    @Order(1)
    public static BeanNameAutoProxyCreator beanNameAutoProxyCreator() {
        BeanNameAutoProxyCreator proxyCreator = new BeanNameAutoProxyCreator();
        proxyCreator.setBeanNames("jdk*", "onlyJdk");
        proxyCreator.setInterceptorNames("myInterceptor");
        return proxyCreator;
    }

    // SimpleTraceInterceptor 정의
    @Bean
    public SimpleTraceInterceptor myInterceptor() {
        return new SimpleTraceInterceptor();
    }

    // 예제 빈 정의 (BeanNameAutoProxyCreator로 자동 프록시 생성)
    @Bean
    public MyBean jdkMyBean() {
        return new MyBean();
    }

    @Bean
    public MyBean onlyJdk() {
        return new MyBean();
    }

    // DefaultAdvisorAutoProxyCreator 정의 (우선순위 설정)
    @Bean
    @Order(2)
    public static DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        return new DefaultAdvisorAutoProxyCreator();
    }

    // Transaction attribute source advisor 정의
    @Bean
    public TransactionAttributeSourceAdvisor transactionAttributeSourceAdvisor(TransactionInterceptor transactionInterceptor) {
        TransactionAttributeSourceAdvisor advisor = new TransactionAttributeSourceAdvisor();
        advisor.setTransactionInterceptor(transactionInterceptor);
        return advisor;
    }

    // Transaction interceptor 정의
    @Bean
    public TransactionInterceptor transactionInterceptor() {
        TransactionInterceptor interceptor = new TransactionInterceptor();
        interceptor.setTransactionManager(transactionManager());
        interceptor.setTransactionAttributeSource(
        		new AnnotationTransactionAttributeSource());
        return interceptor;
    }

    // Transaction manager 정의
    @Bean
    public SimpleTransactionManager transactionManager() {
        return new SimpleTransactionManager();
    }

    // 예제 비즈니스 오브젝트 정의 (DefaultAdvisorAutoProxyCreator로 자동 프록시 생성)
    @Bean
    public BusinessObject1 businessObject1() {
        return new BusinessObject1();
    }

    @Bean
    public BusinessObject2 businessObject2() {
        return new BusinessObject2();
    }
}