package com.example.embededproject20252;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class EmbededProject20252Application {

  public static void main(String[] args) {
    SpringApplication.run(EmbededProject20252Application.class, args);
  }

}
