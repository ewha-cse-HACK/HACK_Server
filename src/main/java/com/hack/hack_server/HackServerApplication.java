package com.hack.hack_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class HackServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackServerApplication.class, args);
	}

}
