package com.w3villa.main.authentication.userServiceImpl;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.w3villa.constants.ProjectConstant;
import com.w3villa.main.authentication.userService.RepositoryService;
import com.w3villa.upload.UploadFileResponse;
import com.w3villa.upload.UploadInfoBean;
import com.w3villa.voBean.FileStream;
import com.w3villa.voBean.UploadItem;

//@Resource(name = "RepositoryFileSystemImpl")
public class RepositoryFileSystemImpl implements RepositoryService {

	@Override
	public UploadFileResponse putAsset(String path, String assetName,
			InputStream asset, HttpSession session, HttpServletRequest request,
			UploadFileResponse uploadFileResponse, MultipartFile file,
			UploadItem uploadItem) throws Exception {
		String fileName = null;
		OutputStream outputStream = null;
		if (file.getSize() > 0) {
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
			fileName = request.getRealPath("") + uploadItem.getUploadPath()
					+ file.getOriginalFilename();
			outputStream = new FileOutputStream(fileName);
			int readBytes = 0;
			byte[] buffer = new byte[15728640];
			int totalByteRead = 0;
			UploadInfoBean infoBean = new UploadInfoBean();
			while ((readBytes = asset.read(buffer, 0, 5120)) != -1) {
				if(buffer.length%1024 == 0){
					totalByteRead = totalByteRead + readBytes;
					infoBean.setBytesRead(totalByteRead);
					infoBean.setTotalSize(file.getSize());
					session.setAttribute("uploadInfoBean", infoBean);
				}
				outputStream.write(buffer, 0, readBytes);
			}
		}
		return uploadFileResponse;
	}

	@Override
	public List<String> getAssetList(String path) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public FileStream getAssetByName(String path, String name,
			HttpSession session)
			throws FileNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String putAsset(String assetPath, String string,
			ByteArrayInputStream byteArrayInputStream, MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUrl(String key) {
		// TODO Auto-generated method stub
		return null;
	}

}
