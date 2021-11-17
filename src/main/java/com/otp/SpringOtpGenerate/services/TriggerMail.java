package com.otp.SpringOtpGenerate.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TriggerMail {
	
	Logger logger=LoggerFactory.getLogger(OtpServiceImp.class);
	@Autowired
	private EmailSender service;
	public boolean triggerMail(String toEmail, String body, String subject)  {
		
		try {
			logger.info("OTP triggered");
			service.sendSimpleEmail(toEmail, body, subject);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
}
