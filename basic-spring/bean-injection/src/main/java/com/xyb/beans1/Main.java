package com.xyb.beans1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        // 1、创建ioc容器
        ApplicationContext ac = new ClassPathXmlApplicationContext("beans1.xml");
        User user = (User) ac.getBean("user5");
        System.out.println(user.toString());
    }
}
