package com.intheeast.springpointcut.dynamicpointcuts;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;


public class MainApp {
	public static void main(String[] args) {
        ApplicationContext context = 
        		new AnnotationConfigApplicationContext(AopConfig.class);
        TaskCaller caller = context.getBean(TaskCaller.class);

        // TaskCaller의 메서드를 통해 호출될 때만 어드바이스가 적용됩니다.
        caller.callTask();

        // 직접 SimpleService의 메서드를 호출할 경우 어드바이스가 적용되지 않습니다.
        context.getBean("proxyFactoryBean", SimpleService.class).performTask();
    }

}
