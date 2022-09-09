package com.ping23.scratch.http;

/**
 * HTTPException
 */
public final class HTTPException extends Exception {

    private static final long serialVersionUID = 1L;
    private final String status;

    /**
     * Instantiates a new HTTPException
     *
     * @param cause the cause
     */
    public HTTPException(final Throwable cause) {
        super(cause);
        this.status = null;
    }

    /**
     * Instantiates a new HTTPException
     *
     * @param message the message
     */
    public HTTPException(final String message) {
        super(message);
        this.status = null;
    }

    /**
     * Instantiates a new HTTPException
     *
     * @param message the message
     * @param cause   the cause
     */
    public HTTPException(final String message, final Throwable cause) {
        super(message, cause);
        this.status = null;
    }

    /**
     * Instantiates a new HTTPException
     *
     * @param status  the status
     * @param message the message
     */
    public HTTPException(final String status, final String message) {
        super(message);
        this.status = status;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

}
