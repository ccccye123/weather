package org.ccccye.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WeatherApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherApplication.class, args);
	}

}
