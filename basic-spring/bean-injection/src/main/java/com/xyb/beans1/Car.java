package com.xyb.beans1;

public class Car {
    private int price;

    public Car() {
    }

    @Override
    public String toString() {
        return "Car{" +
                "price=" + price +
                '}';
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Car(int price) {

        this.price = price;
    }
}
