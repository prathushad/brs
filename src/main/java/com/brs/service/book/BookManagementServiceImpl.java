package com.brs.service.book;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brs.model.Book;
import com.brs.repository.IBookRepository;

@Service
public class BookManagementServiceImpl implements BookManagementService {

	@Autowired
	private IBookRepository iBookRepository;

	@Override
	public List<Book> findBooksByAuthor(String author) {

		List<Book> booksByAuthor = new ArrayList<Book>();
		booksByAuthor = iBookRepository.findBooksByAuthor(author);
		return booksByAuthor;
	}

}
