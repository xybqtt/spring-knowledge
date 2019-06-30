package com.xyb.proxy;

public class CalcImpl implements Calc{


    @Override
    public int add(int a, int b){
        int c = a + b;
        return c;
    }

    @Override
    public int sub(int a, int b){
        int c = a - b;
        return c;
    }

    @Override
    public int div(int a, int b){
        int c = a / b;
        return c;
    }

    @Override
    public int mul(int a, int b){
        int c = a * b;
        return c;
    }

}
