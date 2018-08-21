package com.reclameaqui.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ReclameAquiApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReclameAquiApiApplication.class, args);
	}
}
