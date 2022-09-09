package com.ping23.scratch.http;

/**
 * This is what we receive in response from endpoint. This is an immutable
 * class.
 */
public final class HTTPResponse {

    private final int statusCode;
    private final String responseBody;
    private final HTTPError errorResponse;

    /**
     * Instantiates a new response. This is an immutable class; note that all of the
     * constructor parameters are immutables.
     *
     * @param statusCode   the status code
     * @param responseBody the response body
     */
    public HTTPResponse(final int statusCode, final String responseBody) {
        this.statusCode = statusCode;
        this.responseBody = responseBody;
        this.errorResponse = null;
    }

    /**
     * Instantiates a new response. This is an immutable class; note that all of the
     * constructor parameters are immutables.
     *
     * @param statusCode   the status code
     * @param responseBody the response body
     * @param error        the error
     */
    public HTTPResponse(final int statusCode, final String responseBody, final HTTPError error) {
        this.statusCode = statusCode;
        this.responseBody = responseBody;
        this.errorResponse = error;
    }

    /**
     * Gets the status code.
     *
     * @return the status code
     */
    public int getStatusCode() {
        return statusCode;
    }

    /**
     * Gets the response body.
     *
     * @return the response body
     */
    public String getResponseBody() {
        return responseBody;
    }

    /**
     * Gets the error response.
     *
     * @return the error response
     */
    public HTTPError getErrorResponse() {
        return errorResponse;
    }
}
