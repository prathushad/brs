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

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(HttpServletRequest httpRequest) {
		return "account/signup";
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(HttpServletRequest httpRequest) {
		User user = new User();
		String firstName = httpRequest.getParameter("firstname");
		String lastName = httpRequest.getParameter("lastname");
		String emailAddress = httpRequest.getParameter("email_address");
		String username = httpRequest.getParameter("email_address");
		String password = httpRequest.getParameter("password");
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(emailAddress);
		user.setUserName(username);
		user.setPassword(password);
		user.setRole("USER");
		User user2 = iUserRepository.save(user);
		return "redirect:portal";

	}

}
