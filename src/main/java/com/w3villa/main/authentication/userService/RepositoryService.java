package com.w3villa.main.authentication.userService;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.multipart.MultipartFile;

import com.w3villa.upload.UploadFileResponse;
import com.w3villa.voBean.FileStream;
import com.w3villa.voBean.UploadItem;

public interface RepositoryService {
	public UploadFileResponse putAsset(String path, String assetName,
			InputStream asset,
			HttpSession session, HttpServletRequest request,
			UploadFileResponse uploadFileResponse, MultipartFile file,
			UploadItem uploadItem) throws Exception;
	public List<String> getAssetList(String path);

	public FileStream getAssetByName(String path, String name,
			HttpSession session) throws FileNotFoundException;

	public void putAsset(String assetPath, String string,
			ByteArrayInputStream byteArrayInputStream, MultipartFile file);
}
