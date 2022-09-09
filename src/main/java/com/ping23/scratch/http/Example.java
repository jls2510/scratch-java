package com.ping23.scratch.http;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Example {
    
    private static final Logger LOG = LogManager.getLogger(Example.class);

    public static void main(String[] args) {
        
        HTTPConnection httpConnection = new HTTPConnection();
        
        //String endpoint = "http://www.jlsfineart.com";
        //String endpoint = "http://ping23.com";
        String endpoint = "https://ping23.com/test";
        
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
