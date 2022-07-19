package com.ping23.scratch.polling;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HealthChecker
 *
 * This class supports a methodical response to  health issues/outages. We
 * use the  health check endpoint to determine if the service is available.
 * If not available we poll until the state is restored to available. In both
 * cases we communicate with TemplateNode about the available status.
 */
public final class ClientHealthChecker {

    private static Logger LOG = LoggerFactory.getLogger(ClientHealthChecker.class);

    /** Timer instance */
    private Timer timer;

    /** Custom TimerTask instance */
    private ClientHealthCheckTimerTask timerTask;

    /**
     * Constructor
     */
    protected ClientHealthChecker() {
    }

    /**
     * Poll for  health restored
     *
     * This method starts a timer that will poll  health check endpoint (See
     * HealthCheckTimerTask for details). When  health is restored we send
     * appropriate notifications.
     */
    private void pollForHealthRestored() {

        // if the polling operation is already active then don't start again
        if (isPolling()) {
            return;
        }

        LOG.debug("Start  Health Check polling");
        System.out.println("Start  Health Check polling");

        // start polling timer
        timer = new Timer("HealthCheckPollingTimer");
        timerTask = new ClientHealthCheckTimerTask(this, 5_000);
        timer.schedule(timerTask, 0, 1000);
    }

    /**
     * Verify  Health
     * 
     * @param lbtServiceEnabled
     * @param lbtServiceAvailable
     */
    protected void verifyHealth(final boolean lbtServiceEnabled, final boolean lbtServiceAvailable) {

        System.out.println("ClientHealthChecker.verifyHealth");
        // only applicable if service is enabled
        if (lbtServiceEnabled) {
            System.out.println("service is enabled");
            // if service is not available then poll for service restored
            if (!lbtServiceAvailable) {
                System.out.println("service is NOT available");
                pollForHealthRestored();
            } else if (isPolling()) {
                System.out.println("service is avalable - cancelling polling");
                cancelPolling();
            }
        }
    }

    /**
     * Check  health
     *
     * Any exceptions are caught within the method body and yield a false return
     * value
     */
    protected void checkHealth() {
        System.out.println("sending  healt check request");
        PollingTarget.requestServiceStatus();
    }

    /**
     * Cancel Polling
     */
    private void cancelPolling() {
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        LOG.debug("cancelled polling");
        System.out.println("cancelled polling");
    }

    /**
     * are we currently polling?
     * 
     * @return the answer
     */
    private boolean isPolling() {
        return timer != null && timerTask != null;
    }

}

/**
 *
 * This TimerTask will poll the  Health Check endpoint for a successful
 * response. When the condition is satisfied the task will terminate the calling
 * Timer.
 *
 * The task is expecting to be executed once per second. The  Health Check
 * will occur at intervals of increasing duration.
 *
 */
final class ClientHealthCheckTimerTask extends TimerTask {

    private static Logger LOG = LoggerFactory.getLogger(ClientHealthCheckTimerTask.class);

    /** ceiling for polling delay value */
    private static final int MAX_POLLING_DELAY = 300_000;

    /** how much to increase delay after each check */
    private int pollingDelayIncrement = 5_000;

    /** delay time in milliseconds to wait before next check */
    private int pollingDelay = pollingDelayIncrement;

    /** timestamp of last check */
    private long lastPollTime = 0;

    private ClientHealthChecker clientHealthChecker;

    /**
     * Constructor
     * 
     * @param pollingDelayIncrement
     */
    protected ClientHealthCheckTimerTask(final ClientHealthChecker clientHealthChecker,
            final int pollingDelayIncrement) {
        this.clientHealthChecker = clientHealthChecker;
        this.pollingDelayIncrement = pollingDelayIncrement;
        this.pollingDelay = pollingDelayIncrement;
    }

    /**
     * run
     */
    @Override
    public void run() {

        // check the polling condition if we're past the current delay
        final long now = new Date().getTime();

        // first time through
        if (lastPollTime == 0) {
            // initialize lastPollTime
            lastPollTime = now;
            return;
        }

        // System.out.println("pollingDelay: " + pollingDelay);

        if (lastPollTime + pollingDelay <= now || lastPollTime + MAX_POLLING_DELAY <= now) {

            // perform the check
            clientHealthChecker.checkHealth();

            // record last polling time
            lastPollTime = now;
            // increase polling delay by POLLING_DELAY_INCREMENT, up to max
            pollingDelay += pollingDelay < MAX_POLLING_DELAY ? pollingDelayIncrement : 0;
        }
    }

}
