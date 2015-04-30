package com.brs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.brs.model.Book;

public interface IBookRepository extends JpaRepository<Book, Integer> {

	List<Book> findBooksByAuthor(String author);
	
	@Query("select bks from Book as bks where bks.onHoldBy=:userId")
	List<Book> findBooksOnHoldForUser(@Param("userId")Integer userId);

	@Query("select bks from Book as bks where bks.checkedOutTo=:userId")
	List<Book> findBooksCheckedOutUser(@Param("userId")Integer userId);

	@Query("select bks from Book as bks where bks.onHoldBy is not null")
	List<Book> findBooksOnHoldForAllUsers();
}
