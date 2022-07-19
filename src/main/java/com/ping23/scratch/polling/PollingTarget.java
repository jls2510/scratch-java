package com.ping23.scratch.polling;

/**
 * PollingTarget
 *
 * This class simply provides a method to return int values from 1 to MAX_VALUE
 *
 */
public class PollingTarget {

    public static final int MAX_VALUE = 10;

    private static int targetValue = 0;

    private static int testCounter = 0;

    /**
     * Get the current target value
     * 
     * @return the value
     */
    public static int getCurrentTargetValue() {
        // return Integer values 1 thru 5
        if (targetValue % MAX_VALUE == 0) {
            targetValue = 0;
        }
        return ++targetValue;
    }

    public static boolean requestServiceStatus() {
        System.out.println("testCounter: " + testCounter);
        if (testCounter > 4) {
            testCounter = 0;
            System.out.println("testCounter > condition, so returning true");
            return true;
        } else {
            testCounter++;
        }

        return false;

    }
}
