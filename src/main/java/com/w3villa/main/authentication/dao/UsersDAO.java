package com.w3villa.main.authentication.dao;

import java.util.List;

import com.w3villa.main.authentication.domain.Users;

public interface UsersDAO {

	List<Users> getUsersList();

	Users findByEmailId(String emailId, boolean disableLazy);

	void saveUser(Users users);

	Users findByUserName(String userName, boolean disableLazy);

	void update(Users users);

	void delete(int id);

	Users findById(int id, boolean disableLazy);

}
