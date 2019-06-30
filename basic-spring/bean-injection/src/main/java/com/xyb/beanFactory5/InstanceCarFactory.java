package com.xyb.beanFactory5;

import java.util.HashMap;
import java.util.Map;

/**
 * 实例工厂方法：实例工厂的方法，即需要创建工厂本身，再调用工厂的实例方法来返回bean的实例。
 */
public class InstanceCarFactory {

    private Map<String, Car> cars = null;

    public InstanceCarFactory(){
        cars = new HashMap<String, Car>();
        cars.put("audi", new Car(30000, "audi"));
        cars.put("ford", new Car(50000, "ford"));
    }

    public Car getCar(String brand){
        return cars.get(brand);
    }

}
