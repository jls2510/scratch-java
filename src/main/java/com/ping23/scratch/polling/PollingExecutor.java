package com.ping23.scratch.polling;

import java.util.Timer;

public class PollingExecutor {
    
    private static boolean conditionSatisfied = Boolean.FALSE;
    
    private static int count = 0;
    
    public static void main(String[] args) {
        
        System.out.println("main() starting");
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
    
    
    private static void startPolling() {
        // Fire off monitor thread
        Timer timer = new Timer("PollingTask");
        PollingTask pollingTask = new PollingTask(timer);
        timer.schedule(pollingTask, 1000, 1000);
        System.out.println("startPolling() finished");
    }
    
    protected static void setConditionSatisfied(boolean conditionSatisfied) {
        PollingExecutor.conditionSatisfied = conditionSatisfied;
    }
    
    protected static void setCount(int count) {
        PollingExecutor.count = count;
    }

}
