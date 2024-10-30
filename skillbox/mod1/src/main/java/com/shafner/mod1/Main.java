package com.shafner.mod1;

import com.shafner.mod1.config.AppConfiguration;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        var appContext = new AnnotationConfigApplicationContext(AppConfiguration.class);
        var contactManager = appContext.getBean(ContactManager.class);
        contactManager.initiate();
    }
}
