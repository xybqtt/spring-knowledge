package com.xyb.beanFactory5;

import java.util.HashMap;
import java.util.Map;

/**
 * 创建bean的静态工厂方法：直接调用某个类的静态方法就可以返回bean的实例
 */
public class StaticCarFactory {


    private static Map<String, Car> cars = new HashMap<String, Car>();

    static {
        cars.put("audi", new Car(30000, "audi"));
        cars.put("ford", new Car(40000, "ford"));
    }

    /**
     * 静态工厂方法
     * @param name
     * @return
     */
    public static Car getCar(String name){
        return cars.get(name);
    }

}
