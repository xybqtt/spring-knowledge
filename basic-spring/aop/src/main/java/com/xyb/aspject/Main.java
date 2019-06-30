package com.xyb.aspject;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        /**
         * 注意Calc会被注入两次，取消aspject-xml中的注入，即可。
         */
        ApplicationContext ac = new ClassPathXmlApplicationContext("aspject-*.xml");

        Calc calc = ac.getBean(Calc.class);

        int result = calc.sub(3, 6);
        System.out.println("result：" + result);

//        int result = calc.add(3, 6);
//        System.out.println("result：" + result);

//        result = calc.div(6, 0);
//        System.out.println("result：" + result);


    }

}
