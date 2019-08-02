/**
 * Copyright (C), 2015-2019, XXX有限公司
 * FileName: DubboConsumerMain
 * Author:   XYB
 * Date:     2019/7/1 15:21
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.xyb;

import com.xyb.xmlService.XmlDemoService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author XYB
 * @create 2019/7/1
 * @since 1.0.0
 */
public class DubboConsumerMain {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("dubbo-consumer-xml.xml");

        context.start();
        XmlDemoService demoService = (XmlDemoService) context.getBean("xmlDemoService");

        System.out.println(demoService.sayHello("哈哈哈"));
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}