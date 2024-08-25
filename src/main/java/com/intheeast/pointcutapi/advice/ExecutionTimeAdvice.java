package com.intheeast.pointcutapi.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class ExecutionTimeAdvice implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return invocation.proceed();
        } finally {
            long timeTaken = System.currentTimeMillis() - start;
            System.out.println("Execution time of " + invocation.getMethod().getName() + " :: " + timeTaken + " ms");
        }
    }
}