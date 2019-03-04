package com.ping23.concurrency.SafePublication;

/**
 * Exchange the reference via a volatile field (JLS 17.4.5)
 */
public class SafeLocalDCLFactory {
    private volatile Singleton instance;

    public Singleton getInstance() {
        Singleton res = instance;
        if (res == null) {
            synchronized (this) {
                res = instance;
                if (res == null) {
                    res = new Singleton();
                    instance = res;
                }
            }
        }
        return res;
    }
}