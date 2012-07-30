package com.w3villa.main.authentication.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.w3villa.main.authentication.domain.UserRoles;
import com.w3villa.main.authentication.domain.Users;

@Service("assembler")
public class Assembler {

	@Transactional(readOnly = true)
	User buildUserFromUserEntity(Users users) {
		String username = users.getUserName();
		String password = users.getPassword();
		boolean isActive = false;
		if ("Y".equals(users.getIsActive()))
			isActive = true;
		boolean enabled = isActive;
		boolean accountNonExpired = isActive;
		boolean credentialsNonExpired = isActive;
		boolean accountNonLocked = isActive;

		Collection<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		for (UserRoles role : users.getUserRoleses()) {
			authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
		}
		User user = new User(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);

		return user;
	}

}
