package com.brs.security;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.brs.model.AccountUser;
import com.brs.repository.IUserRepository;

@SuppressWarnings("rawtypes")
@Service
public class CustomUserDetailsService implements UserDetailsService,
		AuthenticationUserDetailsService {
	private static final Logger LOGGER = Logger
			.getLogger(CustomUserDetailsService.class);

	@Autowired
	private IUserRepository userRepository;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		LOGGER.info("Getting user details from loadUserByUsername :: ");

		AccountUser domainUser = null;
		try {

			domainUser = userRepository.findByUserName(username.toLowerCase());
			if (domainUser == null) {
				LOGGER.error("Username Not Found :: " + username);
			}

		} catch (Exception ex) {
			LOGGER.error("Exception : " + ex.getMessage(), ex);
			throw new AuthenticationServiceException(ex.getMessage(), ex);
		}

		return domainUser;
	}

	@Override
	public UserDetails loadUserDetails(Authentication token)
			throws UsernameNotFoundException {
		LOGGER.info("Getting user details from loadUserDetails :: ");

		String username = token.getName();
		UserDetails domainUser = null;
		domainUser = loadUserByUsername(username);

		LOGGER.info("Loaded user details object for (" + username + ") ::"
				+ domainUser);
		return domainUser;
	}
}
