package com.globant.photoapp.api.gateway.photoappapizuulapigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class PhotoappapizuulapigatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoappapizuulapigatewayApplication.class, args);
	}

}
