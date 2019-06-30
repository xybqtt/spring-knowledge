package com.xyb.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * java动态代理实现aop，被代理类必须有接口
 */
public class CalcProxy {

    // 要代理的对象
    private Calc target;

    public CalcProxy(Calc target){
        this.target = target;
    }

    public Calc getProxy(){

        // 代理对象由哪一个类加载器负责加载
        ClassLoader classLoader = target.getClass().getClassLoader();

        // 代理对象的类型，即其中有哪些方法
        Class[] interfaces = new Class[]{Calc.class};

        // 当调用代理对象其中的方法时，该执行的代码
        InvocationHandler handler = new InvocationHandler() {

            /**
             *
             * @param proxy 正在返回的那个代理对象，一般情况下，在invoke方法中都不使用该对象
             * @param method 正在被调用的方法
             * @param args 调用方法时，传入的参数
             * @return
             * @throws Throwable
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String methodName = method.getName();
                // 日志
                System.out.println("The method " + methodName + "，参数 " + Arrays.asList(args));
                // 执行方法
                Object result = method.invoke(target, args);
                // 日志
                System.out.println("The method " + methodName + "ends with" + result);
                return result;
            }
        };

        Calc proxy = (Calc) Proxy.newProxyInstance(classLoader, interfaces, handler);
        return proxy;


    }

}
