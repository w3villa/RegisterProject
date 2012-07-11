package com.w3villa.main;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.w3villa.main.authentication.userService.UsersService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml","file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class JunitTestCommon  {

	@Autowired
	private UsersService usersService;
	
	@Test
	public void testGetUsersList(){
		System.out.println("********************** Start testGetUsersList *******************************");
		System.out.println(usersService.getUsersList());
		System.out.println("********************** End testGetUsersList *******************************");
	}
	
	@Test
	public void testFindByEmailId(){
		String emailId = "pgupta@xebia.com";
		System.out.println("*********************** Start testFindByEmailId ******************************");
		System.out.println(usersService.findByEmailId(emailId));
		System.out.println("*********************** End testFindByEmailId ******************************");
	}
}
