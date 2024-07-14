package com.example.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ApiGatewayApplication.class);

		// Configura el perfil por defecto si no se especifica uno
		if (System.getenv("SPRING_PROFILES_ACTIVE") == null) {
			app.setAdditionalProfiles("dev");
		}

		app.run(args);
	}

}
