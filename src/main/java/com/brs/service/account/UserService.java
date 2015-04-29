package com.brs.service.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.brs.model.User;
import com.brs.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository repository;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = repository.findByUserName(username);
		if (user == null)
			throw new UsernameNotFoundException("User not found");

		return user;
	}
}
