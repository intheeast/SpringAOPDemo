package com.intheeast.manipulatingadvisedobjects;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.intheeast.manipulatingadvisedobjects.config.AppConfig;
import com.intheeast.manipulatingadvisedobjects.service.MyService;

public class Main {
	
	public static void main(String[] args) {
        // Spring 컨텍스트 초기화
        AnnotationConfigApplicationContext context = 
        		new AnnotationConfigApplicationContext(AppConfig.class);

     // 프록시된 myService 빈 가져오기
        MyService myServiceProxy = (MyService) context.getBean("myServiceProxy");

        // Advised 인터페이스로 캐스팅
        Advised advised = (Advised) myServiceProxy;

        // 초기 어드바이저 수 확인
        System.out.println("Initial Advisors count: " + advised.getAdvisors().length);

        // 특정 이름의 어드바이저를 사용하여 추가
        DefaultPointcutAdvisor myAdvisor = context.getBean("myAdvisor", DefaultPointcutAdvisor.class);
        advised.addAdvisor(myAdvisor);

        // 어드바이저 추가 후 확인
        System.out.println("Advisors count after addition: " + advised.getAdvisors().length);

        // performOperation 메서드 호출 (첫 번째 어드바이스 적용 확인)
        myServiceProxy.performOperation();

        // 두 번째 어드바이저를 가져와 첫 번째 어드바이저와 교체
        DefaultPointcutAdvisor anotherAdvisor = context.getBean("anotherAdvisor", DefaultPointcutAdvisor.class);
        advised.replaceAdvisor(myAdvisor, anotherAdvisor);

        // 어드바이저 교체 후 확인
        System.out.println("Advisors count after replacing: " + advised.getAdvisors().length);

        // performOperation 메서드 호출 (두 번째 어드바이스 적용 확인)
        myServiceProxy.performOperation();

        // 컨텍스트 종료
        context.close();
    }

}
