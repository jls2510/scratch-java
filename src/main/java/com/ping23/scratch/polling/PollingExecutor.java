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
        timerTask = new PollingTask();
        timer.schedule(timerTask, 0, 1000);
        System.out.println("startPolling() finished");
    }
    
    /**
     * What to do when the polling condition is satisfied
     * 
     * this also cancels the polling operation
     */
    protected static void conditionSatisfied() {
        // update the value
        PollingExecutor.conditionSatisfied = Boolean.TRUE;
        
        // cancel the polling operation
        cancelPolling();
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
    private static void cancelPolling() {
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
