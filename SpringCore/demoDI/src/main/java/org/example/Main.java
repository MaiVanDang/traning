package org.example;

import org.example.models.Teacher;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        AbstractApplicationContext context;
        context = new ClassPathXmlApplicationContext("/teacher-bean.xml");
        Teacher teacher = (Teacher) context.getBean("teacher");
        System.out.println(teacher);
    }
}