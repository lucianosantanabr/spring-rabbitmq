package com.github.lucianosantanabr.config;

import java.util.HashSet;
import java.util.Set;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurationSupport {

  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2).select()
        .apis(RequestHandlerSelectors.basePackage("com.github.lucianosantanabr.resource")).build()
        .apiInfo(metaData()).protocols(protocols())
        .host("localhost:8082");

  }

  private Set<String> protocols() {
    Set<String> protocols = new HashSet<>(1);
    protocols.add("http");
    return protocols;
  }

  private ApiInfo metaData() {
    return new ApiInfoBuilder().title("producer rabbitMQ rest API")
        .description(//
            "Applications responsible to configure devices and expose current setup configuration.")//
        .version("v1")//
        .license("Copyrights 2021 - Luciano S. Santana - Todos os Direitos Reservados")//
        .licenseUrl("https://github.com/lucianosantanabr").build();
  }

  @Override
  protected void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("swagger-ui.html")//
        .addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**")//
        .addResourceLocations("classpath:/META-INF/resources/webjars/");
  }

}