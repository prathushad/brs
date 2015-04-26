package com.brs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.history.RevisionRepository;

import com.brs.model.AccountUser;

public interface IUserRepository extends JpaRepository<AccountUser, Integer>,
		RevisionRepository<AccountUser, Integer, Integer> {
	List<AccountUser> findAll();
	AccountUser findByUserName(String userName);
	AccountUser findByEmail(String email);

}
