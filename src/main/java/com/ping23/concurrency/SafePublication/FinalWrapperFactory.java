package com.ping23.concurrency.SafePublication;

/**
 * Initialize the value into a final field (JLS 17.5)
 */
public class FinalWrapperFactory {
    private FinalWrapper wrapper;

    public Singleton getInstance() {
        FinalWrapper w = wrapper;
        if (w == null) { // check 1
            synchronized(this) {
                w = wrapper;
                if (w == null) { // check2
                    w = new FinalWrapper(new Singleton());
                    wrapper = w;
                }
            }
        }
        return w.instance;
    }

    private static class FinalWrapper {
        private final Singleton instance;
        FinalWrapper(Singleton instance) {
            this.instance = instance;
        }
    }
}
