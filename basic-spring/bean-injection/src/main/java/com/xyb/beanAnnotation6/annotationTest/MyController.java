package com.xyb.beanAnnotation6.annotationTest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MyController {

    /**
     * 注解可以放到set方法上，与放到属性上是一样的。
     * @Autowired(required = false)则是不要求此bean被注入。
     */
    @Autowired
    @Qualifier("myService") //当有多个类型一样的bean时，可以用此注解指定bean的id
    private MyService myService;

    @Autowired
    @Qualifier("myService") //当有多个类型一样的bean时，可以用此注解指定bean的id
    public void setMyService(MyService myService){
        this.myService = myService;
    }
//  也可以使用这种方式
//    public void setMyService(@Qualifier("myService") MyService myService){
//        this.myService = myService;
//    }

    public void execute(){
        myService.execute();
    }

}
