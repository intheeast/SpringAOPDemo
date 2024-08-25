package com.intheeast.jbproperties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.intheeast.jbproperties.config.AppConfig;
import com.intheeast.jbproperties.service.SimpleService;
import com.intheeast.jbproperties.service.SimpleServiceImpl;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.AopContext;
import org.springframework.aop.framework.ProxyFactoryBean;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = 
        		new AnnotationConfigApplicationContext(AppConfig.class);

        // ProxyFactoryBean에서 생성된 프록시를 가져옵니다.
        SimpleService proxy = (SimpleService) context.getBean("proxySimpleService");

        // 1. 프록시를 통해 메서드 호출
        System.out.println("=== 1. 프록시를 통한 메서드 호출 ===");
        proxy.doSomething();

        // 2. AopContext.currentProxy()를 통해 프록시 접근
        System.out.println("=== 2. AopContext.currentProxy()를 통한 메서드 호출 ===");
        try {
            SimpleService currentProxy = (SimpleService) AopContext.currentProxy();
            currentProxy.doSomething();
        } catch (IllegalStateException e) {
            System.out.println("AopContext.currentProxy() 호출 실패: " + e.getMessage());
        }

        // 3. 인터페이스에 따른 프록시 확인 (JDK 프록시 또는 CGLIB 프록시)
        System.out.println("=== 3. 프록시 클래스 확인 ===");
        System.out.println("프록시 클래스: " + proxy.getClass().getName());

        // 4. CGLIB 프록시 테스트
        System.out.println("=== 4. CGLIB 프록시 테스트 ===");
        if (proxy instanceof SimpleServiceImpl) {
            System.out.println("CGLIB 프록시가 적용되었습니다: 프록시가 SimpleServiceImpl을 상속함");
        } else {
            System.out.println("JDK 동적 프록시가 적용되었습니다: 프록시가 인터페이스만 구현함");
        }

        // 5. 프록시가 singleton인지 테스트
        System.out.println("=== 5. Singleton 프록시 테스트 ===");
        SimpleService anotherProxyInstance = (SimpleService) context.getBean("proxySimpleService");
        System.out.println("동일한 프록시 인스턴스인가: " + (proxy == anotherProxyInstance));

        // 6. frozen 설정 테스트
        System.out.println("=== 6. Frozen 설정 테스트 ===");
        try {
            // 동적으로 어드바이스 추가 시도 (실패해야 함)
            ProxyFactoryBean proxyFactoryBean = (ProxyFactoryBean) context.getBean("&proxyFactoryBean");
            Advised advised = (Advised) proxyFactoryBean.getObject();

            advised.addAdvice(new MethodBeforeAdvice() {
                @Override
                public void before(Method method, Object[] args, Object target) throws Throwable {
                    System.out.println("동적으로 추가된 어드바이스");
                }
            });
            System.out.println("Frozen 설정이 무시되었습니다. 어드바이스가 추가되었습니다.");
        } catch (UnsupportedOperationException e) {
            System.out.println("Frozen 설정으로 인해 어드바이스 추가가 차단되었습니다: " + e.getMessage());
        }
    }
}