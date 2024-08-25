package com.intheeast.manipulatingadvisedobjects.advice;

public class AnotherInterceptor implements org.aopalliance.intercept.MethodInterceptor {
    @Override
    public Object invoke(org.aopalliance.intercept.MethodInvocation invocation) throws Throwable {
        System.out.println("AnotherInterceptor: Before method " + invocation.getMethod().getName());
        Object retVal = invocation.proceed();
        System.out.println("AnotherInterceptor: After method " + invocation.getMethod().getName());
        return retVal;
    }
}
