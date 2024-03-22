package com.ping23.scratch.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.SSLContext;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;

/**
 * HTTPConnection
 *
 * This class exists solely to properly initialize the HttpURLConnection class
 *
 * The class does not, by default, support the PATCH method in all contexts
 * (specifically TemplateHttpServer). By statically initializing this class we
 * ensure that the PATCH method is supported.
 */
public final class HTTPConnection {

    // get the custom Gson instance
    private static final Gson GSON = new Gson();

    private static final Logger LOG = LogManager.getLogger(HTTPConnection.class);

    static {
        // make sure PATCH method is supported
        // this is necessary in some contexts, including TemplateHttpServer
        allowMethods("PATCH");
    }

    private final int timeoutMilliseconds = 5000;

    /**
     * Constructor
     */
    protected HTTPConnection() {
    }

    /**
     * Needed to support PATCH method in some contexts
     *
     * @param methods
     */
    private static void allowMethods(final String... methods) {
        try {
            final Field methodsField = HttpURLConnection.class.getDeclaredField("methods");

            final Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(methodsField, methodsField.getModifiers() & ~Modifier.FINAL);

            methodsField.setAccessible(true);

            final String[] oldMethods = (String[]) methodsField.get(null);
            final Set<String> methodsSet = new LinkedHashSet<>(Arrays.asList(oldMethods));
            methodsSet.addAll(Arrays.asList(methods));
            final String[] newMethods = methodsSet.toArray(new String[0]);

            methodsField.set(null/* static field */, newMethods);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * Send request
     *
     * @param endpoint
     * @param method
     * @param headers
     * @param body
     *
     * @return the HTTPResponse
     */
    protected HTTPResponse sendRequest(String endpoint, final String method, final Map<String, String> headers,
            final String body) throws HTTPException {
        if (StringUtils.isBlank(endpoint) || StringUtils.isBlank(method)) {
            throw new HTTPException("Empty params in sendRequest(), cannot proceed");
        }

        // sanitize spaces in endpoint to %20
        endpoint = endpoint.replaceAll(" ", "%20");

        LOG.debug("sendRequest() sending to endpoint {}", endpoint);

        HTTPResponse response = null;

        try {

            HttpURLConnection connection = HttpURLConnectionProvider.getHttpURLConnection(endpoint);

            connection.setRequestMethod(method);
            connection.setConnectTimeout(timeoutMilliseconds);

            if (headers != null) {
                for (final Map.Entry<String, String> entry : headers.entrySet()) {
                    connection.setRequestProperty(entry.getKey(), entry.getValue());
                }
            }

            // send the request
            // include body for appropriate methods
            if (SupportedMethods.methodExpectsRequestBody(method) && body != null) {
                connection.setDoOutput(true);
                try (OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream())) {
                    out.write(body);
                }
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
            HTTPError httpError = null;
            if (responseBody != null && responseBody.contains("errorMessage")) {
                httpError = GSON.fromJson(responseBody, HTTPError.class);
            }
            response = new HTTPResponse(responseCode, responseBody, httpError);

        } catch (final IOException ioException) {

            LOG.error(Constants.SERVICE_UNAVAILABLE + "sendRequest: Exception while sending request to endpoint.",
                    ioException);
            throw new HTTPException(Constants.SERVICE_UNAVAILABLE + ": Cannot contact the server.", ioException);
        }

        return response;
    }

    /**
     * Get the endpoint URL
     *
     * @param root
     * @param pathComponents
     *
     * @return the endpoint URL
     */
    protected static String getUrlWithPath(final String root, final String... pathComponents) {

        if (StringUtils.isBlank(root)) {
            return StringUtils.EMPTY;
        } else if (pathComponents.length < 1) {
            return root;
        }

        // start with root - get rid of trailing slash
        final StringBuilder endpoint = new StringBuilder(StringUtils.removeEnd(root, "/"));

        // add correctly punctuated path components
        for (final String pathComponent : pathComponents) {
            if (!pathComponent.startsWith("/")) {
                endpoint.append("/");
            }

            // get rid of trailing slashes at each step
            endpoint.append(StringUtils.removeEnd(pathComponent, "/"));
        }

        return endpoint.toString();
    }

}
