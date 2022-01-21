package com.ping23.scratch.concurrency.SafePublication;

/**
 * Singleton class
 */
class Singleton {
    private int value = 0;

    void increment() {
        value++;
    }

    @Override
    public String toString() {
        return "value = " + value;
    }
}

