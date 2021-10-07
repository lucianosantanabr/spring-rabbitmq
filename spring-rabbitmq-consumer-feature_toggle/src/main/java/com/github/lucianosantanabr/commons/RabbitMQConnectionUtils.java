package com.github.lucianosantanabr.commons;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class RabbitMQConnectionUtils {

  public static <T> byte[] convertData(final T data) throws JsonProcessingException {
    final ObjectMapper objectMapper = new ObjectMapper();
    return objectMapper.writeValueAsBytes(data);
  }

  public static <U> U convertData(final byte[] data, final Class<U> javaType) throws IOException {
    final ObjectMapper objectMapper = new ObjectMapper();

    return objectMapper.readValue(data, javaType);
  }

}
