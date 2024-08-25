package com.intheeast.springadvices;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.intheeast.springadvices.config.AopConfig;
import com.intheeast.springadvices.service.SimpleService;

public class MainApp {
	
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
        SimpleService service = (SimpleService) context.getBean("proxyFactoryBean");

        service.performTask();  // 모든 어드바이스가 적용됨

        String greeting = service.returnGreeting("John");  // 모든 어드바이스가 적용됨
        System.out.println("Greeting: " + greeting);

        try {
            service.throwException();  // Throws Advice가 적용됨
        } catch (Exception e) {
            System.out.println("Exception caught in main: " + e.getMessage());
        }
    }
}
