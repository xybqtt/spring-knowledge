package com.xyb.beanFactory5;

public class Car {

    private int price;
    private String brand;

    public Car() {
    }

    public Car(int price, String brand) {

        this.price = price;
        this.brand = brand;
    }

    public int getPrice() {

        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Car{" +
                "price=" + price +
                ", brand='" + brand + '\'' +
                '}';
    }
}
