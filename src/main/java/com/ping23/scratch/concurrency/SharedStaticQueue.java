package com.ping23.scratch.concurrency;

import java.util.concurrent.ConcurrentLinkedQueue;

public class SharedStaticQueue {

    public static ConcurrentLinkedQueue<String> myQueue =
        new ConcurrentLinkedQueue<String>();

}
