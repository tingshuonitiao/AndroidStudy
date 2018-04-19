package com.example.tsnt.design_pattern.producer_and_consumer;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-04-19 22:11
 * @Description:
 */

public class Product {
    private final int data;

    public Product(int d) {
        data = d;
    }

    public int getData() {
        return data;
    }
}
