package com.w3villa.main.authentication.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.w3villa.main.authentication.dao.UsersDAO;
import com.w3villa.main.authentication.domain.Users;

@Service("userDetailsService") 
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private Assembler assembler;
	
	@Autowired
	private UsersDAO usersDAO;
	
	@Transactional
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		Users users = usersDAO.findByEmailId(username, false);
		if(users == null)
			throw new UsernameNotFoundException("UserName with emailId :"+username+" not found");
		return assembler.buildUserFromUserEntity(users);
	}

}
