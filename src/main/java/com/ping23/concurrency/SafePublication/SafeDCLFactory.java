package com.ping23.concurrency.SafePublication;

/**
 * Exchange the reference via a volatile field (JLS 17.4.5)
 */
public class SafeDCLFactory {
    private volatile Singleton instance;

    public Singleton getInstance() {
        if (instance == null) {  // check 1
            synchronized (this) {
                if (instance == null) { // check 2
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

