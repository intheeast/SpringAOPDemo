package com.intheeast.springadvices.advices;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

public class SimpleThrowsAdvice implements ThrowsAdvice {

    public void afterThrowing(Method m, Object[] args, Object target, Exception ex) {
        System.out.println("Exception thrown in method: " + m.getName() + ", exception: " + ex.getMessage());
    }
}