package com.intheeast.springadvices.advices.introduction;

public interface Lockable {  
    void lock();  
    void unlock();  
    boolean locked();  
}