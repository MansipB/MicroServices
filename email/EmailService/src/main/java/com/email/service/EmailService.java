package com.email.service;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.email.model.EmailRequest;

@Service
public class EmailService {

	@Autowired
	RestTemplate restTemplate;

	public void sendEmail(EmailRequest request) throws MessagingException {

		String subject = request.getSubject();
		String message = request.getMessage();
		String to = request.getTo();
		String from = request.getFrom();

		// variable for gmail
		String host = "smtp.gmail.com";

		// get the system properties
		Properties properties = System.getProperties();

		// set host
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port", "465");
		properties.put("mail.smtp.ssl.enable", "true");
		properties.put("mail.smtp.auth", "true");

		// get the session object
		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication("peakyblinders28hs@gmail.com", "Peaky28B");
			}

		});

		session.setDebug(true);
		// compose the message
		MimeMessage mimeMessage = new MimeMessage(session);

		mimeMessage.setFrom(from);
		mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		mimeMessage.setSubject(subject);
		mimeMessage.setText(message);

		Transport.send(mimeMessage);
	}

}
