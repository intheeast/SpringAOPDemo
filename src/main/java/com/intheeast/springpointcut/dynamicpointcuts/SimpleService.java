package com.intheeast.springpointcut.dynamicpointcuts;

public class SimpleService {

    public void performTask() {
        System.out.println("Performing a task");
        internalTask();  // 내부 메서드 호출
    }

    public void internalTask() {
        System.out.println("Performing internal task");
    }
}