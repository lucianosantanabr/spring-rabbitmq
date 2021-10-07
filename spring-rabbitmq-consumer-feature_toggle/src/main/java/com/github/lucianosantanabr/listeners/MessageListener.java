package com.github.lucianosantanabr.listeners;

import com.github.lucianosantanabr.commons.RabbitMQConnectionUtils;
import com.github.lucianosantanabr.dto.MessageMQ;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageListener {

  public static final String ID = "listenerMessageQueue";

  @RabbitListener(id = ID, queues = "${app.listener.message.queue:message_queue}",//
      autoStartup = "${app.listener.message.autostart}")
  public void consumerMessage(byte[] message) {

    try {
      MessageMQ messageMQ = RabbitMQConnectionUtils.convertData(message, MessageMQ.class);
      log.info("Message Listener => " + messageMQ);
    } catch (IOException e) {
      log.error("Error on consuming from RabbitMQ. Message {} ", e.getMessage());
    }

  }
}
