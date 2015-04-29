package com.brs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.brs.model.User;
import com.brs.repository.IUserRepository;

@Controller
public class RegistrationController {
	
	@Autowired
	private IUserRepository iUserRepository;
	
	@RequestMapping(value = "/submitregistration", method = RequestMethod.GET)
	public String register(HttpServletRequest httpRequest){
		User user = new User();
		user.setFirstName("test 3");
		user.setLastName("last 3");
		user.setEmail("test@brs.com");
		user.setUserName("test@brs.com");
		user.setPassword("password");
		user.setRole("USER");
		User user2 = iUserRepository.save(user);
		System.out.println(user2.getId());
		return "portal";
		
	}

	
}
