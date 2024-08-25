package com.intheeast.manipulatingadvisedobjects.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class DebugInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        System.out.println("DebugInterceptor: Before method " + invocation.getMethod().getName());
        Object retVal = invocation.proceed();
        System.out.println("DebugInterceptor: After method " + invocation.getMethod().getName());
        return retVal;
    }
}