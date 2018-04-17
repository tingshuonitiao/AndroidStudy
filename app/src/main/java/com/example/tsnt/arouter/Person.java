package com.example.tsnt.arouter;

import com.alibaba.android.arouter.facade.annotation.Route;

/**
 * @Author: zhangxiaozong
 * @Date: 2018-04-16 21:29
 * @Description:
 */

public class Person {
    public String name;
    public int age;
    public String hometown;

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

    public String getHometown() {
        return hometown;
    }

    public void setHometown(String hometown) {
        this.hometown = hometown;
    }
}
