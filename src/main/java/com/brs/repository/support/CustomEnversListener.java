package com.brs.repository.support;

import org.hibernate.envers.RevisionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.brs.model.AccountUser;
import com.brs.repository.UserRepository;

public class CustomEnversListener implements RevisionListener {
	@Autowired
	private UserRepository repository;

	public void newRevision(Object revisionEntity) {
		CustomRevisionEntity customRevisionEntity = (CustomRevisionEntity) revisionEntity;
		Integer userId = customRevisionEntity.getUserid();

		// added this condition for CA call as user id is not available while
		// generating partial broker record
		if (userId != null && userId != 0) {
			AccountUser user;
			if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
				UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
				if (userDetails instanceof AccountUser) {
					user = (AccountUser) userDetails;
				} else {
					user = repository.findByUserName(userDetails.getUsername());
				}

				customRevisionEntity.setUserid(user.getId());
				customRevisionEntity.setName(user.getFirstName() + " " + user.getLastName());
			}
		}
	}
}