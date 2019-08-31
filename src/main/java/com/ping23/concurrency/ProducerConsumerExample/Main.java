package com.ping23.concurrency.ProducerConsumerExample;

public class Main {

    private ClockTicker clockTicker;

    public static void main(String[] args) {
        new Main().startTheParty();
    }

    public void startTheParty() {
        MessageBroker messageBroker = new MessageBroker();
        (new Thread(new Producer(messageBroker))).start();
        (new Thread(new Consumer(messageBroker, this))).start();

        clockTicker = new ClockTicker();
        (new Thread(clockTicker)).start();

    }

    public void stopTheClockTicker () {
        clockTicker.stop();
    }
}