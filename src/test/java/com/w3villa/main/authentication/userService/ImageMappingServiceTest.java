package com.w3villa.main.authentication.userService;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.w3villa.main.authentication.domain.ImageMapping;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/security/security-app-context.xml" })
public class ImageMappingServiceTest {

	@Autowired
	private ImageMappingService imageMappingService;

	@Autowired
	private RepositoryService repositoryService;

	int sequenceNo = 0;
	int userId = 10028;

	// @Test
	// public void testSequenceSaveDelete() {
	// getSequenceNoTest();
	// saveRecordTest();
	// deleteSelectedTest();
	// }

	@Test
	public void getSequenceNoTest(){
		sequenceNo = imageMappingService.getNewSequenceNo(userId);
		assertFalse(sequenceNo == 0);
	}


	public void saveRecordTest() {
		imageMappingService.saveRecord(userId, sequenceNo, "tempPath");
		List<ImageMapping> imageMappings = imageMappingService
				.listRecord(userId);
		assertTrue(imageMappings.size() != 0);
	}

	@Test
	public void deleteAllTest() {
		imageMappingService.deleteRecordsByUserId(userId);
	}

	public void deleteSelectedTest() {
		ImageMapping imageMapping = imageMappingService.recordsAccToSeq(userId,
				sequenceNo);
		assertNotNull(imageMapping);
		imageMappingService.deleteRecordsById(imageMapping.getImageMappingId());
	}

	// @Test
	// public void testUrls() {
	// List<ImageMapping> imageMappings = imageMappingService
	// .listRecord(userId);
	// for (ImageMapping imageMapping : imageMappings) {
	// System.out.println(repositoryService.getUrl("igild/" + userId
	// + "/logo" + "/" + imageMapping.getImagePath()));
	// }
	// }

}
