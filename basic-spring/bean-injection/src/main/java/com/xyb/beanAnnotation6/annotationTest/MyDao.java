package com.xyb.beanAnnotation6.annotationTest;

import org.springframework.stereotype.Repository;

@Repository
public class MyDao {

    public void save(){
        System.out.println("执行结束");
    }
}
