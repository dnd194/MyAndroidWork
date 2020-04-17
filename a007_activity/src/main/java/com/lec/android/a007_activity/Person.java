package com.lec.android.a007_activity;


import java.io.Serializable;

// Intent에 담아 보내는 객체는 반 . 드 . 시 Serializable 되어 있어야한다.
public class Person implements Serializable {
    String name;
    int age;

    //alt + insert 로 생성자랑, getter/setter 생성 가능
    //우클릭 + generate 도 가능
    public Person() {
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
