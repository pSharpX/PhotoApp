package com.globant.photoapp.api.account.photoappapiaccountmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PhotoappapiaccountmanagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhotoappapiaccountmanagementApplication.class, args);
	}

}
