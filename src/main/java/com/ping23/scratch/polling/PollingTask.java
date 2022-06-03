package com.ping23.scratch.polling;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class PollingTask extends TimerTask {

    private static final int MAX_POLLING_DELAY = 300000;
    private static final int POLLING_DELAY_INCREMENT = 1000;

    private static Timer timer;

    private static int pollingDelay = 0;
    private static long lastPoll = 0;

    /**
     * Constructor
     * @param timer
     */
    public PollingTask(Timer timer) {
        PollingTask.timer = timer;
    }

    /** run */
    public void run() {

        long now = new Date().getTime();

        if (lastPoll + pollingDelay <= now) {
            
            // reset count
            PollingExecutor.setCount(0);

            Integer pollingTargetValue = PollingTarget.requestTargetValue();
            System.out.println("Polling condition target value: " + pollingTargetValue);

            if (pollingTargetValue > 5) {
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
