package com.w3villa.main;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.w3villa.main.authentication.domain.ImageAlbumChoiceMapping;
import com.w3villa.main.authentication.domain.ImageMapping;
import com.w3villa.main.authentication.domain.UserAlbumChoiceMpg;
import com.w3villa.main.authentication.domain.Users;
import com.w3villa.main.authentication.userService.ImageAlbumChoiceMappingService;
import com.w3villa.main.authentication.userService.UsersService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/security/security-app-context.xml" })
public class JunitTestCommon {

	@Autowired
	private UsersService usersService;

	@Autowired
	private ImageAlbumChoiceMappingService imageAlbumChoiceMappingService;

	private Users users = null;

	@Before
	public void beforeCall() {
		users = usersService.findByUserName("admin", true);
		if (users != null)
			System.out.println("******  ADMIN user found.");
	}

	@Test
	public void testGetUsersList() {
		System.out
				.println("********************** Start testGetUsersList *******************************");
		System.out.println(usersService.getUsersList());
		System.out
				.println("********************** End testGetUsersList *******************************");
	}

	@Test
	public void testFindByEmailId() {
		String emailId = "pgupta@xebia.com";
		System.out
				.println("*********************** Start testFindByEmailId ******************************");
		// System.out.println(usersService.findByEmailId(emailId));
		System.out
				.println("*********************** End testFindByEmailId ******************************");
	}

	@Test
	public void testImageAlbumChoiceMapping() {
		if (users != null) {
			Set<UserAlbumChoiceMpg> userAlbumChoiceMpgs = users
					.getUserAlbumChoiceMpgs();
			if (userAlbumChoiceMpgs != null && userAlbumChoiceMpgs.size() != 0) {
				System.out.println("******  userAlbumChoiceMpgs found.");
				Iterator<UserAlbumChoiceMpg> itr = userAlbumChoiceMpgs
						.iterator();
				if (itr.hasNext()) {
					UserAlbumChoiceMpg userAlbumChoiceMpg = itr.next();
					Set<ImageMapping> imageMappings = users.getImageMappings();
					if (imageMappings != null && imageMappings.size() != 0) {
						System.out.println("******  imageMappings found.");
						imageAlbumChoiceMappingService
								.deleteRecordsByUserAlbumChoiceMpgId(userAlbumChoiceMpg
										.getUserAlbumChoiceMpgId());
						System.out
								.println("******  Old imageAlbumChoiceMapping records deleted for userAlbumChoiceMpgId : "
										+ userAlbumChoiceMpg
												.getUserAlbumChoiceMpgId()
										+ " .");
						Iterator<ImageMapping> itr1 = imageMappings.iterator();
						int seqNo = 0;
						ImageAlbumChoiceMapping imageAlbumChoiceMapping = null;
						while (itr1.hasNext()) {
							ImageMapping imageMapping = itr1.next();
							seqNo = imageAlbumChoiceMappingService
									.getNewSequenceNo(userAlbumChoiceMpg
											.getUserAlbumChoiceMpgId());
							System.out.println("******  new Sequence No. : ."
									+ seqNo);
							imageAlbumChoiceMapping = new ImageAlbumChoiceMapping(
									imageMapping, userAlbumChoiceMpg, seqNo);
							Serializable id = imageAlbumChoiceMappingService
									.saveImageAlbumChoiceMapping(imageAlbumChoiceMapping);
							System.out
									.println("******  new imageAlbumChoiceMapping data created with id : "
											+ id.toString());
						}

					}

				}

			}
		}

	}
}
