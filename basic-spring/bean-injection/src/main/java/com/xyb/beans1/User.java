package com.xyb.beans1;

import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

public class User {

    // 使用构造器注入
    private String userName;
    private Car car1;

    // 使用set注入
    private int age;
    private Car car2;

    // 注入List、Map、Set和Properties
    private List<Object> list;
    private Map<String, Object> map;
    private Set<Object> set;
    private Properties properties;

    public User(String userName, Car car1, List<Object> list) {
        this.userName = userName;
        this.car1 = car1;
        this.list = list;
    }

    public Properties getProperties() {
        return properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public Set<Object> getSet() {
        return set;
    }

    public void setSet(Set<Object> set) {
        this.set = set;
    }

    public User() {
    }

    public User(Car car1) {
        this.car1 = car1;
    }

    public User(String userName, Car car1) {
        this.userName = userName;
        this.car1 = car1;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", car1=" + car1 +
                ", age=" + age +
                ", car2=" + car2 +
                ", list=" + list +
                ", map=" + map +
                ", set=" + set +
                ", properties=" + properties +
                '}';
    }

    public User(List<Object> list, Map<String, Object> map, Set<Object> set, Properties properties) {
        this.list = list;
        this.map = map;
        this.set = set;
        this.properties = properties;
    }

    public void setCar1(Car car1) {
        this.car1 = car1;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Car getCar1() {
        return car1;
    }

    public int getAge() {
        return age;
    }

    public Car getCar2() {
        return car2;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setCar2(Car car2) {
        this.car2 = car2;
    }

}

