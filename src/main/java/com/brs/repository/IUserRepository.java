package com.brs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.brs.model.User;

public interface IUserRepository extends JpaRepository<User, Integer> {

	User findByUserName(String userName);

	User findByEmail(String email);

}
