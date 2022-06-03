package com.ping23.scratch.polling;

import java.util.Date;
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

    /** define the condition to be met to satisfy polling operation */
    private static final int POLLING_CONDITION = 5;
    
    private static int pollingDelay = POLLING_DELAY_INCREMENT;
    private static long lastPollTime = 0;

    /** run */
    public void run() {

        // check the polling condition if we're past the current delay
        final long now = new Date().getTime();

        // first time through
        if (lastPollTime == 0) {
            lastPollTime = now;
            return;
        }

        System.out.println("pollingDelay: " + pollingDelay);

        if (lastPollTime + pollingDelay <= now || lastPollTime + MAX_POLLING_DELAY <= now) {

            // reset count
            PollingExecutor.setCount(0);

            Integer pollingTargetValue = PollingTarget.getCurrentTargetValue();
            System.out.println("Polling condition target value: " + pollingTargetValue);

            if (pollingTargetValue >= POLLING_CONDITION) {
                System.out.println("Polling condition satisfied; cancelling.");

                // reset control values
                pollingDelay = POLLING_DELAY_INCREMENT;
                lastPollTime = 0;

                // indicate to caller that condition is satisfied
                // this also cancels the polling operation
                PollingExecutor.conditionSatisfied();
                
            } else {
                // record last polling time
                lastPollTime = now;
                // increase polling delay by POLLING_DELAY_INCREMENT, up to max
                pollingDelay += pollingDelay < MAX_POLLING_DELAY ? POLLING_DELAY_INCREMENT : 0;
            }

        }

    }

}
