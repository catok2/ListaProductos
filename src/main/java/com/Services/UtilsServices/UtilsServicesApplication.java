package com.Services.UtilsServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.Services.UtilsServices.models") // Escanea el paquete de modelos
public class UtilsServicesApplication {
	public static void main(String[] args) {
		SpringApplication.run(UtilsServicesApplication.class, args);
	}
}