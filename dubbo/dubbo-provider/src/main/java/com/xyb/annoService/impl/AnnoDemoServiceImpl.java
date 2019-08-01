package com.xyb.annoService.impl;

import com.xyb.annoService.AnnoDemoService;
import org.springframework.stereotype.Service;

@Service(value = "annoDemoServiceImpl")
public class AnnoDemoServiceImpl implements AnnoDemoService {

    public String sayHello(String name) {
        return "hello anno " + name;
    }
}