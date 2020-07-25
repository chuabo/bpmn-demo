package com.example.bpmndemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.camunda.bpm.spring.boot.starter.annotation.EnableProcessApplication;

//@SpringBootApplication
//@SpringBootApplication(scanBasePackages = "com.example.controller")
@SpringBootApplication(scanBasePackages = "com")
@EnableProcessApplication
public class BpmnDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BpmnDemoApplication.class, args);
	}

}
