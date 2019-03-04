package com.ping23.concurrency.SafePublication;

/**
 * Exchange the reference through a properly locked field (JLS 17.4.5)
 */
public class SynchronizedCLFactory {
    private Singleton instance;

    public Singleton getInstance() {
        synchronized (this) {
            if (instance == null) {
                instance = new Singleton();
            }
            return instance;
        }
    }
}
