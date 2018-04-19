package com.example.tsnt.design_pattern.producer_and_consumer;

import java.util.List;
import java.util.Random;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-04-19 22:13
 * @Description:
 */

public class Producer implements Runnable {
    private int length;
    private List<Product> queue;

    public Producer(List<Product> queue, int length) {
        this.queue = queue;
        this.length = length;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (Thread.currentThread().isInterrupted()) break;
                Random r = new Random();
                int temp = r.nextInt(100);
                synchronized (queue) {
                    if (queue.size() >= length) {
                        queue.notifyAll();
                        queue.wait();
                    } else {
                        System.out.println("生产者" + Thread.currentThread().getId() +
                                "生产了：" + temp);
                        Product product = new Product(temp);
                        queue.add(product);
                    }
                }
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
