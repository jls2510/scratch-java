package com.ping23.scratch.java8;

import java.util.function.Function;

public class Greet {

	private static void print(String name, Function<String, String> greeting) {
		System.out.println(greeting.apply(name));
	}

	public static void main(String[] args) {
		print("Jim", name -> "Greetings, " + name + "!");
	}

}
