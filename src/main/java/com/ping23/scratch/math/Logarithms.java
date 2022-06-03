package com.ping23.scratch.math;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logarithms {
    private static Logger LOG = LoggerFactory.getLogger(Logarithms.class);
    
    
    // main
    public static void main(String args[])
    {
        double a = -2.55;
        double b = 1.0 / 0;
        double c = 0, d = 145.256;
          
  
        // negative integer as argument, output NAN
        LOG.debug("log(a): {}", Math.log(a));
        // positive infinity as argument, output Infinity
        LOG.debug("log(b): {}", Math.log(b));
        // positive zero as argument, output -Infinity
        LOG.debug("log(c): {}", Math.log(c));
        // positive double as argument
        LOG.debug("log(d): {}", Math.log(d));  
        
        LOG.debug("customLog(2, 256): {}", customLog(2, 256));
        LOG.debug("customLog(10, 100): {}", customLog(10, 100));
        
    }    
    
    /**
     * Common Logs
     */
    @Test
    public void givenLog10_shouldReturnValidResults() {
        assertEquals(2, Math.log10(100), 0.001);
        assertEquals(3, Math.log10(1000), 0.001);
    }

    /**
     * Natural Logs
     */
    @Test
    public void givenLog_shouldReturnValidResults() {
        assertEquals(1, Math.log(Math.E), 0.001);
        assertEquals(2.30258, Math.log(10), 0.001);
    }

    /**
     * Custom Base
     */
    @Test
    public void givenCustomLog_shouldReturnValidResults() {
        assertEquals(8, customLog(2, 256), 0.001);
        assertEquals(2, customLog(10, 100), 0.001);
    }

    private static double customLog(double base, double logNumber) {
        return Math.log(logNumber) / Math.log(base);
    }
    @Test
    public void logTest() {
        
        assertEquals(0, 0);
        
    }
    

}
