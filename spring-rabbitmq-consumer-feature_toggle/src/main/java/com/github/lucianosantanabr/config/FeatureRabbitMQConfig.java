package com.github.lucianosantanabr.config;

import com.github.lucianosantanabr.listeners.MessageListener;
import com.github.lucianosantanabr.service.FeatureFlagService;
import java.util.Optional;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@EnableScheduling
public class FeatureRabbitMQConfig {

  private static final String MESSAGE_FLAG = "app.listener.message.activate";

  private @Autowired
  RabbitListenerEndpointRegistry registry;
  private @Autowired
  FeatureFlagService flag;

  @Scheduled(fixedRateString = "${app.listener.toggle.scheduler}")
  public void reCheck() {
    toggle();
  }

  @PostConstruct
  public void toggle() {

    if (registry == null) {
      log.error("Listener Registry is null....");
      return;
    }

    if (flag.get(MESSAGE_FLAG).getStatus()) {
      if (!isRunning(MessageListener.ID)) {
        log.info("Starting queue {}", MessageListener.ID);
        start(MessageListener.ID);
      }
    } else {
      if (isRunning(MessageListener.ID)) {
        log.info("Stoping queue {}", MessageListener.ID);
        stop(MessageListener.ID);
      }
    }
  }

  public void start(String id) {
    Optional<MessageListenerContainer> optional = get(id);
    if (optional.isPresent()) {
      optional.get().start();
    }
  }

  public void stop(String id) {
    Optional<MessageListenerContainer> optional = get(id);
    if (optional.isPresent()) {
      optional.get().stop();
    }
  }

  public boolean isRunning(String id) {
    Optional<MessageListenerContainer> optional = get(id);
    if (optional.isPresent()) {
      return optional.get().isRunning();
    }
    return false;
  }

  public Optional<MessageListenerContainer> get(String id) {
    return Optional.ofNullable(registry.getListenerContainer(id));
  }

}
