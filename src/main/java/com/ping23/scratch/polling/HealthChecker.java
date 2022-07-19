package com.ping23.scratch.polling;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

/**
 * ServiceHealthChecker
 *
 * This class supports a methodical response to Service health issues/outages. We
 * use the Service health check endpoint to determine if the service is available.
 * If not available we poll until the state is restored to available. In both
 * cases we communicate with TemplateNode about the available status.
 */
public final class HealthChecker {

    /** Health Check Failed error message */
    private static final String ERR_Service_HEALTH_CHECK_FAILED = "Service Health Check Failed";

    private static Logger LOG = LoggerFactory.getLogger(HealthChecker.class);

    /** Service connection timeout value */
    private static final int TIMEOUT_MS = 5000;

    /** Timer instance */
    private static Timer timer;

    /** Custom TimerTask instance */
    private static ServiceHealthCheckTimerTask timerTask;

    private static int testCounter = 0;
    
    private static String baseURL = "https://www.ping23.com";
    

    /**
     * Constructor
     */
    protected HealthChecker() {
    }

    /**
     * Poll for Service health restored
     *
     * This method starts a timer that will poll Service health check endpoint (See
     * ServiceHealthCheckTimerTask for details). When Service health is restored we send
     * appropriate notifications.
     */
    protected void pollForServiceHealthRestored() {

        // if the polling operation is already active then don't start again
        if (isPolling()) {
            return;
        }

        // do an initial check - if successful then communicate service available and
        // return - no need to continue
        if (checkServiceHealth()) {
            healthCheckPassed();
            return;
        }

        LOG.debug("Start Service Health Check polling");

        // start polling timer
        timer = new Timer("ServiceHealthCheckPollingTimer");
        timerTask = new ServiceHealthCheckTimerTask(this, 5_000);
        timer.schedule(timerTask, 0, 1000);

    }

    /**
     * Check Service health
     *
     * Any exceptions are caught within the method body and yield a false return
     * value
     *
     * @return true if Service health check is successful, false otherwise
     */
    protected static boolean checkServiceHealth() {

        if (testCounter > 4) {
            testCounter = 0;
            return true;
        } else {
            testCounter++;
        }

        ServiceResponse response;
        try {
            response = sendHealthCheckRequest();
        } catch (final Exception e) {
            LOG.error("checkServiceHealth: {}", ERR_Service_HEALTH_CHECK_FAILED);
            // if we fail with exception here then return false
            return Boolean.FALSE;
        }

        if (response == null) {
            LOG.error("checkServiceHealth: {}", ERR_Service_HEALTH_CHECK_FAILED);
            return Boolean.FALSE;
        }

        final int status = response.getStatusCode();
        LOG.debug("checkServiceHealth: Received response from Service: {}", status);

        if (status != HttpURLConnection.HTTP_OK) {
            LOG.error("checkServiceHealth: {}", ERR_Service_HEALTH_CHECK_FAILED);
            return Boolean.FALSE;
        }

        LOG.debug("checkServiceHealth: success");
        return Boolean.TRUE;
    }

    /**
     * Send Health Check request to Service
     *
     * @return the ServiceResponse
     */
    private static ServiceResponse sendHealthCheckRequest() throws Exception {

        final String endpoint = baseURL + "/status/health";
        LOG.debug("sendHealthCheckRequest: Sending Service request to: GET {}", endpoint);

        final String method = "GET";
        final Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json; utf-8");

        ServiceResponse response;
        try {
            final URL url = new URL(endpoint);
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
            connection.setConnectTimeout(TIMEOUT_MS);

            for (final Map.Entry<String, String> entry : headers.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }

            // obtain response
            final int responseCode = connection.getResponseCode();
            final StringBuilder responseStringBuilder = new StringBuilder();
            String decodedString;
            try (InputStream inputStream = responseCode < 400 ? connection.getInputStream()
                    : connection.getErrorStream();
                    final BufferedReader inputReader = new BufferedReader(
                            new InputStreamReader(inputStream, "utf-8"));) {
                while ((decodedString = inputReader.readLine()) != null) {
                    responseStringBuilder.append(decodedString);
                }
            }

            final String responseBody = responseStringBuilder.toString();
            Error error = null;
            if (responseBody != null && responseBody.contains("errorMessage")) {
                error = new Gson().fromJson(responseBody, Error.class);
            }
            response = new ServiceResponse(responseCode, responseBody, error);

        } catch (final IOException ioException) {
            LOG.error("sendRequest: Exception while sending request to Service endpoint.", ioException);
            throw new Exception("Cannot contact the Service server.", ioException);
        }

        return response;
    }

    /**
     * What to do if Health Check failed
     */
    protected void healthCheckFailed() {

        LOG.debug("healthCheckFailed");
        System.out.println("healthCheckFailed");

    }

    /**
     * What to do if Health Check passed
     */
    protected void healthCheckPassed() {

        LOG.debug("healthCheckPassed");
        System.out.println("healthCheckPassed");

        // make sure polling is cancelled
        cancelPolling();

    }

    /**
     * Cancel Polling
     */
    private static void cancelPolling() {
        if (timerTask != null) {
            timerTask.cancel();
            timerTask = null;
        }
        if (timer != null) {
            timer.cancel();
            timer = null;
        }

        LOG.debug("cancelled polling");
    }

    /**
     * are we currently polling?
     * 
     * @return the answer
     */
    private static boolean isPolling() {
        return timer != null && timerTask != null;
    }

}

/**
 *
 * This TimerTask will poll the Service Health Check endpoint for a successful
 * response. When the condition is satisfied the task will terminate the calling
 * Timer.
 *
 * The task is expecting to be executed once per second. The Service Health Check
 * will occur at intervals of increasing duration.
 *
 */
final class ServiceHealthCheckTimerTask extends TimerTask {

    private static Logger LOG = LoggerFactory.getLogger(ServiceHealthCheckTimerTask.class);

    /** ceiling for polling delay value */
    private static final int MAX_POLLING_DELAY = 30_000;

    /** what value to check for to satisfy operation */
    private static final boolean POLLING_CONDITION = Boolean.TRUE;

    /** how much to increase delay after each check */
    private int pollingDelayIncrement = 5_000;

    /** delay time in milliseconds to wait before next check */
    private int pollingDelay = pollingDelayIncrement;

    /** timestamp of last check */
    private long lastPollTime = 0;
    
    private final HealthChecker ServiceHealthChecker;

    /**
     * Default Constructor
     * 
     * @param pollingDelayIncrement
     */
    protected ServiceHealthCheckTimerTask(final HealthChecker ServiceHealthChecker) {
        this.ServiceHealthChecker = ServiceHealthChecker;
        // use default pollingDelay values
    }

    /**
     * Constructor
     * 
     * @param pollingDelayIncrement
     */
    protected ServiceHealthCheckTimerTask(final HealthChecker ServiceHealthChecker, final int pollingDelayIncrement) {
        this.ServiceHealthChecker = ServiceHealthChecker;
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
            final boolean healthCheckValue = HealthChecker.checkServiceHealth();

            LOG.debug("Service Health Check value: {}", healthCheckValue);
            System.out.println("Service Health Check value: " + healthCheckValue);

            if (healthCheckValue == POLLING_CONDITION) {
                LOG.debug("Service Health Check condition satisfied; cancelling.");

                // signal health check passed
                // this also cancels the polling operation
                ServiceHealthChecker.healthCheckPassed();

            } else {
                // record last polling time
                lastPollTime = now;
                // increase polling delay by POLLING_DELAY_INCREMENT, up to max
                pollingDelay += pollingDelay < MAX_POLLING_DELAY ? pollingDelayIncrement : 0;
            }

        }
    }
}
