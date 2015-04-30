package com.brs.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.brs.model.Book;
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
}
