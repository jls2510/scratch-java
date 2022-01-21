package com.ping23.scratch.concurrency.ProducerConsumerExample;

import java.util.Random;

public class Consumer implements Runnable {
    private MessageBroker messageBroker;
    private Main parent;

    public Consumer(MessageBroker messageBroker, Main parent) {
        this.parent = parent;
        this.messageBroker = messageBroker;
    }

    public void run() {
        Random random = new Random();
        for (String message = messageBroker.take(); !message.equals("DONE"); message = messageBroker.take()) {
            System.out.format("MESSAGE RECEIVED: %s%n", message);
            try {
                System.out.println("Consumer going to sleep...");
                Thread.sleep(random.nextInt(10000));
            } catch (InterruptedException e) {
                System.out.println("Consumer sleep interrupted");
            }
            System.out.println("\nConsumer calling messageBroker.take()");
        }

        parent.stopTheClockTicker();
    }
}