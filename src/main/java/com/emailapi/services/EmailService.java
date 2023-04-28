package com.emailapi.services;

import java.io.File;
import java.util.Properties;

import org.springframework.stereotype.Service;

import jakarta.mail.Address;
import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;


@Service
public class EmailService {
	
	public boolean sendEmail(String to,String from,String subject,String message)
	{
		boolean flag=true;
		
		// logic 
		// stmp properties
		// Step 1: Putting Host in the Properties
		
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", true);
		properties.put("mail.smtp.starttls.enable", true);
		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		
		// Step 2: To get the Session Object
		
		final String username="sandeepkumar201198";
		final String password="xnrfnlhltymmgpjo";
		
		
		
		//  We create Session here
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				
				return new PasswordAuthentication(username, password);
			}
			
		});
		
		// Step 3: Compose the Message
		try {
			String to1="171220042@nitdelhi.ac.in";
			String to2="lila.devi99165@gmail.com";
			
			Message m = new MimeMessage(session);
			m.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
			m.setRecipient(Message.RecipientType.CC, new InternetAddress(to1));
			m.setRecipient(Message.RecipientType.BCC, new InternetAddress(to2));
			m.setFrom(new InternetAddress(from));
			m.setSubject(subject);
			//m.setText(message);
			
			// attachment in the mail
			String path="C:\\Users\\ASUS\\Desktop\\eSharam.pdf";
			
			MimeMultipart mimeMultipart = new MimeMultipart();
			// text
			// file
			
			MimeBodyPart textMime= new MimeBodyPart();
			MimeBodyPart fileMime= new MimeBodyPart();
			
			try {
				textMime.setText(message);
				
				File file = new File(path);
				fileMime.attachFile(file);
				
				mimeMultipart.addBodyPart(textMime);
				mimeMultipart.addBodyPart(fileMime);
				
				
				
			}
			catch (Exception e) {
				// TODO: handle exception
			}
			
			m.setContent(mimeMultipart);
			
			Transport.send(m);
			flag=true;
			
			
			
		}catch (Exception e) {
           e.printStackTrace();
		}
		
		return flag;
	}

}
