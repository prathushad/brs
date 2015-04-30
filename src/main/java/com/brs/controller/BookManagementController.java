package com.brs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
