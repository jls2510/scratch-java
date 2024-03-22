package com.ping23.scratch.http;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Example {
    
    private static final Logger LOG = LogManager.getLogger(Example.class);

    public static void main(String[] args) {
        
        HTTPConnection httpConnection = new HTTPConnection();
        
        String endpoint = "http://www.jlsfineart.com";
        //String endpoint = "http://ping23.com";
        //String endpoint = "https://ping23.com/test";
        
        // NOTE see code in HttpURLConnectionProvider for configuring connection to trust self-signed certificates
        //String endpoint = "https://capella:81";
        
        String method = SupportedMethods.HTTP_GET.getName();
        
        HTTPResponse response = null;
        try {
            response = httpConnection.sendRequest(endpoint, method, null, null);
        } catch (HTTPException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        if (response != null) {
            LOG.info("Received Response: " + response.getResponseBody());
        } else {
            LOG.info("no response obtained");
        }
        
    }

}
