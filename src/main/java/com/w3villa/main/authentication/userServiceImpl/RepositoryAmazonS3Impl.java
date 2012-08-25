package com.w3villa.main.authentication.userServiceImpl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.amazonaws.services.s3.transfer.Download;
import com.amazonaws.services.s3.transfer.TransferManager;
import com.amazonaws.services.s3.transfer.Upload;
import com.w3villa.main.authentication.userService.RepositoryService;
import com.w3villa.upload.UploadFileResponse;
import com.w3villa.upload.UploadInfoBean;
import com.w3villa.voBean.FileStream;
import com.w3villa.voBean.UploadItem;

@Component
public class RepositoryAmazonS3Impl implements RepositoryService {

	private static final String FOLDER_SUFFIX = "/";

	private final AmazonS3 s3;

	@Value("w3villa")
	private String bucket;

	private final TransferManager transferManager;

	@Autowired
	public RepositoryAmazonS3Impl(AmazonS3 client) {
		s3 = client;
		transferManager = new TransferManager(s3);
	}

	@Override
	public FileStream getAssetByName(String path, String name,
			HttpSession session) throws FileNotFoundException {
		String realContextPath = (String) session
				.getAttribute("realContextPath");
		String[] pathElements = name.split("/");
		String dirName = realContextPath;
		for (int i = 0; i < pathElements.length - 1; i++)
			dirName = dirName + "/" + pathElements[i];
		System.out.println("dir : " + dirName);
		File dir = new File(dirName);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File file = new File(realContextPath + name);
		System.out
				.println("file.getAbsolutePath() : " + file.getAbsolutePath());
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Download download = transferManager.download(bucket, name, file);
		while (!download.isDone()) {
			System.out.println("downloading competed :"
					+ download.getProgress().getPercentTransfered() + "%");
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		/*
		 * S3Object obj = s3.getObject(new GetObjectRequest(bucket,
		 * getS3Path(path) + name));
		 */

		return null;
	}

	@Override
	public List<String> getAssetList(String path) {
		List<String> result = new ArrayList<String>();
		ObjectListing objList = s3.listObjects(bucket, getS3Path(path));
		for (S3ObjectSummary summary : objList.getObjectSummaries()) {
			// ignore folders
			if (!summary.getKey().endsWith(FOLDER_SUFFIX)) {
				result.add(summary.getKey().substring(path.length()));
			}
		}
		return result;
	}

	@Override
	public UploadFileResponse putAsset(String path, String assetName,
			InputStream asset, HttpSession session, HttpServletRequest request,
			UploadFileResponse uploadFileResponse, MultipartFile file,
			UploadItem uploadItem) throws Exception {
		UploadFileResponse fileResponse = new UploadFileResponse();
		ObjectMetadata meta = new ObjectMetadata();
		meta.setContentLength(((ByteArrayInputStream) asset).available());
		/*
		 * s3.putObject(new PutObjectRequest(bucket, getS3Path(path) +
		 * assetName, asset, meta));
		 */
		Upload upload = transferManager.upload(bucket, assetName, asset, meta);
		UploadInfoBean infoBean = new UploadInfoBean();

		while (!upload.isDone()) {
			infoBean.setPercentage(((Double) upload.getProgress()
					.getPercentTransfered()).intValue());
			infoBean.setBytesRead(upload.getProgress().getBytesTransfered());
			infoBean.setTotalSize(file.getSize());
			session.setAttribute("uploadInfoBean", infoBean);
			Thread.sleep(50);
		}
		if (upload.isDone()) {
			infoBean.setPercentage(((Double) upload.getProgress()
					.getPercentTransfered()).intValue());
			infoBean.setBytesRead(upload.getProgress().getBytesTransfered());
			infoBean.setTotalSize(file.getSize());
			session.setAttribute("uploadInfoBean", infoBean);
		}

		// getAssetByName(path, assetName, session);
		return fileResponse;
	}

	private String getS3Path(String path) {
		// remove root path: /
		if (path.startsWith(FOLDER_SUFFIX)) {
			path = path.substring(1);
		}

		return path + FOLDER_SUFFIX;
	}

	@Override
	public void putAsset(String assetPath, String assetName,
			ByteArrayInputStream byteArrayInputStream, MultipartFile file) {
		ObjectMetadata meta = new ObjectMetadata();
		meta.setContentLength(byteArrayInputStream.available());
		Upload upload = transferManager.upload(bucket, assetName,
				byteArrayInputStream, meta);
		UploadInfoBean infoBean = new UploadInfoBean();
		while (!upload.isDone()) {
			System.out.println("Uploaded percentage : "
					+ upload.getProgress().getPercentTransfered() + " %");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (upload.isDone()) {
			System.out.println("file Uploaded.");
		}

	}
}
