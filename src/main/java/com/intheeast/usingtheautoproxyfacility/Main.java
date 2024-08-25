package com.intheeast.usingtheautoproxyfacility;

import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.intheeast.usingtheautoproxyfacility.bean.BusinessObject1;
import com.intheeast.usingtheautoproxyfacility.bean.BusinessObject2;
import com.intheeast.usingtheautoproxyfacility.bean.MyBean;
import com.intheeast.usingtheautoproxyfacility.config.AppConfig;
import com.intheeast.usingtheautoproxyfacility.service.BusinessService;

public class Main {
	public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        MyBean jdkMyBean = context.getBean("jdkMyBean", MyBean.class);
        printProxyInfo(jdkMyBean);

        BusinessService businessObject1 = context.getBean("businessObject1", BusinessService.class);
        printProxyInfo(businessObject1);

        BusinessService businessObject2 = context.getBean("businessObject2", BusinessService.class);
        printProxyInfo(businessObject2);
    }

    private static void printProxyInfo(Object bean) {
        Class<?> targetClass = AopProxyUtils.ultimateTargetClass(bean);
        System.out.println("Bean class: " + bean.getClass().getName());
        System.out.println("Is JDK dynamic proxy: " + (java.lang.reflect.Proxy.isProxyClass(bean.getClass())));
        System.out.println("Is CGLIB proxy: " + (bean.getClass().getName().contains("$$")));
        System.out.println("Target class: " + targetClass.getName());
        System.out.println("----------------------");
    }
}