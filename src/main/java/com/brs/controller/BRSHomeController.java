package com.brs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BRSHomeController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getHomePage(HttpServletRequest httpRequest){
		System.out.println("here");
		return "account/login";
	}

}
