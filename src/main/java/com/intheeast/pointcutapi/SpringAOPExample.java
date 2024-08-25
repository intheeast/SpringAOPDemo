package com.intheeast.pointcutapi;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.intheeast.pointcutapi.config.AppConfig;
import com.intheeast.pointcutapi.service.AnotherService;
import com.intheeast.pointcutapi.service.MyService;

public class SpringAOPExample {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(AppConfig.class);

        MyService myService = (MyService) context.getBean("myServiceProxy");
        AnotherService anotherService = (AnotherService) context.getBean("anotherServiceProxy");

        // 메서드 실행 테스트
        myService.myMethod();              // AspectJ 포인트컷 및 커스텀 포인트컷 모두 적용
        myService.anotherMethod("test");   // 커스텀 포인트컷만 적용
        
        anotherService.differentMethod(123); // 커스텀 포인트컷만 적용

        try {
            myService.methodWithException(); // 예외 처리 어드바이스가 실행
        } catch (Exception e) {
            System.out.println("Exception handled in main");
        }

        context.close();    
    }
}
