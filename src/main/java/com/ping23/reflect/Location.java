package com.ping23.reflect;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.List;

import lombok.Data;

@Data
public class Location implements Serializable, Thing {

	/** Class Members **/
	private static final long serialVersionUID = 1L;
	
	/** Instance Members **/
	private String country;
	private String postalCode;
	private String state;
	private String city;
	private List<String> addressLines;
	private Double lat;
	private Double lon;
	private ZoneId timeZone;
}
