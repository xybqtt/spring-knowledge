package com.xyb.dubboService.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xyb.dubboService.Provider;

// 此注解是alibaba的注解，里面包含了spring的注解
@Service
public class ProviderImpl implements Provider {

    @Override
    public int add(int a) {
        return a + 1;
    }
}
