package com.intheeast.springpointcut.service;

public class SimpleService {

    public void setName(String name) {
        System.out.println("Setting name: " + name);
    }

    public void absquatulate() {
        System.out.println("Absquatulating!");
    }

    public void performTask() {
        System.out.println("Performing a task");
    }

    public void resetPassword(String username) {
        System.out.println("Resetting password for: " + username);
    }

    public void getName() {
        System.out.println("Getting name");
    }

    public void updateStatus() {
        System.out.println("Updating status");
    }

    public void completeAbsquatulation() {
        System.out.println("Completing absquatulation!");
    }

    public void initialize() {
        System.out.println("Initializing service");
    }
}
