package com.oupp.service;


import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.stereotype.Component;

@Component
public class EmailService {
	
	public boolean sendEmail(String subject,String message,String to)
	{
		boolean flag=false;
		
		String from="bdash3339@gmail.com";
		
		String host="smtp.gmail.com";
		
		//get the system propertity
		Properties properties=System.getProperties();
		System.out.println("Propertites"+properties);
		
		//host set
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");
		
		//step-1 to get the session object
		Session session=Session.getInstance(properties, new Authenticator() {
		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication("bdash3339@gmail.com", "czpe xnou ljkd zcpr");
		}
		});
		session.setDebug(true);
		
		//setp-2 compose the msg
		MimeMessage m=new MimeMessage(session);
		try {
			m.setFrom(from);
			
			m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			m.setSubject(subject);
			
			m.setText(message);
			
			Transport.send(m);
			
			System.out.println("Success");
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return flag;
	}
}
