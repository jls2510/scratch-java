package com.ping23.scratch;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class PhysicsTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetAverageSpeed() {
		
		double deltaS = 47.5;
		double deltaT = 13.12	;
		double expected = deltaS / deltaT;
		
		double actual = Physics.getAverageSpeed(deltaS, deltaT);
		
		Assert.assertTrue(expected == actual);
				
		//Assert.fail("Not yet implemented");
	}

}
