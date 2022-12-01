package com.jbk.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class AuthorRestApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthorRestApiApplication.class, args);
	}
}
