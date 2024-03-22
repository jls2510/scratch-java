package com.ping23.scratch.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * AppProperties
 * 
 *Returns properties from the named properties resource.
 * 
 */
public class AppProperties {
	
	private static final String defaultPropertiesResource = "application.properties";

    private final Properties properties;
    
    // static instance for default properties
    public static final AppProperties DEFAULT = new AppProperties();
    
    /**
     * Default constructor
     */
    public AppProperties() {
    	properties = getNamedProperties(defaultPropertiesResource);
    }

    /**
     * Constructor for named properties resource
     * @param propertiesResource
     */
    public AppProperties(String propertiesResource) {
    	properties = getNamedProperties(propertiesResource);
    }

    private Properties getNamedProperties(String propertiesResource) {
    	
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Properties namedProperties = new Properties();
        InputStream input;
        try {
            input = classLoader.getResourceAsStream(propertiesResource);
            namedProperties.load(input);
        } catch (final IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return namedProperties;
        
    }

    /**
     * Convenience wrapper method
     *
     * @param key
     * @return
     */
    public String getProperty(final String key) {
        return properties.getProperty(key);
    }

}
