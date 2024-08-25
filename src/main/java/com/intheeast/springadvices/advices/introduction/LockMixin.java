package com.intheeast.springadvices.advices.introduction;

import org.springframework.aop.support.DelegatingIntroductionInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class LockMixin extends DelegatingIntroductionInterceptor implements Lockable {

    private boolean locked;

    @Override
    public void lock() {
        this.locked = true;
    }

    @Override
    public void unlock() {
        this.locked = false;
    }

    @Override
    public boolean locked() {
        return this.locked;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        if (locked() && invocation.getMethod().getName().startsWith("set")) {
            throw new LockedException();
        }
        return super.invoke(invocation);
    }
}