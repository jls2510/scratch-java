package com.ping23.scratch.java8;

import java.util.function.Predicate;

public class Radioactive {
	
	static final String RADIOACTIVE = "Radioactive";
	static final String NOT_RADIOACTIVE = "Not Radioactive";

	public static void main(String[] args) {
		
		String currentRadioactiveStatus = NOT_RADIOACTIVE;
		
		boolean amIRadioactive = testIfRadioactive(currentRadioactiveStatus, new RadioactiveTester());
		System.out.println("amIRadioactive = " + amIRadioactive);
		
		currentRadioactiveStatus = RADIOACTIVE;
		
		boolean amILambdaRadioactive = testIfRadioactive(currentRadioactiveStatus, radioactiveStatus -> radioactiveStatus.equals(RADIOACTIVE));
		System.out.println("amILambdaRadioactive = " + amILambdaRadioactive);
		
	}
	
	static boolean testIfRadioactive(String radioactiveStatus, Predicate<String> radioactiveTester) {
		return radioactiveTester.test(radioactiveStatus);
	}

}

class RadioactiveTester implements Predicate<String> {
	public boolean test(String radioactiveStatus) {
		return radioactiveStatus.equals(Radioactive.RADIOACTIVE);
	}
}
