package com.config.server.ImplementationConfigServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class ImplementationConfigServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ImplementationConfigServerApplication.class, args);
	}

}
