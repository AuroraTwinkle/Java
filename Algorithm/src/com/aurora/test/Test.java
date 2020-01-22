package com.aurora.test;

import com.aurora.person.Person;

import java.util.Arrays;
import java.util.Scanner;

public class Test {
    private String[] names;
    private int age;
    public void setName(String[] names){
        this.names=names;
        Person person=new Person();
        person.name="bob";
    }
    public String[] getNames(){
        return names;
    }
}

