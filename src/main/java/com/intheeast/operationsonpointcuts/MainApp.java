package com.intheeast.operationsonpointcuts;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MainApp {
	
	public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AopConfig.class);
        SimpleService service = context.getBean(SimpleService.class);

        service.methodA();  // Should trigger unionPointcut advice
        service.methodB();  // Should also trigger unionPointcut advice
    }

}
