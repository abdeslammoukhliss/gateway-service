package org.test.gatewayservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableDiscoveryClient

public class GatewayServiceApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(GatewayServiceApplication.class, args);
	}

	@Autowired
	KubernetesServiceDiscoveryService kubService;
	@Override
	public void run(String... args) throws Exception {
		System.out.println(kubService.getServiceUrls("default","app=spring-microservice"));
	}
}
