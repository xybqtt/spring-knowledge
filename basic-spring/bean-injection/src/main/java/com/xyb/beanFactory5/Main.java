package com.xyb.beanFactory5;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("beanFactory5.xml");
        Car car1 = (Car) ac.getBean("car1");
        Car car2 = (Car) ac.getBean("car2");
        Car car3 = (Car) ac.getBean("car3");
        System.out.println(car1.toString());
        System.out.println(car2.toString());
        System.out.println(car3.toString());
    }
}
