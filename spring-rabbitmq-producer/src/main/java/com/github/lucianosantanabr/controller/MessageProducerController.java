package com.github.lucianosantanabr.controller;

import java.util.Date;
import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.lucianosantanabr.config.RabbitMQConfig;
import com.github.lucianosantanabr.dto.MessageMQ;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@Api(value = "producers")
@RestController
@RequestMapping(path = "/producers")
public class MessageProducerController {

	@Autowired
	private RabbitTemplate template;
	
	@ApiOperation(value = "producer message RabbitMQ", notes = "Method responsible for producing messages in RabbitMQ. ")
	@PostMapping
	public String producerMessage(@ApiParam(value = "MessageMQ", required = true, example = "MessageMQ") @RequestBody MessageMQ message) {
		message.setIdMessage(UUID.randomUUID().toString());
		message.setDateMessage(new Date());
		template.convertAndSend(RabbitMQConfig.EXCHANGE,
						   		RabbitMQConfig.ROUTING_KEY,
						   		message);
		
		return "Message Published!!";
	}
}
