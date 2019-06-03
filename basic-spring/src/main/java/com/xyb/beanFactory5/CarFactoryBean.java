package com.xyb.beanFactory5;

import org.springframework.beans.factory.FactoryBean;

/**
 * 自定义的FactoryBean需要实现FactoryBean接口。
 */
public class CarFactoryBean implements FactoryBean<Car> {


    private String brand;

    public void setBrand(String brand){
        this.brand = brand;
    }

    /**
     * 返回bean的对象
     * @return
     * @throws Exception
     */
    public Car getObject() throws Exception {
        return new Car(50000, brand);
    }

    /**
     * 返回bean的类型
     * @return
     */
    public Class<?> getObjectType() {
        return Car.class;
    }

    /**
     * 是否单实例
     * @return
     */
    public boolean isSingleton() {
        return true;
    }
}
