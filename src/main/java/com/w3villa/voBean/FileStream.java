package com.w3villa.voBean;

import java.io.InputStream;

public class FileStream {
	private InputStream inputStream;
	private long size;
	
	public FileStream(InputStream input, long size) {
		this.inputStream = input;
		this.size = size;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream input) {
		this.inputStream = input;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
	
	
}
