package com.xyb.genericsInjection7;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 泛型注入
 */
public class Main7 {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("genericsInjection7.xml");
        UserService userService = (UserService) ac.getBean("userService");
        userService.add();
    }

}
