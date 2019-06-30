package com.xyb.beanAnnotation6;

import com.xyb.beanAnnotation6.annotationTest.MyController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class Main6 {


    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("beanAnnotation6.xml");
        MyController user = (MyController) ac.getBean("myController");
        System.out.println(user.toString());


    }

}
