package com.intheeast.springadvices.advices;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class CountingAfterReturningAdvice implements AfterReturningAdvice {

    private int count;

    @Override
    public void afterReturning(Object returnValue, Method m, Object[] args, Object target) throws Throwable {
        ++count;
        System.out.println("After method: " + m.getName() + ", return value: " + returnValue + ", count=" + count);
    }

    public int getCount() {
        return count;
    }
}