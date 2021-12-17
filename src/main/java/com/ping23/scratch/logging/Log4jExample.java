package com.ping23.scratch.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Log4jExample {


	  private static Logger log = LogManager.getLogger(Log4jExample.class);
	  
	   public static void main(String[] args) {
	      //log.setLevel(Level.WARN);

	      log.trace("Trace Message!");
	      log.debug("Debug Message!");
	      log.info("Info Message!");
	      log.warn("Warn Message!");
	      log.error("Error Message!");
	      log.fatal("Fatal Message!");
	   }

}
