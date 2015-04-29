package com.brs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BRSHomeController {

	@RequestMapping(value = "/loginsa", method = RequestMethod.GET)
	public String getHomePage(HttpServletRequest httpRequest){
		System.out.println("here");
		return "account/login";
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String getList(HttpServletRequest httpRequest){
		System.out.println("here=home");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		auth.getName();
		System.out.println(auth.getAuthorities());
		return "portal";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		return "account/login";
	}

	@RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
	public String loginerror(Model model) {
		model.addAttribute("error", "true");
		return "account/denied";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Model model) {
		return "account/logout";
	}

}
