package com.main.java.test;


public class TestService {
    private String na;
    private String name;

    public void setNa(String na) {
        this.na = na;
    }

    public String getNa() {
        return na;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {

        return name;
    }

    public void hello()
    {
        System.out.println("hello "+ getNa());
    }
}
