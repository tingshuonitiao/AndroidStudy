package com.example.tsnt.design_pattern.producer_and_consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: zhangxiaozong
 * @Date: 2019-01-22 18:58
 * @Description:
 */

public class ConsumerProducerBySyn {

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

    static class Consumer implements Runnable {
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

    static class Producer implements Runnable {
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
}
