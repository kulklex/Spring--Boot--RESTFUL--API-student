package com.studentservice.app.services;

import com.studentservice.app.entity.Email;

public interface EmailService {
	int sendHtmlEmail(Email email) throws Exception;
}
