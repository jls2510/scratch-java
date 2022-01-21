package com.ping23.scratch.concurrency.SafePublication;


/**
 * Use static initializer to do the initializing stores (JLS 12.4)
 */
public class HolderFactory {
    public static Singleton getInstance() {
        return Holder.instance;
    }

    // Class initialization is done under the lock, as per JLS 12.4.2.
    // Class initialization lock provides the mutual exclusion during the class initialization,
    // that is, only a single thread can initialize the static fields.
    private static class Holder {
        static final Singleton instance = new Singleton();
    }
}
