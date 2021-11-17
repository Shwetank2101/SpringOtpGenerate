package com.otp.SpringOtpGenerate.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.otp.SpringOtpGenerate.entities.Otp;

public interface Repository extends JpaRepository<Otp, String>{
	
}

