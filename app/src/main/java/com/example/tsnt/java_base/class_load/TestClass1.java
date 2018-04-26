package com.example.tsnt.java_base.class_load;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-04-26 11:08
 * @Description:
 */

public class TestClass1 {

    public static TestClass2 sTestClass2 = new TestClass2();

    static class TestClass2 {
        public TestClass2() {
            System.out.println("TestClass2 is created in Thread " + Thread.currentThread().getName());
        }
    }
}
