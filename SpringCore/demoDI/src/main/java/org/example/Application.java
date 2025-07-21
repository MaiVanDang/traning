package org.example;

import org.example.bean.HelloWorld;
import org.example.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(AppConfig.class);

        HelloWorld helloWorld = (HelloWorld) applicationContext.getBean("helloWorldBean");
        System.out.println(helloWorld.getMessage());
    }
}
