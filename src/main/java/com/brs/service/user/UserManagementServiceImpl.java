package com.brs.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brs.model.User;
import com.brs.repository.IUserRepository;

@Service
public class UserManagementServiceImpl implements UserManagementService {

	@Autowired
	private IUserRepository iUserRepository;

	@Override
	public User findUserByUsername(String userName) {
		return iUserRepository.findByUserName(userName);
	}

	@Override
	public User updateUser(User user) {
		return iUserRepository.save(user);
	}

}
