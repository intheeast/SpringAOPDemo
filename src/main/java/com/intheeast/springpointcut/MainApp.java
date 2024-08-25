package com.intheeast.springpointcut;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.intheeast.springpointcut.config.AopConfig;
import com.intheeast.springpointcut.service.SimpleService;

public class MainApp {
    public static void main(String[] args) {
        ApplicationContext context = 
        		new AnnotationConfigApplicationContext(AopConfig.class);
        SimpleService service = (SimpleService) context.getBean("proxyFactoryBean");

        service.setName("John Doe");  // Advice가 적용됨
        service.getName();            // Advice가 적용됨
        service.absquatulate();       // Advice가 적용됨
        service.performTask();        // Advice가 적용되지 않음
    }
}
