package com.brs.service.user;

import com.brs.model.User;


public interface UserManagementService {

	User findUserByUsername(String userName);
	User updateUser(User user);
}
