package com.ping23.scratch.polling;

public class PollingTarget {
    
    private static Integer targetValue = 0;
    
    public static Integer requestTargetValue() {
        
        // return Integer values 1 thru 5
        if (targetValue % 10 == 0) {
            targetValue = 0;
        }
        
        return ++targetValue;
    }

}
