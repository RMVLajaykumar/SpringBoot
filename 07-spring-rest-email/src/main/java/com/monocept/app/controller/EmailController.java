package com.monocept.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.monocept.app.model.EmailStructure;
import com.monocept.app.services.MailService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/email")
public class EmailController {
	
	
	@Autowired
	private MailService mailService;
	
	
	
	@PostMapping("/send/{email}")
	public String sendMail(@PathVariable (name="email") String mail, @RequestBody EmailStructure mailStructure) {
		mailService.sendMail(mail, mailStructure);
		return "mail sent Successfully";
	}
	
	
	@PostMapping("/sendWithAttachment/{email}")
    public ResponseEntity<String> sendEmailWithAttachment(@PathVariable(name = "email") String mail, @RequestBody EmailStructure mailStructure, @RequestParam(name = "path") String path) throws MessagingException {
        if (mailService.sendEmailWithAttachment(mail, mailStructure, path)) {
        	return new ResponseEntity<String>("mail sent successfully",HttpStatus.ACCEPTED);
          
        }
		return  new ResponseEntity<String>("mail failed to send",HttpStatus.INTERNAL_SERVER_ERROR);
        
      
}


}
