package com.email.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.email.model.EmailRequest;
import com.email.service.EmailService;

@RestController
@RequestMapping("/email")
@CrossOrigin
public class EmailController {

	@Autowired
	EmailService emailService;

	@PostMapping("/send")
	public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {
		try {
			emailService.sendEmail(request);
		} catch (Exception e) {
			return ResponseEntity.internalServerError().body("Something Went Wrong");
		}
		return ResponseEntity.ok().body("success");
	}
}
