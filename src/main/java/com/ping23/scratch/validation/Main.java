package com.ping23.scratch.validation;

public class Main {

    public static void main(String[] args) {

	final Location loc = new Location();
	
	loc.setCity("A"); // should not pass validation

	final Double lat = 100.0; // should not pass validation
	final Double lon = 1.0;

	try {
	    loc.setLatLon(new LatLon().setLat(lat).setLon(lon));
	} catch (Exception e) {
	    System.out.println("Error finding Latitude and Longitude.");
	}
	
	System.out.println("lat = " + loc.getLatLon().getLat());
	System.out.println("lon = " + loc.getLatLon().getLon());

    }

}
