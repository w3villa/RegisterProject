package com.w3villa.main.authentication.service;

import java.util.List;

import com.w3villa.main.authentication.bean.UserEntityBean;
import com.w3villa.main.authentication.domain.Users;

public interface UsersService {
	List<Users> getUsersList();
	Users findByEmailId(String emailId);
	void saveUser(UserEntityBean userEntityBean);
}
