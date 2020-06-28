package org.ccccye.citycode;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CitycodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitycodeApplication.class, args);
	}

}
