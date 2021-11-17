package com.otp.SpringOtpGenerate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.otp.SpringOtpGenerate.entities.Otp;
import com.otp.SpringOtpGenerate.services.EmailSender;
import com.otp.SpringOtpGenerate.services.OtpService;
import com.otp.SpringOtpGenerate.services.OtpServiceImp;
import com.otp.SpringOtpGenerate.services.TriggerMail;

@SpringBootTest
class SpringOtpGenerateApplicationTests {

	@Autowired
	Otp otpObj;
	
	@Autowired
	OtpService otpservice;
	
	@Autowired
	TriggerMail trigmail;
	
	@Autowired
	EmailSender emailSender;
	
	
	@Test
	void contextLoads() {
	}
	
	
	//
	@Test
	public void checkEmailSend() {
		boolean isTrue = emailSender.sendSimpleEmail("Shwetankdixitofficial@gmail.com","This is body of a mail","This is subject of mail");
		assertTrue(isTrue);
	}
	
	@Test
	public void getRealOtp() {
		Otp otp = otpservice.getRealOtp("Shwetankdixitofficial@gmail.com");
		assertNotNull(otp);
	}
	
	@Test
	public void getOtp() {
		String otp = otpservice.getOtp("Shwetankdixitofficial@gmail.com");
		assertNotNull(otp);
	}
	
	@Test
	public void getTime() {
		long time = otpservice.getTime("Shwetankdixitofficial@gmail.com");
		assertNotNull(time);
	}

	
	
	@Test
	public void getAttempt() {
		
		int attempt = otpservice.getAttempt("Shwetankdixitofficial@gmail.com");
		assertNotNull(attempt); 
		
	}
	
	@Test
	public void addOtp() {
		Otp otp = otpservice.addOtp("Shwetankdixitofficial@gmail.com", "123456",4252,3);
		assertNotNull(otp);
		
	}
	
	@Test
	public void isMailTriggered() {
		boolean isTrue = trigmail.triggerMail("Shwetankdixitofficial@gmail.com", "This is body", "This is subject");
		assertTrue(isTrue);
	}
	
	@Test
	public void checkOtpAttempts() {
		int attempt = 1;
		otpObj.setAttempts(attempt);
		assertEquals(attempt,otpObj.getAttempts());
	}
	
	@Test
	public void checkOtpGetEmail() {
		String email = "Shwetankdixitofficial@gmail.com";
		otpObj.setEmail(email);
		assertEquals(email,otpObj.getEmail());
	}
	
	
	
	@Test
	public void checkOTPGetOtpNumber() {
		String otpNumber = "123456";
		otpObj.setOtpNumber(otpNumber);
		assertEquals(otpNumber,otpObj.getOtpNumber());
		
	}
	
	@Test
	public void checkOTPGetTime() {
		long time = 1234534;
		otpObj.setTime(time);
		assertEquals(time,otpObj.getTime());
		
	}
	
	
	
	

}
