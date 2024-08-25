package com.intheeast.springpointcut.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MainApp {
	
    public static void main(String[] args) {
        ApplicationContext context = 
        		new AnnotationConfigApplicationContext(AopConfig.class);
        SimpleService service = context.getBean(SimpleService.class);

        service.setName("John Doe");       // Advice가 적용됨
        service.absquatulate();            // Advice가 적용되지 않음
        service.performTask();             // Advice가 적용됨
        service.resetPassword("johndoe");  // Advice가 적용되지 않음
    }


}
