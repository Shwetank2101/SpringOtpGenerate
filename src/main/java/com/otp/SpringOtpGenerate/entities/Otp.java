package com.otp.SpringOtpGenerate.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Component;

@Component
@Table(name = "Otp2Records")
@Entity
public class Otp {
	
	@Id
	private String email;
	private String otpNumber;
	private long time;
	private int attempts;
	
	

	public Otp(String email, String otpNumber, long time, int attempts) {
		super();
		this.email = email;
		this.otpNumber = otpNumber;
		this.time = time;
		this.attempts = attempts;
	}

	public int getAttempts() {
		return attempts;
	}

	public void setAttempts(int attempts) {
		this.attempts = attempts;
	}

	public Otp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOtpNumber() {
		return otpNumber;
	}

	public void setOtpNumber(String otpNumber) {
		this.otpNumber = otpNumber;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

}
