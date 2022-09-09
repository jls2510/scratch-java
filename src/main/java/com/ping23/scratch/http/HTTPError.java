package com.ping23.scratch.http;

/**
 * Error response. This is an immutable class.
 */
public final class HTTPError {

    private final String errorCode;
    private final String errorMessage;
    private final Integer status;
    private final String timestamp;

    /**
     * Constructor
     *
     * @param errorCode
     * @param errorMessage
     * @param status
     * @param timestamp
     */
    public HTTPError(final String errorCode, final String errorMessage, final Integer status, final String timestamp) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.status = status;
        this.timestamp = timestamp;
    }

    /**
     * Gets the error code.
     *
     * @return the error code
     */
    protected String getErrorCode() {
        return errorCode;
    }

    /**
     * Gets the error message.
     *
     * @return the error message
     */
    protected String getErrorMessage() {
        return errorMessage;
    }

    protected Integer getStatus() {
        return status;
    }

    /**
     * Gets the timestamp.
     *
     * @return the timestamp
     */
    protected String getTimestamp() {
        return timestamp;
    }

}
