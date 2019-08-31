package com.ping23.concurrency.ProducerConsumerExample;

public class MessageBroker {
    // Message sent from producer
    // to consumer.
    private String message;
    // True if consumer should wait
    // for producer to send message,
    // false if producer should wait for
    // consumer to retrieve message.
    private boolean empty = true;

    public synchronized String take() {
        System.out.println("MessageBroker.take() called");
        // Wait until message is
        // available.
        while (empty) {
            System.out.println("MessageBroker.take() EMPTY, can't give you what I don't have!");
            try {
                System.out.println("MessageBroker.take() starting wait");
                wait();
            } catch (InterruptedException e) {
                System.out.println("MessageBroker.take() wait interrupted");
            }
        }
        // Toggle status.
        empty = true;
        // Notify producer that
        // status has changed.
        System.out.println("MessageBroker.take() notifying and returning message: " + message);
        notifyAll();
        return message;
    }

    public synchronized void put(String message) {
        System.out.println("\nMessageBroker.put() called with String: " + message);
        // Wait until message has
        // been retrieved.
        while (!empty) {
            System.out.println("MessageBroker.put() NOT EMPTY, can't accept message: " + message);
            try {
                System.out.println("MessageBroker.put() starting wait");
                wait();
            } catch (InterruptedException e) {
                System.out.println("MessageBroker.put() wait interrupted"); // this never happens
            }
        }
        // Toggle status.
        empty = false;
        // Store message.
        this.message = message;
        // Notify consumer that status
        // has changed.
        System.out.println("MessageBroker.put() accepting message: " + message + ", notifying and returning");
        notifyAll();
    }
}
