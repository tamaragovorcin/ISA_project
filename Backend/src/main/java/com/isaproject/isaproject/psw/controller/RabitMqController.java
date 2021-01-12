package com.isaproject.isaproject.psw.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.isaproject.isaproject.psw.model.Message;
import com.isaproject.isaproject.psw.model.Pharmacy;
import com.isaproject.isaproject.psw.service.IPharmacyService;
import com.isaproject.isaproject.service.RabbitMQSender;

@RestController
@RequestMapping("/api")
public class RabitMqController {

	@Autowired
	private RabbitMQSender service;
	Message m = new Message();
	
	 @PostMapping("/messageRabbit")
	 Message newMessage(@RequestBody Message message) throws ParseException
	 {
	      /*  return service.add(message) ?
	            ResponseEntity.ok(message) :
	            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	            */
		 System.out.println("********************");
		 System.out.println(message.getText());
		 System.out.println(message.getDateAction());
		 SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);
		 
		 m = new Message(message.getText(), new Date(), message.getDateAction());
	        service.send(m);
	        System.out.println("Sent :" + m.toString());
	
		 //Date date = (Date) formatter.parse(message.getDateAction());
		 //Date date1=(Date) new SimpleDateFormat("dd/MM/yyyy").parse(message.getDateAction());  
		// System.out.println(date1);  
		 return message;
	 }
}
