package com.intheeast.operationsonpointcuts;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.StaticMethodMatcher;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.Pointcuts;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@Aspect
public class AopConfig {

    // Pointcut for methodA
    private static Pointcut pointcutForMethodA() {
        return (Pointcut) new StaticMethodMatcherPointcut() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                return "methodA".equals(method.getName());
            }
        };
    }

    // Pointcut for methodB
    private static Pointcut pointcutForMethodB() {
        return new StaticMethodMatcherPointcut() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                return "methodB".equals(method.getName());
            }
        };
    }

    // Combined Pointcut (Union - OR)
    private static Pointcut unionPointcut() {
        return Pointcuts.union(pointcutForMethodA(), pointcutForMethodB());
    }

    // Combined Pointcut (Intersection - AND)
    private static Pointcut intersectionPointcut() {
        return Pointcuts.intersection(pointcutForMethodA(), pointcutForMethodB());
    }

    @Before("execution(* com.intheeast.operationsonpointcuts.SimpleService.*(..))")
    public void beforeAdvice() {
        System.out.println("Before method execution");
    }

    @Bean
    public SimpleService simpleService() {
    	ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        proxyFactoryBean.setTarget(new SimpleService());

        // MethodInterceptor를 Advice로 사용하여 Advisor 생성
        org.aopalliance.aop.Advice advice = 
        		new MethodInterceptor() {        	
            		@Override
		            public Object invoke(MethodInvocation methodInvocation) throws Throwable {
		                System.out.println("Additional advice before unionPointcut method execution");
		                return methodInvocation.proceed();
		            }
        };

        // DefaultPointcutAdvisor는 Pointcut과 Advice를 각각 받아야 함
        DefaultPointcutAdvisor advisor = 
        		new DefaultPointcutAdvisor(unionPointcut(), advice);

        // ProxyFactoryBean에 Advisor 추가
        proxyFactoryBean.addAdvisor(advisor);

        return (SimpleService) proxyFactoryBean.getObject();
    }
}