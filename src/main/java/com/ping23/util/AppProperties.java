package com.ping23.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class AppProperties {

    private static final Properties properties;
    static {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        properties = new Properties();
        InputStream input;
        try {
            input = classLoader.getResourceAsStream("application.properties");
            properties.load(input);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    /**
     * wrapper method
     * @param key
     * @return
     */
    public static String getProperty(final String key) {
        
        String value = properties.getProperty(key);
        
        // dereference environment variables
        if (value.startsWith("${")) {
            String envVar = value.replaceAll("\\$", "").replaceAll("\\{", "").replaceAll("\\}", "");
            value = System.getenv(envVar);
        }
        
        return value;
    }

}
