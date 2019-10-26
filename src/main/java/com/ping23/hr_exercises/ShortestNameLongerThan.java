package com.ping23.hr_exercises;

public class ShortestNameLongerThan {

	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String[] names = { "Sam", "Ed", "Joanna", "Franklin", "Mandy", "Roosevelt", "Kim", "Fred" };
		int longerThan = 4;
		String answer = "Superkalafragilisticexpialadocious";

		for (String name : names) {
			if (name.length() > longerThan && (answer.length() == 0 || name.length() < answer.length())) {
				answer = name;
			}

		}
		
		System.out.println("answer = " + answer);

	}

}
