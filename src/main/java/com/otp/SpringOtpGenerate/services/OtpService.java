package com.otp.SpringOtpGenerate.services;


import com.otp.SpringOtpGenerate.entities.Otp;


public interface OtpService {
	
	public Otp addOtp(String email, String otp, long time, int attempts);
	public String getOtp(String email);
	public long getTime(String email);
	public int getAttempt(String email);
	public Otp getRealOtp(String email);
}
