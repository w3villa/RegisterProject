package com.w3villa.main.authentication.userServiceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.w3villa.main.authentication.bean.UserEntityBean;
import com.w3villa.main.authentication.constant.RegisterConstant;
import com.w3villa.main.authentication.dao.StylePreferenceDAO;
import com.w3villa.main.authentication.dao.UserStylePreferncesMpgDAO;
import com.w3villa.main.authentication.dao.UsersDAO;
import com.w3villa.main.authentication.domain.StylePreference;
import com.w3villa.main.authentication.domain.UserRoles;
import com.w3villa.main.authentication.domain.UserStylePreferncesMpg;
import com.w3villa.main.authentication.domain.Users;
import com.w3villa.main.authentication.userService.UsersService;

@Service
@Transactional(rollbackFor = Exception.class)
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersDAO usersDAO;

	@Autowired
	private StylePreferenceDAO stylePreferenceDAO;

	@Autowired
	private UserStylePreferncesMpgDAO userStylePreferncesMpgDAO;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public List<Users> getUsersList() {
		return usersDAO.getUsersList();
	}

	@Override
	public Users findByEmailId(String emailId, boolean disableLazy) {
		return usersDAO.findByEmailId(emailId, disableLazy);
	}

	@Override
	public Users findByUserName(String userName, boolean disableLazy) {
		return usersDAO.findByUserName(userName, disableLazy);
	}

	@Override
	public void updateUsers(Users users) {
		usersDAO.update(users);
	}

	@Override
	public void saveUser(UserEntityBean userEntityBean,
			String[] stylePreferences) {
		Users users = new Users();

		userEntityBean.setIsActive("Y");
		String password = userEntityBean.getPassword();
		String encodedPass = encoder.encode(password);

		getUserDomainFromVo(userEntityBean, users);
		users.setUserName(userEntityBean.getUserName());
		users.setPassword(encodedPass);
		users.setIsActive("Y");
		users.setCreatedDt(new Date());
		users.setUpdateDt(new Date());

		// For USER_ROELS
		UserRoles userRoles = new UserRoles();
		userRoles.setRoleName(RegisterConstant.ROLE_USER);
		userRoles.setUsers(users);
		users.getUserRoleses().add(userRoles);

		// For STYLE_PREFERENCES
		UserStylePreferncesMpg userStylePreferncesMpg = null;
		/*
		 * List<StylePreference> preferences = stylePreferenceDAO
		 * .getStylePreferenceList();
		 * 
		 * // Set<StylePreference> stylePreferences2 = new //
		 * HashSet<StylePreference>(); for (String stylePreferenceId :
		 * stylePreferences) { for (StylePreference preference : preferences) {
		 * if (stylePreferenceId.equals(preference.getStylePreferenceId() + ""))
		 * { userStylePreferncesMpg = new UserStylePreferncesMpg();
		 * userStylePreferncesMpg.setUsers(users);
		 * userStylePreferncesMpg.setStylePreference(preference);
		 * users.getUserStylePreferncesMpgs().add( userStylePreferncesMpg); } }
		 * }
		 */

		usersDAO.saveUser(users);

		StylePreference preference = null;
		if (stylePreferences != null && stylePreferences.length != 0) {
			for (String stylePreferenceId : stylePreferences) {
				preference = stylePreferenceDAO.getById(Integer
						.parseInt(stylePreferenceId));
				userStylePreferncesMpg = new UserStylePreferncesMpg();
				userStylePreferncesMpg.setUsers(users);
				userStylePreferncesMpg.setStylePreference(preference);
				userStylePreferncesMpgDAO
						.saveUserStylePreferncesMpg(userStylePreferncesMpg);
			}
		}
		getUserVoFromDomain(userEntityBean, users);
	}

	@Override
	public List<UserEntityBean> getAllUserEntityBean() {
		List<UserEntityBean> userEntityBeanList = null;
		UserEntityBean userEntityBean = null;
		List<Users> usersList = getUsersList();
		if (!usersList.isEmpty())
			userEntityBeanList = new ArrayList<UserEntityBean>();
		for (Users users : usersList) {
			userEntityBean = new UserEntityBean();
			userEntityBeanList.add(getUserVoFromDomain(userEntityBean, users));
		}
		return userEntityBeanList;
	}

	@Override
	public void update(UserEntityBean userEntityBean) {
		// Users users = new Users();
		Users users = usersDAO.findById(
				Integer.parseInt(userEntityBean.getId()), false);
		users = getUserDomainFromVo(userEntityBean, users);
		users.setUpdateDt(new Date());
		if (userEntityBean.getPassword() != null
				&& !"".equals(userEntityBean.getPassword()))
				users.setPassword(userEntityBean.getPassword());
		usersDAO.update(users);
	}

	@Override
	public void delete(int id) {
		usersDAO.delete(id);
	}

	private Users getUserDomainFromVo(UserEntityBean userEntityBean, Users users) {
		Date createdDt = users.getCreatedDt();
		Date updateDt = new Date();
		String userName = users.getUserName();
		String password = users.getPassword();
		String isActive = users.getIsActive();
		BeanUtils.copyProperties(userEntityBean, users, new String[] {
				"createdDt", "updateDt","userName","password","isActive"});
		users.setCreatedDt(createdDt);
		users.setUpdateDt(updateDt);
		users.setUserName(userName);
		users.setPassword(password);
		users.setIsActive(isActive);
		return users;
	}

	private UserEntityBean getUserVoFromDomain(UserEntityBean userEntityBean,
			Users users) {
		BeanUtils.copyProperties(users, userEntityBean, new String[] {
				"createdDt", "updateDt" });
		userEntityBean.setId(users.getUserId() + "");
		userEntityBean.setCreatedDt(users.getCreatedDt().toGMTString());
		userEntityBean.setUpdateDt(users.getUpdateDt().toGMTString());
		userEntityBean.setIsActive(users.getIsActive());
		userEntityBean.setLastName(users.getLastName());
		return userEntityBean;
	}

}
