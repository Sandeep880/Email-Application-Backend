package com.emailapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.emailapi.model.EmailRequest;
import com.emailapi.model.EmailResponse;
import com.emailapi.services.EmailService;

@RestController
@CrossOrigin
public class EmailController {
	
	@Autowired
	private EmailService emailService;
	
	
	@RequestMapping(value = "/sendmail" , method= RequestMethod.POST)
	public ResponseEntity<?> sendemail(@RequestBody EmailRequest request)
	{
		boolean result = this.emailService.sendEmail(request.getTo(), request.getFrom(), request.getSubject(), request.getMessage());
		System.out.println(request);
		
		if(result)
		   {
			  return ResponseEntity.ok("Email Sent Successfully");
		   }
		else
		{
			return new ResponseEntity<>("Email not sent successfully",HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
