package com.xyb.beanCycle4;

public class Car {

    private int price;
    private int maxSpeed;

    public Car() {
        System.out.println("调用构造方法");
    }

    public Car(int price, int maxSpeed) {

        this.price = price;
        this.maxSpeed = maxSpeed;
    }

    public void initMethod(){
        System.out.println("调用了init方法");
    }

    public void destoryMethod(){
        System.out.println("调用了destory方法");
    }



    @Override
    public String toString() {
        return "Car{" +
                "price=" + price +
                ", maxSpeed=" + maxSpeed +
                '}';
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        System.out.println("调用了set方法");
        this.price = price;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

}
