package com.xyb.beanCycle4;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * bean后处理器，需要实现BeanPostProcessor接口，配置到xml中(不需配置id，ioc容器自动识别)，就会在所有bean的init方法前后执行。
 *
 */
public class MyBeanPostProcessor implements BeanPostProcessor {

    /**
     *
     * @param bean bean实例本身
     * @param beanName bean在xml中配置的id
     * @return 实际上返回给用户的bean，注意，可以在方法中修改bean，甚至可以修改bean，
     * @throws BeansException
     */
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("bean后处理器：在调用" + beanName + "init方法前执行");
        // 可以使用这种方法，来决定哪种bean使用哪种处理
        if(Car.class.equals(bean.getClass())){

        }
        return bean;
    }

    /**
     *
     * @param bean bean实例本身
     * @param beanName bean在xml中配置的id
     * @return 实际上返回给用户的bean，注意，可以在方法中修改bean，甚至可以修改bean，
     * @throws BeansException
     */
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("bean后处理器：在调用" + beanName + "init方法后执行");
        return bean;
    }

}
