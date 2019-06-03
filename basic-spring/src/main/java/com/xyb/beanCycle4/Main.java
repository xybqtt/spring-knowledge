package com.xyb.beanCycle4;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext4.xml");
        Car car = (Car) ac.getBean("car");
        System.out.println(car.toString());
        ac.close();
    }

}
