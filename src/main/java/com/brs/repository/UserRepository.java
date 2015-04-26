package com.brs.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.brs.model.AccountUser;

@Repository
@Transactional(readOnly = true)
public class UserRepository {
	
	@Autowired
	private IUserRepository userRepository;
	    
	public void flush(){
		userRepository.flush();
	}	
	
	public AccountUser findById(Integer id) {
		if( userRepository.exists(id) ){
			return userRepository.findOne(id);
		}
		return null;
    }

	public List<AccountUser> findAll() {
       return userRepository.findAll();
    }

    @Transactional
    public AccountUser save(AccountUser user) {
            return userRepository.save(user);
    }
    
    public AccountUser findByUserName(String userName)
    {
    	return userRepository.findByUserName(userName);
    }
    
    public AccountUser findByEmail(String email)
    {
    	return userRepository.findByEmail(email);
    }
    
	public void delete(AccountUser user) {
		userRepository.delete(user);
	}
}