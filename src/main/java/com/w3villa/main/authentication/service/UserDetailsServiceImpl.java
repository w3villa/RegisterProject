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
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		System.out.println(username);
		Users users = usersDAO.findByUserName(username, false);

		if (users == null) {
			throw new UsernameNotFoundException("User with user name :"
					+ username + " not found");
		}
		System.out.println(users.getUserName());
		return assembler.buildUserFromUserEntity(users);
	}

}
