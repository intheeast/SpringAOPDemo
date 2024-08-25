package com.intheeast.pointcutapi;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.intheeast.pointcutapi.config.AppConfig;
import com.intheeast.pointcutapi.config.AppConfigForEnableAspectJAutoProxy;
import com.intheeast.pointcutapi.service.AnotherService;
import com.intheeast.pointcutapi.service.MyService;

public class SpringAOPExample {
	
	public static void execAppConfig() {
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
	
	public static void execAppConfigForEnableAspectJAutoProxy() {
        AnnotationConfigApplicationContext context = 
        		new AnnotationConfigApplicationContext(AppConfigForEnableAspectJAutoProxy.class);

        // MyService 빈을 가져와서 메서드를 호출합니다.
        MyService myService = context.getBean(MyService.class);
        myService.myMethod();  // LoggingAdvice와 AspectJ 포인트컷이 적용됩니다.
        
        try {
            myService.methodWithException();  // ExceptionHandlingAdvice가 적용됩니다.
        } catch (Exception e) {
            System.out.println("Exception handled in main");
        }

        // AnotherService 빈을 가져와서 메서드를 호출합니다.
        AnotherService anotherService = context.getBean(AnotherService.class);
        anotherService.differentMethod(123);  // CustomPointcut이 적용됩니다.

        // 스프링 컨텍스트를 종료하여 리소스를 해제합니다.
        context.close();	
		
	}

	
	public static void main(String[] args) {
//		execAppConfig();
		
		execAppConfigForEnableAspectJAutoProxy();
    }
}
