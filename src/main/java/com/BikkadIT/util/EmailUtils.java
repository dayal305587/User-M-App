package com.BikkadIT.util;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailUtils {
	@Autowired
	private JavaMailSender mailSender;
	
	public boolean sendmail(String to,String subject,String body) throws MessagingException {
		
		boolean isSent=false;
		
	MimeMessage mineMessage = mailSender.createMimeMessage();
	
	MimeMessageHelper helper = new MimeMessageHelper(mineMessage);
	helper.setTo(to);
	helper.setSubject(subject);
	helper.setText(body,true);
	
	mailSender.send(mineMessage);
	isSent=true;
		
		return isSent;
		
	}

}
