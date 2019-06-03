package com.xyb.beanAutowire2;

public class Car {

    private int price;
    private int maxSpeed;

    public Car() {
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    @Override
    public String toString() {
        return "Car{" +
                "price=" + price +
                ", maxSpeed=" + maxSpeed +
                '}';
    }
}
