package com.ping23.scratch.polling;

import java.util.Timer;

/**
 * Execute a polling operation
 */
public class PollingExecutor {
    
    private static boolean conditionSatisfied = Boolean.FALSE;
    private static int count = 0;
    
    /**
     * main
     */
    public static void main(String[] args) {
        System.out.println("main() starting");
        
        // start the polling operation
        startPolling();
        
        while(!conditionSatisfied) {
            try {
                System.out.println("waiting in main thread for condition: " + ++count);
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
        System.out.println("condition satisfied; main() finished");
    }
    
    /**
     * Start the polling operation
     */
    private static void startPolling() {
        // what condition are we looking to be satisfied?
        int pollingCondition = 6;
        
        // Fire off monitor thread
        Timer timer = new Timer("PollingTask");
        PollingTask pollingTask = new PollingTask(timer, pollingCondition);
        timer.schedule(pollingTask, 1000, 1000);
        System.out.println("startPolling() finished");
    }
    
    /**
     * set conditionSatisfied value
     * @param conditionSatisfied
     */
    protected static void setConditionSatisfied(boolean conditionSatisfied) {
        PollingExecutor.conditionSatisfied = conditionSatisfied;
    }
    
    /**
     * set count value
     * @param count
     */
    protected static void setCount(int count) {
        PollingExecutor.count = count;
    }

}
