package com.intheeast.springadvices.service;

public class SimpleService {

    public void performTask() {
        System.out.println("Performing a task");
    }

    public String returnGreeting(String name) {
        return "Hello, " + name;
    }

    public void throwException() throws Exception {
        throw new Exception("This is a test exception");
    }
}