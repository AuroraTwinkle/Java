package com.aurora.person;

public class Person{
    protected int life;
    public String name;

    public Person(){
        this.name="bob";
        this.life=100;
    }

    public Person(String name,int life){
        this.name=name;
        this.life=life;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class Student extends Person{
    public Student(){}
    public Student(String name,int life,String sid,float grade){
        super(name,life);
        this.sid=sid;
        this.grade=grade;
    }
    public String sid;
    private float grade;
}