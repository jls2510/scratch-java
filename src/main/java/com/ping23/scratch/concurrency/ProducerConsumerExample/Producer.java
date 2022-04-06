package com.ping23.scratch.concurrency.ProducerConsumerExample;

import java.util.Random;

public class Producer implements Runnable {
    private MessageBroker messageBroker;

    public Producer(MessageBroker messageBroker) {
        this.messageBroker = messageBroker;
    }

    public void run() {
        String importantInfo[] = {
                "Mares eat oats",
                "Does eat oats",
                "Little lambs eat ivy",
                "A kid will eat ivy too"
        };
        Random random = new Random();

        for (int i = 0;
             i < importantInfo.length;
             i++) {
            System.out.println("\nProducer putting line: " + importantInfo[i]);
            messageBroker.put(importantInfo[i]);
            try {
                System.out.println("Producer going to sleep...");
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                System.out.println("Producer sleep interrupted"); // this never happens

            }
        }
        messageBroker.put("DONE");
    }
}