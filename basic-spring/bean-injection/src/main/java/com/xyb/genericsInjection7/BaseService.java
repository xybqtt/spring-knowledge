package com.xyb.genericsInjection7;

import org.springframework.beans.factory.annotation.Autowired;

public class BaseService<T> {

    // 注解写在属性上，不是写在类上，可以被子类继承。
    @Autowired
    protected BaseRepository<T> repository;

    public void add(){
        System.out.println("add...");
        System.out.println(repository);
    }
}
