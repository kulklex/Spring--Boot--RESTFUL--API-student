package com.studentservice.app.service.impl;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailPreparationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.studentservice.app.entity.Email;
import com.studentservice.app.services.EmailService;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
public class EmailServiceImpl implements EmailService {
	
	@Autowired
	Configuration freemarkerMailConfiguration;
	@Autowired
	private JavaMailSender mailSender;
	
	private final static String TEMPLATE_PATH = "templates";

	@Override
	public int sendHtmlEmail(Email email) throws Exception {
		final String eml = email.getFrom();
	    final String from = email.getFromEmail();
	    final String to = email.getTo();
	    final String subject = email.getSubject();
	    final String tmpl = email.getTemplateName();
	    final Map<String, String> templateTokens = email.getTemplateTokens();
	    
	    MimeMessage message = mailSender.createMimeMessage();
	    InternetAddress inetAddress = new InternetAddress();

        inetAddress.setPersonal(eml);
        inetAddress.setAddress(from);
        
        try {
        	MimeMessageHelper helper = new MimeMessageHelper(message,
					MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
					StandardCharsets.UTF_8.name());
        	freemarkerMailConfiguration.setClassForTemplateLoading(EmailServiceImpl.class, "/");
        	 Template textTemplate = freemarkerMailConfiguration.getTemplate(
        	            new StringBuilder(TEMPLATE_PATH).append("/").append(tmpl).toString());
             String html = FreeMarkerTemplateUtils.processTemplateIntoString(textTemplate, templateTokens);
        	helper.setFrom(inetAddress);
        	helper.setSubject(subject);
        	helper.setText(html, true);
        	helper.setTo(to);
        	mailSender.send(message);
        	return 1;
        }catch(Exception e) {
            throw new MailPreparationException("Can't generate text mail", e);
        }
	}

}
