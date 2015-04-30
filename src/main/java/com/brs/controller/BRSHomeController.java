package com.brs.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.brs.model.User;
import com.brs.service.book.BookManagementService;

@Controller
public class BRSHomeController {
	
	@Autowired
	private BookManagementService bookManagementService;

	@RequestMapping(value = "/portal", method = RequestMethod.GET)
	public String getList(HttpServletRequest httpRequest, Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
		
		int numberOfBooksCheckedOut = bookManagementService.findBooksCheckedOutByUser(user.getId()).size();
		int numberOfBooksOnHold = bookManagementService.findBooksOnHoldForUser(user.getId()).size();
		
		model.addAttribute("displayname", user.getFullName());
		model.addAttribute("checkouts", numberOfBooksCheckedOut);
		model.addAttribute("holds", numberOfBooksOnHold);
		
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

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(Model model) {
		return "home";
	}

	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error(Model model) {
		return "error";
	}

}
