package com.ping23.scratch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IdentifyMethodCaller {

    private static final Logger LOG = LoggerFactory.getLogger(IdentifyMethodCaller.class);

    public static void main(String[] args) {
        
        method1();

    }
    
    private static void method1() {
        method2();
        
    }
    
    
    private static void method2() {
        
        LOG.debug("method2 called from methodName: " + Thread.currentThread().getStackTrace()[2].getMethodName());
        LOG.debug("method2 called from fileName: " + Thread.currentThread().getStackTrace()[2].getFileName());
        LOG.debug("method2 called from className: " + Thread.currentThread().getStackTrace()[2].getClassName());

        LOG.debug("self class getCanonicalName: " + IdentifyMethodCaller.class.getCanonicalName());
}

}
