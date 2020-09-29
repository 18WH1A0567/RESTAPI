package com.dao;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.dto.Driver;
import com.dto.Record;

import java.util.Properties;


public class emailSending {
	
	public int generateOTP(Record record){
		 int randomPin = (int)(Math.random()*9000)+1000;
		 String otp  = String.valueOf(randomPin);
		 
		 
		 final String username = "movingmadeeasy2001@gmail.com";
	     final String password = "movingmadeeasy";
	        
	        String sendMessage = "Dear " + record.getCustName() +",\nWe have received your request for transport." +
	        "\nHere is your OTP for this transaction\n" + 
	        otp +".";

	        Properties prop = new Properties();
	        prop.put("mail.smtp.host", "smtp.gmail.com");
	        prop.put("mail.smtp.port", "587");
	        prop.put("mail.smtp.auth", "true");
	        prop.put("mail.smtp.starttls.enable", "true"); //TLS

	        Session session = Session.getInstance(prop,
	                new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(username, password);
	                    }
	                });

	        try {

	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("movingmadeeasy2001@gmail.com"));
	            message.setRecipients(
	                    Message.RecipientType.TO,
	                    InternetAddress.parse(record.getEmail())
	            );
	            message.setSubject("OTP mail ");
	            message.setText(sendMessage);

	            Transport.send(message);
	            
	            //record.setOtp(randomPin);
	            
	            
	            System.out.println("Done");

	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	        
	        return Integer.parseInt(otp);
	    }
		 
	
	public void sendEmail(Record record, Driver driver){
		
			System.out.println("In mail sending");

	        final String username = "movingmadeeasy2001@gmail.com";
	        final String password = "movingmadeeasy";
	        
	        String sendMessage = "Dear " + record.getCustName() +",\nWe have received your request for transport." +
	    	        "\nHere are the details of the driver allocated to you\n" +
	    	        "Transaction Id:" + record.getTransactionId() + "\n" +
	    	        "Driver Name: " + driver.getDriverName() + "\n" + 
	    	        "Driver Phone: " + driver.getDriverPhone() + "\n" +
	    	        "Vehicle Number: " + driver.getVehicleId() + "\n" +
	    	        "Vehilce Type: " + driver.getVehicleType() + "\n" +
	    	        "Total Amount: Rs." + (int)record.getBill() + " only\n" +
	    	        "\nAdvance amount must be paid prior to the service to the staff while packing." + "\n" +
	    	        "\nYou can get in touch with the driver" + "\n" + 
	     	        "The balance amount must be paid post service." + "\n" + 
	    	        "Thanks for choosing us.\nWe wish to serve you again." + 
	     	        "\nFor any queries: Contact " + record.getManager().getManagerName() +".\n" +
	    	        "Phone: " + record.getManager().getManagerPhone() +".\n";

	        Properties prop = new Properties();
	        prop.put("mail.smtp.host", "smtp.gmail.com");
	        prop.put("mail.smtp.port", "587");
	        prop.put("mail.smtp.auth", "true");
	        prop.put("mail.smtp.starttls.enable", "true"); //TLS

	        Session session = Session.getInstance(prop,
	                new javax.mail.Authenticator() {
	                    protected PasswordAuthentication getPasswordAuthentication() {
	                        return new PasswordAuthentication(username, password);
	                    }
	                });

	        try {

	            Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress("movingmadeeasy2001@gmail.com"));
	            message.setRecipients(
	                    Message.RecipientType.TO,
	                    InternetAddress.parse(record.getEmail())
	            );
	            message.setSubject("Confirmation mail ");
	            message.setText(sendMessage);

	            Transport.send(message);

	            System.out.println("Done");

	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }
	
	public void rejectionMail(Record record, String msg){
		System.out.println("In mail sending");

        final String username = "movingmadeeasy2001@gmail.com";
        final String password = "movingmadeeasy";
        
        String sendMessage = "Dear " + record.getCustName() +" ,\n" + 
        					"We have received your request for availing our service.\n" +
        					"But we are sorry to inform you that we may not be able to provide you the service this time.\n" + 
        					"We hope you understand. Thank you for your cooperation and trust! " +
        					"\nMessage from the team: " + msg + 
        					"\nHave a great day!";
        					
        					
        
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("movingmadeeasy2001@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(record.getEmail())
            );
            message.setSubject("Sorry for the inconvenience! ");
            message.setText(sendMessage);

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        
	}
}

