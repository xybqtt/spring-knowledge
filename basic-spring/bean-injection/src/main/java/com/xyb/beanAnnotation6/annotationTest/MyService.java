package com.xyb.beanAnnotation6.annotationTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @Autowired
    private MyDao myDao;

    public void execute(){
        myDao.save();
    }
}
