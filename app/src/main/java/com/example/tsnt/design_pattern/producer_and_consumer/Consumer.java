package com.example.tsnt.design_pattern.producer_and_consumer;

import java.util.List;

/**
 * @Author: tingshuonitiao
 * @Date: 2018-04-19 22:11
 * @Description:
 */

public class Consumer implements Runnable {
    private List<Product> queue;

    public Consumer(List<Product> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (Thread.currentThread().isInterrupted()) break;
                Product product;
                synchronized (queue) {
                    if (queue.size() == 0) {
                        queue.notifyAll();
                        queue.wait();
                    } else {
                        product = queue.remove(0);
                        System.out.println("消费者" + Thread.currentThread().getId() +
                                "消费了:" + product.getData());
                    }
                }
                Thread.sleep(100);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
