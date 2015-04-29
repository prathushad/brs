package com.brs.service.account;

import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CryptoPasswordEncoder implements PasswordEncoder {

	@Override
	public String encodePassword(String rawPass, Object salt) {
		return rawPass;
	}

	@Override
	public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
		System.out.println(encPass + " ec");
		System.out.println(rawPass + " rp");
		return true;
	}

}
