package com.ping23.scratch.http;

/**
 * The Enum SupportedMethods.
 */
public enum SupportedMethods {
    HTTP_DELETE("DELETE"), HTTP_GET("GET"), HTTP_PATCH("PATCH"), HTTP_POST("POST"), HTTP_PUT("PUT");

    private final String name;

    /**
     * Instantiates a new supported methods object.
     *
     * @param name the name
     */
    private SupportedMethods(final String name) {
        this.name = name;
    }

    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Checks if the method expects a request body
     *
     * By definition this includes POST, PUT, PATCH
     *
     * @param method
     * @return true, if the method expects a request body
     */
    public static boolean methodExpectsRequestBody(final String method) {
        return SupportedMethods.HTTP_POST.getName().equalsIgnoreCase(method)
                || SupportedMethods.HTTP_PUT.getName().equalsIgnoreCase(method)
                || SupportedMethods.HTTP_PATCH.getName().equalsIgnoreCase(method);
    }
}
