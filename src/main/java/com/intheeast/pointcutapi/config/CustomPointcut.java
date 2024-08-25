package com.intheeast.pointcutapi.config;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

import com.intheeast.pointcutapi.service.CustomAnnotation;

public class CustomPointcut implements Pointcut {

    @Override
    public ClassFilter getClassFilter() {
        return clazz -> clazz.getName().startsWith("com.intheeast.pointcutapi.service");
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return new MethodMatcher() {
            @Override
            public boolean matches(Method method, Class<?> targetClass) {
                // 특정 어노테이션이 있는 메서드와 메서드 이름이 "differentMethod"인 경우 매칭
                return method.isAnnotationPresent(CustomAnnotation.class)
                        || "differentMethod".equals(method.getName());
            }

            @Override
            public boolean isRuntime() {
                return true;
            }

            @Override
            public boolean matches(Method method, Class<?> targetClass, Object... args) {
                // 인자가 String 타입인 경우 매칭
                if (args.length > 0 && args[0] instanceof String) {
                    return true;
                }
                return false;
            }
        };
    }
}
