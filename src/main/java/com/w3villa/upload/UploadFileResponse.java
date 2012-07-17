package com.w3villa.upload;

public class UploadFileResponse {
	private String status;
	private String message;
	private String fileName;
	private String uploadPath;
	
	
	public String getUploadPath() {
		return uploadPath;
	}

	public void setUploadPath(String uploadPath) {
		this.uploadPath = uploadPath;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "UploadFileResponse [status=" + status + ", message=" + message
				+ ", fileName=" + fileName + ", uploadPath=" + uploadPath + "]";
	}
	


}
