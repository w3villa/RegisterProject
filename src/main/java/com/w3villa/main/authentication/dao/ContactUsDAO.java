package com.w3villa.main.authentication.dao;

import java.util.List;

import com.w3villa.main.authentication.domain.ContactUs;

public interface ContactUsDAO {
	void saveContactUs(ContactUs contactUs);

	List<ContactUs> getAllContactUs();

	ContactUs getById(int id);
}
