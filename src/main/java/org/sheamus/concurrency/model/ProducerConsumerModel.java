package org.sheamus.concurrency.model;

import java.util.concurrent.LinkedBlockingQueue;

public class ProducerConsumerModel {

    // 缓冲区
    private LinkedBlockingQueue<Integer> buffer;

    public ProducerConsumerModel() {
        // 使用 LinkedBlockingQueue 作为缓冲区
        buffer = new LinkedBlockingQueue<>();
    }

    // 生产者任务
    public static class ProducerTask implements Runnable {
        private ProducerConsumerModel model;

        public ProducerTask(ProducerConsumerModel model) {
            this.model = model;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 10; i++) {
                try {
                    System.out.println("Produced: " + i);
                    // 生产数据并放入缓冲区
                    model.buffer.put(i);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // 消费者任务
    public static class ConsumerTask implements Runnable {
        private ProducerConsumerModel model;

        public ConsumerTask(ProducerConsumerModel model) {
            this.model = model;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 10; i++) {
                try {
                    // 从缓冲区中消费数据
                    int data = model.buffer.take();
                    System.out.println("Consumed: " + data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumerModel model = new ProducerConsumerModel();

        // 创建生产者线程
        Thread producerThread = new Thread(new ProducerTask(model));
        // 创建消费者线程
        Thread consumerThread = new Thread(new ConsumerTask(model));

        producerThread.start();
        consumerThread.start();

        try {
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}