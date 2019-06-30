package com.xyb.beanRelation3;

import com.xyb.beanAutowire2.Address;
import com.xyb.beanAutowire2.Person;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("beanRelation3.xml");
        Address address1 = (Address) ac.getBean("address1");
        Address address2 = (Address) ac.getBean("address2");
        Person person = (Person) ac.getBean("person");
        System.out.println(address1.toString());
        System.out.println(address2.toString());
        System.out.println(person.toString());


    }
}
