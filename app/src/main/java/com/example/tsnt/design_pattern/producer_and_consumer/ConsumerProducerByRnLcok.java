package com.example.tsnt.design_pattern.producer_and_consumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: zhangxiaozong
 * @Date: 2019-01-22 17:45
 * @Description:
 */

public class ConsumerProducerByRnLcok {

    public static void main(String[] args) {
        List<Product> queue = new ArrayList();
        int length = 10;
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition emptyCondition = reentrantLock.newCondition();
        Condition fullCondition = reentrantLock.newCondition();
        Producer p1 = new Producer(length, queue, reentrantLock, emptyCondition, fullCondition);
        Producer p2 = new Producer(length, queue, reentrantLock, emptyCondition, fullCondition);
        Producer p3 = new Producer(length, queue, reentrantLock, emptyCondition, fullCondition);
        Consumer c1 = new Consumer(queue, reentrantLock, emptyCondition, fullCondition);
        Consumer c2 = new Consumer(queue, reentrantLock, emptyCondition, fullCondition);
        Consumer c3 = new Consumer(queue, reentrantLock, emptyCondition, fullCondition);
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

        private ReentrantLock reentrantLock;
        private Condition emptyCondition;
        private Condition fullCondition;

        public Consumer(List<Product> queue, ReentrantLock reentrantLock, Condition emptyCondition, Condition fullCondition) {
            this.queue = queue;
            this.reentrantLock = reentrantLock;
            this.emptyCondition = emptyCondition;
            this.fullCondition = fullCondition;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    reentrantLock.lock();
                    if (queue.size() == 0) {
                        emptyCondition.await();
                    } else {
                        Product product = queue.remove(0);
                        System.out.println("Consumer" + Thread.currentThread().getId() +
                                " consume: " + product.getData());
                        fullCondition.signalAll();
                    }
                } catch (Exception e) {
                } finally {
                    reentrantLock.unlock();
                }

                try {
                    Thread.sleep(100);
                } catch (Exception e) {

                }
            }
        }
    }

    static class Producer implements Runnable {

        public static int index = 0;

        private int maxLength;
        private List<Product> queue;

        private ReentrantLock reentrantLock;
        private Condition emptyCondition;
        private Condition fullCondition;

        public Producer(int maxLength, List<Product> queue,
                        ReentrantLock reentrantLock, Condition emptyCondition, Condition fullCondition) {
            this.maxLength = maxLength;
            this.queue = queue;
            this.reentrantLock = reentrantLock;
            this.emptyCondition = emptyCondition;
            this.fullCondition = fullCondition;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    reentrantLock.lock();
                    if (queue.size() == maxLength) {
                        fullCondition.await();
                    } else {
                        Product product = new Product(index++);
                        queue.add(product);
                        System.out.println("Producer" + Thread.currentThread().getId() +
                                " produce：" + product.getData());
                        emptyCondition.signalAll();
                    }
                } catch (Exception e) {
                } finally {
                    reentrantLock.unlock();
                }

                try {
                    Thread.sleep(100);
                } catch (Exception e) {

                }
            }
        }
    }
}
