package com.github.lucianosantanabr.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.github.lucianosantanabr.config.RabbitMQConfig;
import com.github.lucianosantanabr.dto.MessageMQ;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class MessageListener {

	@RabbitListener(queues = RabbitMQConfig.QUEUE)
	public void consumerMessage(MessageMQ message) {
		log.info("Message Listener => " + message);
	}
}
