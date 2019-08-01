package com.xyb;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 启动dubbo
 */
public class DubboProviderMain {

    public static void main(String[] args) {
        ApplicationContext ac = new ClassPathXmlApplicationContext("dubbo-provider-xml.xml");


        System.out.println("Dubbo provider start...");

        try {
            System.in.read();	// 按任意键退出
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}