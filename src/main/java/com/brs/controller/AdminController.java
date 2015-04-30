package com.brs.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.brs.model.Book;
import com.brs.model.User;
import com.brs.service.book.BookManagementService;
import com.brs.service.user.UserManagementService;

@Controller
public class AdminController {

	@Autowired
	private BookManagementService bookManagementService;

	@Autowired
	private UserManagementService userManagementService;

	@RequestMapping(value = "/manageholds", method = RequestMethod.GET)
	public String manageholds(Model model, HttpServletRequest httpRequest) {
		return "admin/manageholds";
	}

	@RequestMapping(value = "/retrieveholds", method = RequestMethod.POST)
	public String updateholds(Model model, HttpServletRequest httpRequest) {
		User user = null;
		List<Book> books = new ArrayList<Book>();
		if(null!=httpRequest.getParameter("username")&&httpRequest.getParameter("username").trim().length()>0){
			user = userManagementService.findUserByUsername(httpRequest.getParameter("username").trim());
			books = bookManagementService.findBooksOnHoldForUser(user.getId());
		} else if (null != httpRequest.getParameter("allusers")
				&& httpRequest.getParameter("allusers").trim().length() > 0
				&& "all".equalsIgnoreCase(httpRequest.getParameter("allusers"))) {
			books = bookManagementService.findBooksOnHoldForAllUsers();
		}
		model.addAttribute("results", books);
		return "admin/holdresults";
	}


	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public String placehold(Model model, HttpServletRequest httpRequest, @RequestParam(value="book") Integer bookId) {

		Book book = bookManagementService.findBookById(bookId);
		
		book.setIsCheckedOut("Y");
		book.setIsAvailable("N");
		book.setCheckedOutTo(book.getOnHoldBy());
		Calendar today = Calendar.getInstance();
		today.add(Calendar.DATE, 10);
		book.setReturnDate(today.getTime());

		book.setIsOnHold("N");
		book.setOnHoldBy(null);
		book.setPickupDueDate(null);

		
		bookManagementService.updateBook(book);
		model.addAttribute("title", book.getTitle());
		return "admin/checkoutsuccess";
	}
	
	@RequestMapping(value = "/releaseholdbyadmin", method = RequestMethod.GET)
	public String releasehold(Model model, HttpServletRequest httpRequest, @RequestParam(value="book") Integer bookId) {
		Book book = bookManagementService.findBookById(bookId);
		book.setIsOnHold("N");
		book.setOnHoldBy(null);
		book.setIsCheckedOut("N");
		book.setIsAvailable("Y");
		book.setPickupDueDate(null);
		bookManagementService.updateBook(book);
		model.addAttribute("title",book.getTitle());
		return "admin/holdreleasesuccess";
	}	
}
