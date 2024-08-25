package com.intheeast.pointcutapi.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class ExceptionHandlingAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        try {
            return invocation.proceed();
        } catch (Exception ex) {
            System.out.println("Exception caught in method: " + invocation.getMethod().getName() + ", exception: " + ex.getMessage());
            throw ex;
        }
    }
}