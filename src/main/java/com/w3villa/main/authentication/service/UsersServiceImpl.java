package com.w3villa.main.authentication.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.stereotype.Service;

import com.w3villa.main.authentication.bean.UserEntityBean;
import com.w3villa.main.authentication.dao.UsersDAO;
import com.w3villa.main.authentication.domain.Users;

@Service
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersDAO usersDAO;

	public List<Users> getUsersList() {
		return usersDAO.getUsersList();
	}

	public Users findByEmailId(String emailId) {
		return usersDAO.findByEmailId(emailId);
	}

	public void saveUser(UserEntityBean userEntityBean){
		Users users = new Users();
		userEntityBean.setIsActive("Y");
		String password = userEntityBean.getPassword();
		PasswordEncoder encoder = new StandardPasswordEncoder();
		String encodedPass = encoder.encode(password);
		userEntityBean.setPassword(encodedPass);
		getUserDomainFromVo(userEntityBean, users);
		System.out.println(users);
		usersDAO.saveUser(users);
		getUserVoFromDomain(userEntityBean, users);
	}

	private Users getUserDomainFromVo(UserEntityBean userEntityBean, Users users) {
		BeanUtils.copyProperties(userEntityBean, users);
		users.setEmailId(userEntityBean.getEmailId());
		users.setFirstName(userEntityBean.getFirstName());
		users.setIsActive(userEntityBean.getIsActive());
		users.setLastName(userEntityBean.getLastName());
		users.setPassword(userEntityBean.getPassword());
		return users;
	}

	private UserEntityBean getUserVoFromDomain(UserEntityBean userEntityBean,
			Users users) {
		BeanUtils.copyProperties(users, userEntityBean);
		userEntityBean.setEmailId(users.getEmailId());
		userEntityBean.setFirstName(users.getFirstName());
		userEntityBean.setIsActive(users.getIsActive());
		userEntityBean.setLastName(users.getLastName());
		return userEntityBean;
	}

}
