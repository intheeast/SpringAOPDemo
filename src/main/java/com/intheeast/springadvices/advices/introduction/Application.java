package com.intheeast.springadvices.advices.introduction;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context = 
        		new AnnotationConfigApplicationContext(AppConfig.class);

        MyTargetClass myObject = context.getBean(MyTargetClass.class);
        Lockable lockable = (Lockable) myObject;

        System.out.println("Initially locked: " + lockable.locked());

        lockable.lock();
        System.out.println("Locked: " + lockable.locked());

        try {
            myObject.setName("New Name");
        } catch (LockedException ex) {
            System.out.println(ex.getMessage());
        }

        lockable.unlock();
        System.out.println("Unlocked: " + lockable.locked());

        myObject.setName("New Name");
        System.out.println("Name after unlocking: " + myObject.getName());
    }
}
