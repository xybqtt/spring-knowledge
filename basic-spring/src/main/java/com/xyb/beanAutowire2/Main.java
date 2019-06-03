package com.xyb.beanAutowire2;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext2.xml");
        com.xyb.beanAutowire2.Person person = ac.getBean(com.xyb.beanAutowire2.Person.class);
        System.out.println(person.toString());
    }
}
