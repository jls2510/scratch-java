package com.ping23.scratch.email;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

   public static void main(String [] args) {    
      // Recipient's email ID needs to be mentioned.
      String to = "jim@ping23.com";

      // Sender's email ID needs to be mentioned
      String from = "jls2510@gmail.com";

      // Assuming you are sending email from localhost
      //String host = "localhost";
      //String host = "secure.emailsrvr.com";

      // Get system properties
      Properties properties = new Properties();
      properties.put("mail.smtp.auth", true);
      properties.put("mail.smtp.starttls.enable", "true");
      properties.put("mail.smtp.host", "smtp.mailtrap.io");
      properties.put("mail.smtp.port", "2525");
      properties.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");
      
      // Setup mail server
      //properties.setProperty("mail.smtp.host", host);

      // Get the default Session object.
      //Session session = Session.getDefaultInstance(properties);
      final String username = "a30be067550090";
      final String password = "c6e8b68637ff5a";
      Session session = Session.getInstance(properties, new Authenticator() {
          @Override
          protected PasswordAuthentication getPasswordAuthentication() {
              return new PasswordAuthentication(username, password);
          }
      });

      try {
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

         // Set Subject: header field
         message.setSubject("This is the Subject Line!");

         // Now set the actual message
         message.setText("This is actual message");

         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
      } catch (MessagingException mex) {
         mex.printStackTrace();
      }
   }
}
