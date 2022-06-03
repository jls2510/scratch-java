package com.ping23.scratch.polling;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * PollingTask
 * 
 * This TimerTask will poll a target for a given condition. When the condition
 * is satisfied the task will terminate the calling Timer.
 * 
 * The task is expecting to be executed once per second. The condition check
 * will occur at itervals of increasing duration.
 * 
 */
public class PollingTask extends TimerTask {

    private static final int MAX_POLLING_DELAY = 300000;
    private static final int POLLING_DELAY_INCREMENT = 1000;
    
    private static Timer timer;
    private static int pollingCondition = 10;

    private static int pollingDelay = 0;
    private static long lastPoll = 0;

    /**
     * Constructor
     * 
     * @param timer
     */
    public PollingTask(Timer timer, int pollingCondition) {
        PollingTask.timer = timer;
        PollingTask.pollingCondition = pollingCondition;
    }

    /** run */
    public void run() {

        // check the polling condition if we're past the current delay
        final long now = new Date().getTime();
        if (lastPoll + pollingDelay <= now || lastPoll + MAX_POLLING_DELAY <= now) {

            // reset count
            PollingExecutor.setCount(0);

            Integer pollingTargetValue = PollingTarget.getCurrentTargetValue();
            System.out.println("Polling condition target value: " + pollingTargetValue);

            if (pollingTargetValue >= pollingCondition) {
                System.out.println("Polling condition satisfied; cancelling.");
                PollingExecutor.setConditionSatisfied(Boolean.TRUE);
                timer.cancel();
            }

            // record last polling time
            lastPoll = now;
            // increase polling delay by POLLING_DELAY_INCREMENT, up to max
            pollingDelay += pollingDelay < MAX_POLLING_DELAY ? POLLING_DELAY_INCREMENT : 0;

        }

        // System.out.println("PollingTask.run() returning");

    }

}
