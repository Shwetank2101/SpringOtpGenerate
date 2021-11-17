package com.otp.SpringOtpGenerate.controller;

import java.util.concurrent.ThreadLocalRandom;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.otp.SpringOtpGenerate.entities.Otp;
import com.otp.SpringOtpGenerate.services.OtpService;
import com.otp.SpringOtpGenerate.services.OtpServiceImp;
import com.otp.SpringOtpGenerate.services.TriggerMail;

@RestController
public class MyController {
	
	Logger logger=LoggerFactory.getLogger(OtpServiceImp.class);
	
	@Autowired
	Otp otpObj;
	
	@Autowired
	OtpService otpservice;
	
	@Autowired
	TriggerMail trigmail;
	
//	@RequestMapping(value="/sendotp", method = RequestMethod.GET)
//	public ModelAndView mail() {
//		
//		logger.info("Email saved! OTP sent");
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("email");
//		
//		return modelAndView;
//	}
//	
//	
	
	@PostMapping("/submitotp")
	public String otpRequest(@RequestParam("email") String email) throws MessagingException {
		
		otpObj.setEmail(email);
		
		logger.info("OTP is submitted from user");
		Otp newOtp = otpservice.getRealOtp(email);
		int attempt = 0;
		try {
			attempt = newOtp.getAttempts();
		}
		catch(Exception e){
			attempt=0;
		}
		
		attempt+=1;
		int min = 100000;
		int max = 999999;
		int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
		trigmail.triggerMail(email, "OTP is "+String.valueOf(randomNum), "Request for OTP");
		long time = System.nanoTime();
		otpservice.addOtp(email, String.valueOf(randomNum), time, attempt);
		attempt = otpservice.getAttempt(email);
		
		if(attempt<=3) {
			
			return "OTP sent :: "+"Total attempt left is "+(3-attempt);
				
		}
		else {
			
			attempt=0;
			Otp newsOtp = otpservice.getRealOtp(email);
			newsOtp.setAttempts(attempt);
			otpObj.setAttempts(attempt);
			otpservice.addOtp(email, String.valueOf(randomNum), time, attempt);
			return "attemptfailed "+" You have exceeded attempt limits";
		}
		
		
	}
	
//	@PostMapping("/submitotp")
//	public ModelAndView otpRequest(@RequestParam("email") String email) throws MessagingException {
//		
//		otpObj.setEmail(email);
//		
//		logger.info("OTP is submitted from user");
//		Otp newOtp = otpservice.getRealOtp(email);
//		int attempt = 0;
//		try {
//			attempt = newOtp.getAttempts();
//		}
//		catch(Exception e){
//			attempt=0;
//		}
//		
//		attempt+=1;
//		int min = 100000;
//		int max = 999999;
//		int randomNum = ThreadLocalRandom.current().nextInt(min, max + 1);
//		trigmail.triggerMail(email, "OTP is "+String.valueOf(randomNum), "Request for OTP");
//		long time = System.nanoTime();
//		otpservice.addOtp(email, String.valueOf(randomNum), time, attempt);
//		attempt = otpservice.getAttempt(email);
//		
//		ModelAndView modelAndView = new ModelAndView();
//		if(attempt<=3) {
//			
//			modelAndView.setViewName("result");
//			modelAndView.addObject("email",email);
//			modelAndView.addObject("attempts",attempt);
//			modelAndView.addObject("attempt","Total attempts left"+"  "+String.valueOf(3-attempt));
//			
//		}
//		else {
//			
//			attempt=0;
//			Otp newsOtp = otpservice.getRealOtp(email);
//			newsOtp.setAttempts(attempt);
//			otpObj.setAttempts(attempt);
//			modelAndView.setViewName("attemptfailed");
//			modelAndView.addObject("error","You have exceeded attempt limits");
//		}
//		
//		return modelAndView;
//		
//	}

	
	@PostMapping("/validateotp")
	public String otpCheck(@RequestParam("otp") String otp ) {
		
		String email = otpObj.getEmail();
		logger.info("Otp number is validating");
		
		String currentotp = otpservice.getOtp(email);
		
		long time = otpservice.getTime(email);
		long currentTime = System.nanoTime();
		long total = (currentTime-time)/ 1000000000;
		
		if(total>60) {
			return "Otp have been expired!!!";
		}
		else {
			if(currentotp.equals(otp)) {
				return "Otp matched Successfully";
			}
			else {
				return "Otp doesn't matched";
			}
		}
		
	}
	
	
//	@PostMapping("/validateotp")
//	public ModelAndView otpCheck(@RequestParam("otp") String otp ) {
//		
//		String email = otpObj.getEmail();
//		logger.info("Otp number is validating");
//		ModelAndView modelAndView = new ModelAndView();
//		modelAndView.setViewName("validate");
//		
//		String currentotp = otpservice.getOtp(email);
//		
//		long time = otpservice.getTime(email);
//		long currentTime = System.nanoTime();
//		long total = (currentTime-time)/ 1000000000;
//		
//		if(total>60) {
//			modelAndView.addObject("otp","Otp have been expired!!!");
//			modelAndView.addObject("color",false);
//		}
//		else {
//			if(currentotp.equals(otp)) {
//				modelAndView.addObject("otp","Otp matched Successfully");
//				modelAndView.addObject("color",true);
//			}
//			else {
//				modelAndView.addObject("otp","Otp doesn't matched");
//				modelAndView.addObject("color",false);
//			}
//		}
//		
//		return modelAndView;
//	}
	
	
	
}
