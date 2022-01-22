package com.rk.mtms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Configuration
@EnableAutoConfiguration
public class Mtms {

	public static void main(String[] args) {
		SpringApplication.run(Mtms.class, args);
	}

}
