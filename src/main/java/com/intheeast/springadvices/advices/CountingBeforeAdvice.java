package com.intheeast.springadvices.advices;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class CountingBeforeAdvice implements MethodBeforeAdvice {

    private int count;

    @Override
    public void before(Method m, Object[] args, Object target) throws Throwable {
        ++count;
        System.out.println("Before method: " + m.getName() + ", count=" + count);
    }

    public int getCount() {
        return count;
    }
}