package com.brs.service.book;

import java.util.ArrayList;
import java.util.Date;
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

	@Override
	public List<Book> findAll() {
		List<Book> allBooks = new ArrayList<Book>();
		allBooks = iBookRepository.findAll();
		return allBooks;
	}

	@Override
	public Book updateBook(Book book) {
		Book savedBook = iBookRepository.save(book);
		return savedBook;
	}

	@Override
	public Book findBookById(Integer id) {
		return iBookRepository.findOne(id);
	}

	@Override
	public List<Book> findBooksOnHoldForUser(Integer userId) {
		return iBookRepository.findBooksOnHoldForUser(userId);
	}

	@Override
	public List<Book> findBooksCheckedOutByUser(Integer userId) {
		return iBookRepository.findBooksCheckedOutUser(userId);
	}

	@Override
	public List<Book> findBooksOnHoldForAllUsers() {
		return iBookRepository.findBooksOnHoldForAllUsers();
	}

	@Override
	public List<Book> findBooksCheckedOutByAllUsers() {
		return iBookRepository.findBooksCheckedOutByAllUsers();
	}

	@Override
	public List<Book> findBooksOnHoldUntilDate(Date selectedDate) {
		return iBookRepository.findBooksOnHoldUntilDate(selectedDate);
	}

}
