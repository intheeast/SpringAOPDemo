package com.intheeast.springpointcut.annotation;


public class SimpleService {

    @LogExecutionTime
    public void setName(String name) {
        System.out.println("Setting name: " + name);
    }

    public void absquatulate() {
        System.out.println("Absquatulating!");
    }

    @LogExecutionTime
    public void performTask() {
        System.out.println("Performing a task");
    }

    public void resetPassword(String username) {
        System.out.println("Resetting password for: " + username);
    }
}