package com.ping23.scratch.reflect;

import java.util.ArrayList;
import java.util.List;

public class ReflectionExercise {

	public static void main(String[] args) {

		// this demonstrates that reflection has access to all original class fields
		// regardless of reference type

		Location myLocation = new Location();
		List<String> addressLines = new ArrayList<>();
		addressLines.add("5 Upper Hook Road");
		myLocation.setAddressLines(addressLines);
		myLocation.setCity("Rhinebeck");
		myLocation.setState("NY");
		myLocation.setCountry("USA");
		myLocation.setPostalCode("12572");

		// cast to anything, in this case empty interface
		Thing myThing = myLocation;

		// also gets cast to Object in method
		ExamineWithReflection.examine(myThing);

	}

}
