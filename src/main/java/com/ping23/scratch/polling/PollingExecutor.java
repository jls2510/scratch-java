package com.ping23.scratch.polling;

import java.util.Timer;

/**
 * Execute a polling operation
 */
public class PollingExecutor {
    
    private static boolean conditionSatisfied = Boolean.FALSE;
    private static int count = 0;
    
    private static Timer timer;
    private static PollingTask timerTask;

    // what condition are we looking to be satisfied?
    private static final int POLLING_CONDITION = 5;
    
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
        // Fire off monitor thread
        timer = new Timer("PollingTask");
        timerTask = new PollingTask(POLLING_CONDITION);
        timer.schedule(timerTask, 0, 1000);
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

    /**
     * Cancel Polling
     */
    protected static void cancelPolling() {
        if (timerTask != null) {
            System.out.println("Cancelling timerTask");
            timerTask.cancel();
            timerTask = null;
        }
        if (timer != null) {
            System.out.println("Cancelling timer");
            timer.cancel();
            timer = null;
        }
        
    }

}
