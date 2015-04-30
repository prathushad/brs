package com.brs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.brs.model.Book;
import com.brs.model.User;
import com.brs.service.book.BookManagementService;

@Controller
public class BookManagementController {

	@Autowired
	private BookManagementService bookManagementService;
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public String search(Model model) {
		List<Book> books = bookManagementService.findBooksByAuthor("Second");
		model.addAttribute("result", books.toString());
		return "books/search";
	}

	@RequestMapping(value = "/dosearch", method = RequestMethod.POST)
	public String dosearch(Model model, HttpServletRequest httpRequest) {
		List<Book> books = bookManagementService.findBooksByAuthor(httpRequest.getParameter("title"));
		model.addAttribute("results", books);
		return "books/searchresults";
	}
	@RequestMapping(value = "/showall", method = RequestMethod.POST)
	public String showall(Model model, HttpServletRequest httpRequest) {
		List<Book> books = bookManagementService.findAll();
		model.addAttribute("results", books);
		return "books/searchresults";
	}
	@RequestMapping(value = "/placehold", method = RequestMethod.GET)
	public String placehold(Model model, HttpServletRequest httpRequest, @RequestParam(value="book") Integer bookId) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();

		Book book = bookManagementService.findBookById(bookId);
		book.setIsOnHold("Y");
		book.setOnHoldBy(user.getId());
		book.setIsCheckedOut("N");
		book.setIsAvailable("N");
		bookManagementService.updateBook(book);
		model.addAttribute("title", book.getTitle());
		return "books/holdsuccess";
	}
}
