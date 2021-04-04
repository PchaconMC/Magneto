package com.magneto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class MagnetoEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(MagnetoEurekaApplication.class, args);
	}

}
