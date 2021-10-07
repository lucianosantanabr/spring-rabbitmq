package com.github.lucianosantanabr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ApplicationRabbitConsumerToggle {

  public static void main(String[] args) {
    SpringApplication.run(ApplicationRabbitConsumerToggle.class, args);
  }

}
