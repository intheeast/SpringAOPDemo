package com.intheeast.pointcutapi.service;

public class MyService {
	
    @CustomAnnotation
    public void myMethod() {
        System.out.println("Executing myMethod");
    }

    public void anotherMethod(String arg) {
        System.out.println("Executing anotherMethod with arg: " + arg);
    }

    public void methodWithException() throws Exception {
        throw new Exception("Exception in methodWithException");
    }

}
