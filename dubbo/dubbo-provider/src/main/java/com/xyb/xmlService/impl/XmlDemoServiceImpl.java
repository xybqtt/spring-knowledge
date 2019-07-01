package com.xyb.xmlService.impl;

import com.xyb.xmlService.XmlDemoService;

public class XmlDemoServiceImpl implements XmlDemoService {

    public String sayHello(String name) {
        return "hello xml " + name;
    }
}