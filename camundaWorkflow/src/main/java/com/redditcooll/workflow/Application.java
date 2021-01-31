package com.redditcooll.workflow;

import org.camunda.bpm.spring.boot.starter.CamundaBpmAutoConfiguration;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = {CamundaBpmAutoConfiguration.class})
@EnableProcessApplication
@EnableTransactionManagement
public class Application {

  public static void main(String... args) {
    SpringApplication.run(Application.class, args);
  }

}