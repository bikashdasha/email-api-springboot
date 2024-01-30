package com.oupp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oupp.model.EmailRequest;
import com.oupp.service.EmailService;

@RestController
public class EmailController {

	@Autowired
	private EmailService emailService;
	@RequestMapping("/")
	public String welcome() {
		return "this is my emial api";
	}
	
	//api to send email
	@RequestMapping(value = "/send",method = RequestMethod.POST)
	public ResponseEntity<?> sendEmail(@RequestBody EmailRequest request){
		
		System.out.print(request);
		boolean result= this.emailService.sendEmail(request.getSubject(), request.getMessage(), request.getTo());
		if(result) {
		return ResponseEntity.ok("suceesfull");
		}
		else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("somethis unsuceesfull");
		}
	}
}
