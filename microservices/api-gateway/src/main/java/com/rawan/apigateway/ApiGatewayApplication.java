package com.rawan.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p.path("/get")
						.filters(f -> f.addRequestHeader("Hello", "World")
								.addRequestParameter("FirstName", "Rawan")
								.addRequestParameter("LastName", "Gedeon"))
						.uri("http://httpbin.org:80"))
				.route(p -> p
						.path("/currency-converter/**")
			            .filters(f -> f.circuitBreaker(config -> config
			                .setName("mycmd")
			                .setFallbackUri("forward:/fallback")))
			            .uri("lb://currency-conversion-service"))
				
				.route(p -> p
						.path("/currency-exchange/**")
						.uri("lb://currency-exchange-service"))
				.route(p -> p
						.path("/currency-converter/**")
						.uri("lb://currency-conversion-service"))
				.route(p -> p
						.path("/currency-converter-feign/**")
						.uri("lb://currency-conversion-service"))

				.build();
	}
	
	@RequestMapping("/fallback")
	public Mono<String> fallback() {
	  return Mono.just("A problem has occured, please try again later.");
	}
	
	

}