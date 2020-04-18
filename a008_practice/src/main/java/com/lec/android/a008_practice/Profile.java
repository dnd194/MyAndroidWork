package com.lec.android.a008_practice;

import java.io.Serializable;

public class Profile implements Serializable {
    static String name;
    static String addr;
    static int age;

    public Profile() {
    }

    public Profile(String name, String addr, int age) {
        this.name = name;
        this.addr = addr;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
