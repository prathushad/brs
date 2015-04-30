package com.brs.service.book;

import java.util.List;

import com.brs.model.Book;

public interface BookManagementService {

	List<Book> findBooksByAuthor(String author);
}
