package com.example.android;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication(scanBasePackages = {"com.example.android"})
public class AndroidApplication {


	public static void main(String[] args) {
		SpringApplication.run(AndroidApplication.class, args);
	}
	@GetMapping("/")
	public String ping() {
		return "Servidor activo y escaneo forzado exitoso!";
	}
}
