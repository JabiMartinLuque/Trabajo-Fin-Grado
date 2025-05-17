package com.tfg.tfg_backend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // Se mapea el directorio "uploads" para que sea accesible vía url "/uploads/**"
    registry.addResourceHandler("/uploads/**")
            .addResourceLocations("file:uploads/");
  }
}