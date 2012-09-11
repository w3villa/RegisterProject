package com.w3villa.main.authentication.userService;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UsersServiceTest extends AbstractTest {

	@Autowired
	private UsersService usersService;

	String oldCity = "";

	@Test
	public void getAndUpdate() {
		// Users users = usersService.findByUserName("ADMIN", true);
		// assertNotNull(users);
		// Set<UserAlbumChoiceMpg> userAlbumChoiceMpgs = users
		// .getUserAlbumChoiceMpgs();
		// oldCity = users.getCity();
		// System.out.println("city before : " + users.getCity());
		// users.setCity(users.getCity() + "_1");
		// usersService.updateUsers(users);
		// System.out.println("city after : " + users.getCity());
		// users.setCity(oldCity);
		// usersService.updateUsers(users);
		assert (true);
	}

}
