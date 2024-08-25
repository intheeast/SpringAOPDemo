package com.intheeast.conciseproxydefinitions;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.intheeast.conciseproxydefinitions.config.AppConfig;
import com.intheeast.conciseproxydefinitions.service.MyService;
import com.intheeast.conciseproxydefinitions.service.MySpecialService;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = 
        		new AnnotationConfigApplicationContext(AppConfig.class);

        // myService 빈 가져오기 및 메서드 실행
        MyService myService = (MyService) context.getBean("myService");
        myService.performOperation();

        // mySpecialService 빈 가져오기 및 메서드 실행
        MySpecialService mySpecialService = (MySpecialService) context.getBean("mySpecialService");
        mySpecialService.performSpecialOperation();

        context.close();
    }

}
