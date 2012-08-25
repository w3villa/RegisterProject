package com.w3villa.main.authentication.userService;

import java.util.List;

import com.w3villa.main.authentication.bean.UserEntityBean;
import com.w3villa.main.authentication.domain.Users;

public interface UsersService {
	List<Users> getUsersList();

	Users findByEmailId(String emailId, boolean disableLazy);

	void saveUser(UserEntityBean userEntityBean, String[] stylePreferences)
			throws Exception;

	Users findByUserName(String userName, boolean disableLazy);

	List<UserEntityBean> getAllUserEntityBean() throws Exception;

	void update(UserEntityBean userEntityBean) throws Exception;

	void delete(int id);
}
