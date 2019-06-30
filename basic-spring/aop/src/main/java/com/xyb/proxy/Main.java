package com.xyb.proxy;

public class Main {

    public static void main(String[] args) {


        Calc target = new CalcImpl();
        Calc proxy = new CalcProxy(target).getProxy();

        int c = proxy.add(1, 2);
        System.out.println("-->" + c);

        c = proxy.mul(2, 2);
        System.out.println("-->" + c);

    }

}
