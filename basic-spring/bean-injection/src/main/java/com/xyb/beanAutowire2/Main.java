package com.xyb.beanAutowire2;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("beanAutowire2.xml");
        Person person = ac.getBean(Person.class);
        System.out.println(person.toString());
    }
}
