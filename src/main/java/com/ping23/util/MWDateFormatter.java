package com.ping23.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

/**
 * <p>Title: com.managementworlds.common.datetime.MWDateTimeFormatter</p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: Management Worlds, Inc.</p>
 *
 * @author David C. Browne
 * @version 1.0
 */
public class MWDateFormatter extends SimpleDateFormat
{
   /**
    * 
    */
   private static final long serialVersionUID = 4601032317677230024L;


   /**
    * format pattern string for the instance
    */
   private String formatPattern;


   /**
    * the default format pattern
    */
   static public String DEFAULT_PATTERN = "dd'-'MMM'-'yy";


   /**
    * the month of year format pattern
    */
   static public String MONTH_OF_YEAR_PATTERN = "MMMM' 'yy";


   /**
    * lookup table for MWDateTimeFormatters based on pattern
    */
   static private final HashMap<String, MWDateFormatter> FORMAT_LOOKUP =
      new HashMap<String, MWDateFormatter>();


   /**
    * a MWDateTimeFormatter for the default pattern
    */
   static private final MWDateFormatter DEFAULT_FORMATTER =
      MWDateFormatter.getInstance(MWDateFormatter.DEFAULT_PATTERN);


   /**
    * get a MWDateTimeFormatter for a given pattern
    * @param pattern is format pattern
    * @return a MWDateTimeFormatter for Dates
    */
   static public MWDateFormatter getInstance(String pattern)
   {
      // sanity check
      if (pattern == null)
      {
         throw new IllegalArgumentException("pattern string must not be null");
      }

      // fetch MWDateTimeFormatter based on pattern
      MWDateFormatter formatter = 
         MWDateFormatter.FORMAT_LOOKUP.get(pattern);

      // if formatter not found, create one
      if (formatter == null)
      {
         formatter = new MWDateFormatter(pattern);
         MWDateFormatter.FORMAT_LOOKUP.put(pattern, formatter);
      }

      return formatter;
   }  // getInstance()


   /**
    * get the default MWDateTimeFormatter
    * @return the default MWDateTimeFormatter
    */
   static public MWDateFormatter getDefault()
   {
      return DEFAULT_FORMATTER;
   }  // getDefault()


   /**
    * private constructor
    * @param pattern is format pattern to use
    */
   private MWDateFormatter(String pattern)
   {
      super(pattern);

      // cache param
      formatPattern = pattern;
      
   }  // MWDateTimeFormatter()


   /**
    * main function for testing
    * @param args String[]
    */
   static public void main(String[] args)
   {
      Calendar cal = Calendar.getInstance();
      cal.set(2005, 3, 28);
      Date date = cal.getTime();

      MWDateFormatter defFmt = 
         MWDateFormatter.getInstance(MWDateFormatter.DEFAULT_PATTERN);
      System.out.println(" using default format pattern = " + defFmt.format(date));


      MWDateFormatter defFmt2 = MWDateFormatter.getDefault();
      System.out.println("using default format pattern2 = " + defFmt2.format(date));
      
      System.out.println("          are defaults equal? = " + (defFmt == defFmt2));

      MWDateFormatter custFmt = 
         MWDateFormatter.getInstance("MMMM dd, YYYY");
      System.out.println("  using custom format pattern = " + custFmt.format(date));
      
   }

}  // class MWDateTimeFormatter
