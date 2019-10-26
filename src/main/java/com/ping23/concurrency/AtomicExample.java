package com.ping23.concurrency;

import java.util.concurrent.atomic.AtomicLong;

public class AtomicExample {
	
	private static AtomicLong counter = new AtomicLong();
	
	public static void main(String[] args) {
		
		for (int index = 0; index <= 100; ++index) {
			System.out.println("AtomicLong value for index " + index + " = " + counter.getAndIncrement());
		}
		
	}

}
