package com.otp.SpringOtpGenerate.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.otp.SpringOtpGenerate.dao.Repository;
import com.otp.SpringOtpGenerate.entities.Otp;

@Service
public class OtpServiceImp implements OtpService{
	
	Logger logger=LoggerFactory.getLogger(OtpServiceImp.class);
	
	@Autowired
	private Repository repository;
	
	public Otp getRealOtp(String email) {
		Otp newOtp =  repository.findById(email).orElse(null);
		return newOtp;
	}
	
	
	public Otp addOtp(String email, String otpNumber, long time, int attempts) {
		logger.info("OTP added to database");
		Otp otp = new Otp(email, otpNumber, time, attempts);
		return repository.save(otp);
		
	}
	 
	
	public String getOtp(String email) {
		logger.info("OTP is fetched");
		Otp otp = repository.findById((email)).orElse(null);
		return otp.getOtpNumber();
		
	}
	
	public long getTime(String email) {
		logger.info("OTP timming is fetched");
		Otp otp = repository.findById((email)).orElse(null);
		return otp.getTime();
	}
	
	public int getAttempt(String email) {
		logger.info("OTP attempts fetched");
		Otp otp = repository.findById((email)).orElse(null);
		return otp.getAttempts();
	}
}
