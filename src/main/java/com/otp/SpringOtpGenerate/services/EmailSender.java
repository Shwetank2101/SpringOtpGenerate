package com.otp.SpringOtpGenerate.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {
	
	
	Logger logger=LoggerFactory.getLogger(OtpServiceImp.class);
	@Autowired
	private JavaMailSender mailSender;
	public boolean sendSimpleEmail(String toEmail,String body,String subject) {
		
		logger.info("Mail is sending");
		
		try {
			 SimpleMailMessage message = new SimpleMailMessage();
			 
			 message.setFrom("indiannbros@gmail.com");
			 message.setTo(toEmail);
			 message.setText(body);
			 message.setSubject(subject);
			 
			 mailSender.send(message);
			 System.out.println("Mail Send....");
			 return true;
		}
		catch(Exception e) {
			return false;
		}
		
	}
}
