package com.brs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brs.model.Book;

public interface IBookRepository extends JpaRepository<Book, Integer> {

	List<Book> findBooksByAuthor(String author);
}
