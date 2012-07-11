package com.w3villa.main.authentication.userService;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
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

@Service
@Transactional(rollbackFor = Exception.class)
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersDAO usersDAO;

	@Autowired
	private StylePreferenceDAO stylePreferenceDAO;

	@Autowired
	private UserStylePreferncesMpgDAO userStylePreferncesMpgDAO;

	public List<Users> getUsersList() {
		return usersDAO.getUsersList();
	}

	public Users findByEmailId(String emailId, boolean disableLazy) {
		return usersDAO.findByEmailId(emailId, disableLazy);
	}

	public void saveUser(UserEntityBean userEntityBean,
			String[] stylePreferences) {
		Users users = new Users();

		userEntityBean.setIsActive("Y");
		String password = userEntityBean.getPassword();
		PasswordEncoder encoder = new StandardPasswordEncoder();
		String encodedPass = encoder.encode(password);
		userEntityBean.setPassword(encodedPass);
		getUserDomainFromVo(userEntityBean, users);
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
		for (String stylePreferenceId : stylePreferences) {
			preference = stylePreferenceDAO.getById(Integer
					.parseInt(stylePreferenceId));
			userStylePreferncesMpg = new UserStylePreferncesMpg();
			userStylePreferncesMpg.setUsers(users);
			userStylePreferncesMpg.setStylePreference(preference);
			userStylePreferncesMpgDAO
					.saveUserStylePreferncesMpg(userStylePreferncesMpg);
		}

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
