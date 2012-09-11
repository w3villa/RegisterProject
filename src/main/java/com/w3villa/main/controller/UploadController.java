package com.w3villa.main.controller;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartRequest;

import com.w3villa.constants.ProjectConstant;
import com.w3villa.main.authentication.constant.RegisterConstant;
import com.w3villa.main.authentication.domain.Users;
import com.w3villa.main.authentication.userService.ImageMappingService;
import com.w3villa.main.authentication.userService.RepositoryService;
import com.w3villa.main.util.CommonUtil;
import com.w3villa.upload.UploadFileResponse;
import com.w3villa.upload.UploadInfoBean;
import com.w3villa.voBean.UploadBean;
import com.w3villa.voBean.UploadItem;

@Controller
public class UploadController {

	private static final Logger logger = LoggerFactory
			.getLogger(UploadController.class);

	private static final String ASSET_PATH = "/asset_tests";

	@Autowired
	private RepositoryService repositoryService;

	@Autowired
	private ImageMappingService imageMappingService;

	@Autowired
	private CommonUtil commonUtil;

	@RequestMapping(value = "/FileUpload", method = RequestMethod.POST)
	public @ResponseBody
	UploadFileResponse create(UploadItem uploadItem, BindingResult result,
			HttpServletRequest request, Model model, HttpSession session) {
		UploadFileResponse uploadFileResponse = new UploadFileResponse();
		String fileOrignalName = "";
		if (result.hasErrors()) {
			model.addAttribute("uploadStatus", "fail");
			uploadFileResponse.setStatus(ProjectConstant.UPLOAD_STATUS_FAIL);
			uploadFileResponse.setMessage("Error in file upload.");
			return uploadFileResponse;
		} else {
			try {
				MultipartFile file = uploadItem.getFileData()[0];
				String fileName = null;
				InputStream inputStream = null;
				OutputStream outputStream = null;
				if (file.getSize() > 0) {
					inputStream = file.getInputStream();
					if (file.getSize() > 15728640) {
						System.out.println("File Size:::" + file.getSize()
								+ " bytes.");
						uploadFileResponse
								.setStatus(ProjectConstant.UPLOAD_STATUS_FAIL);
						uploadFileResponse
								.setMessage("File size exceeds limit. Max size allowed less than 15 MB.");
						return uploadFileResponse;
					}
					System.out.println("size::" + file.getSize());
					/*
					 * fileName =
					 * "C:\\Users\\Ishank\\Desktop\\priyank development\\" +
					 * file.getOriginalFilename();
					 */
					if (uploadItem.getUploadPath() == null
							|| ("").equals(uploadItem.getUploadPath())) {
						throw new Exception(
								"Please provide upload Path its empty.");
					}
					ServletContext context = session.getServletContext();
					String realContextPath = context.getRealPath(request
							.getContextPath());

					fileName = realContextPath + uploadItem.getUploadPath()
							+ file.getOriginalFilename();
					outputStream = new FileOutputStream(fileName);
					fileOrignalName = file.getOriginalFilename();
					System.out.println("fileName:" + fileOrignalName);
					int readBytes = 0;
					byte[] buffer = new byte[15728640];
					int totalByteRead = 0;
					UploadInfoBean infoBean = new UploadInfoBean();
					while ((readBytes = inputStream.read(buffer, 0, 5120)) != -1) {
						if (buffer.length % 1024 == 0) {
							totalByteRead = totalByteRead + readBytes;
							infoBean.setBytesRead(totalByteRead);
							infoBean.setTotalSize(file.getSize());
							session.setAttribute("uploadInfoBean", infoBean);
						}
						outputStream.write(buffer, 0, readBytes);
					}
					outputStream.close();
					inputStream.close();
				}
				session.setAttribute("uploadFile", file.getOriginalFilename());
			} catch (Exception e) {
				e.printStackTrace();
				uploadFileResponse
						.setStatus(ProjectConstant.UPLOAD_STATUS_FAIL);
				uploadFileResponse
						.setMessage("Exception thrown in file upload.["
								+ e.getMessage() + "]");
				return uploadFileResponse;
			}
			uploadFileResponse.setFileName(fileOrignalName);
			uploadFileResponse.setStatus(ProjectConstant.UPLOAD_STATUS_PASS);
			uploadFileResponse.setMessage(fileOrignalName
					+ " uploaded Successfully.");
			return uploadFileResponse;
		}
	}

	@RequestMapping(value = "/FileUploadAmazon", method = RequestMethod.POST)
	public @ResponseBody
	List<UploadFileResponse> createAmazon(UploadItem uploadItem,
			BindingResult result, HttpServletRequest request, Model model,
			HttpSession session) {
		List<UploadFileResponse> uploadFileResponses = new ArrayList<UploadFileResponse>();
		UploadFileResponse uploadFileResponse = new UploadFileResponse();
		String fileOrignalName = "";
		String realContextPath = "";
		String folderPath = "";
		if (result.hasErrors()) {
			model.addAttribute("uploadStatus", "fail");
			uploadFileResponse.setStatus(ProjectConstant.UPLOAD_STATUS_FAIL);
			uploadFileResponse.setMessage("Error in file upload.");
			uploadFileResponses.add(uploadFileResponse);
			return uploadFileResponses;
		} else {
			try {
				ServletContext context = session.getServletContext();
				realContextPath = context.getRealPath("") + "/resources/";
				session.setAttribute("realContextPath", realContextPath);
				System.out.println(realContextPath);
				Users users = (Users) session.getAttribute("users");
				Date dt = new Date();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				df.format(dt);
				folderPath = users.getUserId() + "/" + df.format(dt) + "/";
				MultipartFile file = uploadItem.getFileData()[0];
				fileOrignalName = file.getOriginalFilename();
				repositoryService.putAsset(ASSET_PATH,
						folderPath + file.getOriginalFilename(),
						new ByteArrayInputStream(file.getBytes()), session,
						request, uploadFileResponse, file, uploadItem);

				session.setAttribute("uploadFile", file.getOriginalFilename());
			} catch (Exception e) {
				e.printStackTrace();
				uploadFileResponse
						.setStatus(ProjectConstant.UPLOAD_STATUS_FAIL);
				uploadFileResponse
						.setMessage("Exception thrown in file upload.["
								+ e.getMessage() + "]");
				uploadFileResponses.add(uploadFileResponse);
				return uploadFileResponses;
			}
			uploadFileResponse.setUploadPath("resources/" + folderPath);
			uploadFileResponse.setFileName(fileOrignalName);
			uploadFileResponse.setStatus(ProjectConstant.UPLOAD_STATUS_PASS);
			System.out.println(uploadFileResponse);
			uploadFileResponse.setMessage(fileOrignalName
					+ " uploaded Successfully.");
			uploadFileResponses.add(uploadFileResponse);
			return uploadFileResponses;
		}
	}

	@RequestMapping(value = "/FileUploadAmazonTemp", method = RequestMethod.POST)
	public @ResponseBody
	List<UploadFileResponse> createAmazonTemp(UploadItem uploadItem,
			BindingResult result, HttpServletRequest request, Model model,
			HttpSession session) {
		List<UploadFileResponse> uploadFileResponses = new ArrayList<UploadFileResponse>();
		UploadFileResponse uploadFileResponse = new UploadFileResponse();
		String fileOrignalName = "";
		String realContextPath = "";
		String folderPath = "";
		if (result.hasErrors()) {
			model.addAttribute("uploadStatus", "fail");
			uploadFileResponse.setStatus(ProjectConstant.UPLOAD_STATUS_FAIL);
			uploadFileResponse.setMessage("Error in file upload.");
			uploadFileResponses.add(uploadFileResponse);
			return uploadFileResponses;
		} else {
			try {
				ServletContext context = session.getServletContext();
				// realContextPath =
				// context.getRealPath(request.getContextPath())
				realContextPath = context.getRealPath("") + "/resources/";
				session.setAttribute("realContextPath", realContextPath);
				System.out.println(realContextPath);
				// Users users = (Users) session.getAttribute("users");
				Date dt = new Date();
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				df.format(dt);
				folderPath = "10020" + "/" + df.format(dt) + "/";
				MultipartFile file = uploadItem.getFileData()[0];
				fileOrignalName = file.getOriginalFilename();
				/*
				 * repositoryService.putAsset(ASSET_PATH, folderPath +
				 * file.getOriginalFilename(), new
				 * ByteArrayInputStream(file.getBytes()), session, request,
				 * uploadFileResponse, file, uploadItem);
				 */
				Thread.sleep(3000);
				session.setAttribute("uploadFile", file.getOriginalFilename());
			} catch (Exception e) {
				e.printStackTrace();
				uploadFileResponse
						.setStatus(ProjectConstant.UPLOAD_STATUS_FAIL);
				uploadFileResponse
						.setMessage("Exception thrown in file upload.["
								+ e.getMessage() + "]");
				uploadFileResponses.add(uploadFileResponse);
				return uploadFileResponses;
			}
			uploadFileResponse.setUploadPath("resources/" + folderPath);
			uploadFileResponse.setFileName(fileOrignalName);
			uploadFileResponse.setStatus(ProjectConstant.UPLOAD_STATUS_PASS);
			System.out.println(uploadFileResponse);
			uploadFileResponse.setMessage(fileOrignalName
					+ " uploaded Successfully.");
			uploadFileResponses.add(uploadFileResponse);
			return uploadFileResponses;
		}
	}

	@RequestMapping(value = "/getStatus", method = RequestMethod.POST)
	public @ResponseBody
	UploadInfoBean getStatus(HttpSession session) {
		logger.info("get status...");
		System.out.println("get status...");
		UploadInfoBean uploadInfoBean = (UploadInfoBean) session
				.getAttribute("uploadInfoBean");
		/*
		 * if (uploadInfoBean != null) { System.out
		 * .println("**************************************************");
		 * 
		 * if (uploadInfoBean.getBytesRead() != uploadInfoBean.getTotalSize()) {
		 * // per looks like 0% - 100%, remove % before submission
		 * uploadInfoBean .setTotalSize(uploadInfoBean.getTotalSize() / 1024);
		 * uploadInfoBean .setBytesRead(uploadInfoBean.getBytesRead() / 1024);
		 * String per = NumberFormat.getPercentInstance().format( (double)
		 * uploadInfoBean.getBytesRead() / (double)
		 * uploadInfoBean.getTotalSize());
		 * uploadInfoBean.setPercentage(Integer.parseInt(per.substring(0,
		 * per.length() - 1))); } else { uploadInfoBean.setPercentage(100);
		 * session.removeAttribute("uploadInfoBean"); } System.out
		 * .println("percentage : " + uploadInfoBean.getPercentage());
		 * System.out
		 * .println("**************************************************"); }
		 * else { uploadInfoBean = new UploadInfoBean();
		 * uploadInfoBean.setPercentage(500); }
		 */
		if (uploadInfoBean != null)
			System.out
					.println("percentage : " + uploadInfoBean.getPercentage());
		if (uploadInfoBean == null) {
			UploadInfoBean infoBean = new UploadInfoBean();
			infoBean.setPercentage(0);
		}
		return uploadInfoBean;
	}

	@RequestMapping(value = "/FileUploadUploadify", method = RequestMethod.POST)
	public @ResponseBody
	String createUploadify(UploadBean uploadBean, BindingResult result,
			HttpServletRequest request, Model model, MultipartRequest mrequest,
			HttpSession session) throws Exception {
		String folderPathOrignal = "";
		String folderPathLogo = "";
		String folderPathThumb = "";
		String fileOrignalName = "";
		String logoUrl = "";
		String orignalUrl = "";
		String thumbUrl = "";
		try {
			System.out.println("started uploading");
			MultipartFile file = mrequest.getFile("Filedata");

			BufferedImage originalImage = ImageIO.read(mrequest.getFile(
					"Filedata").getInputStream());
			int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB
					: originalImage.getType();

			BufferedImage resizeImageJpgLogo = resizeImageLogo(originalImage,
					type);
			// ImageIO.write(resizeImageJpg, "jpg", new
			// File("c:\\resizedImage\\"
			// + mrequest.getFile("Filedata").getOriginalFilename()
			// + "_small.jpg"));
			ByteArrayOutputStream osLogo = new ByteArrayOutputStream();
			ImageIO.write(resizeImageJpgLogo, "jpg", osLogo);

			BufferedImage resizeImageJpgThumb = resizeImageThumb(originalImage,
					type);
			ByteArrayOutputStream osThumb = new ByteArrayOutputStream();
			ImageIO.write(resizeImageJpgThumb, "jpg", osThumb);

			System.out.println("session : " + session);
			// String userName = request.getParameter("userName");
			String userId = request.getParameter("userId");

			folderPathOrignal = "igild/" + userId + "/orignal";
			folderPathLogo = "igild/" + userId + "/logo";
			folderPathThumb = "igild/" + userId + "/thumb";

			fileOrignalName = file.getOriginalFilename();
			orignalUrl = repositoryService.putAsset(folderPathOrignal,
					folderPathOrignal + "/" + file.getOriginalFilename(),
					new ByteArrayInputStream(file.getBytes()), file);
			System.out.println("Orignal file uploaded");
			logoUrl = repositoryService.putAsset(folderPathLogo, folderPathLogo
					+ "/" + file.getOriginalFilename(),
					new ByteArrayInputStream(osLogo.toByteArray()), file);
			System.out.println("Logo file uploaded");

			thumbUrl = repositoryService.putAsset(folderPathLogo,
					folderPathThumb + "/" + file.getOriginalFilename(),
					new ByteArrayInputStream(osThumb.toByteArray()), file);
			System.out.println("Thumb file uploaded");

			saveImageMappingData(Integer.parseInt(userId),
					file.getOriginalFilename());

		} catch (Exception e) {
			throw e;
		}
		return logoUrl;

	}

	private void saveImageMappingData(int userId, String fileName) {
		// int sequenceNo = imageMappingService.getNewSequenceNo(userId);
		imageMappingService.saveRecord(userId, fileName);
	}

	private BufferedImage resizeImageLogo(BufferedImage originalImage, int type) {
		BufferedImage resizedImage = new BufferedImage(
				RegisterConstant.IMG_WIDTH_1, RegisterConstant.IMG_HEIGHT_1,
				type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, RegisterConstant.IMG_WIDTH_1,
				RegisterConstant.IMG_HEIGHT_1, null);
		g.dispose();

		return resizedImage;
	}

	private BufferedImage resizeImageThumb(BufferedImage originalImage, int type) {
		BufferedImage resizedImage = new BufferedImage(
				RegisterConstant.IMG_WIDTH_2, RegisterConstant.IMG_HEIGHT_2,
				type);
		Graphics2D g = resizedImage.createGraphics();
		g.drawImage(originalImage, 0, 0, RegisterConstant.IMG_WIDTH_2,
				RegisterConstant.IMG_HEIGHT_2, null);
		g.dispose();

		return resizedImage;
	}

	@RequestMapping(value = "/ListUploadedImages", method = RequestMethod.GET)
	public String getAllImages(Model model, HttpServletRequest request,
			HttpSession session) {
		commonUtil.getImages(model, session, ProjectConstant.IMAGE_TYPE_THUMB);
		return "imageListView";
	}


	@RequestMapping(value = "/updateUploadedImages")
	public String updateUploadedImages(Model model, HttpServletRequest request,
			HttpSession session) {
		String csv = request.getParameter("csv");
		String[] ids = csv.split(",");
		for (int i = 1; i <= ids.length; i++) {
			if (!"".equals(ids[i - 1])) {
				// imageMappingService
				// .updateSeqNo(Integer.parseInt(ids[i - 1]), i);
			}
		}

		commonUtil.getImages(model, session, ProjectConstant.IMAGE_TYPE_THUMB);
		return "imageListView";
	}
}
