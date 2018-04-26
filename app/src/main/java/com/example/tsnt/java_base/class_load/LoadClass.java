package com.example.tsnt.java_base.class_load;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-04-26 11:06
 * @Description:
 */

public class LoadClass {
    public static void main(String[] args) {
        boolean createdInMainThead = false;
        System.out.println("Main Thread is " + Thread.currentThread().getName());
        if (createdInMainThead) {
            // 主线程中创建
            new TestClass1();
        } else {
            // 子线程中创建
            new Thread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("Son Thread is " + Thread.currentThread().getName());
                    new TestClass1();
                }
            }).start();
        }
    }
}
