package com.isaproject.isaproject.rabbitmq;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.isaproject.isaproject.psw.Message;
import com.isaproject.isaproject.service.RabbitMQSender;

@Component
public class ScheduledTasks {
	 private final RabbitMQSender rabbitMQSender;
	 
	    @Autowired
	    public ScheduledTasks(RabbitMQSender rabbitMQSender) {
	        this.rabbitMQSender = rabbitMQSender;
	    }


		@Scheduled(fixedRate = 3000)
	    public void sendMessage() {
			Message message = new Message("Neka poruka: " + UUID.randomUUID().toString(),
		             new Date());;
	        rabbitMQSender.send(message);
	        System.out.println("Sent :" + message.toString());
	    }
}
