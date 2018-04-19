package com.example.tsnt.design_pattern.producer_and_consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-04-19 22:16
 * @Description:
 */

public class Test {
    public static void main(String[] args) {
        List<Product> queue = new ArrayList();
        int length = 10;
        Producer p1 = new Producer(queue, length);
        Producer p2 = new Producer(queue, length);
        Producer p3 = new Producer(queue, length);
        Consumer c1 = new Consumer(queue);
        Consumer c2 = new Consumer(queue);
        Consumer c3 = new Consumer(queue);
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(p1);
        service.execute(p2);
        service.execute(p3);
        service.execute(c1);
        service.execute(c2);
        service.execute(c3);
    }
}
