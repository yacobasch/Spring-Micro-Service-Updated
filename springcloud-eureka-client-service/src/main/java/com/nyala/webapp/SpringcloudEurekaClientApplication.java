package com.nyala.webapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringcloudEurekaClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringcloudEurekaClientApplication.class, args);
	}

}
