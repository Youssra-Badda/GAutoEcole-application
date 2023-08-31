package com.condidat;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
//import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.client.RestTemplate;


@SpringBootApplication
@ComponentScan(basePackages = "com.condidat")

public class CondidatServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CondidatServiceApplication.class, args);
		System.out.println("hiiiiiiii Im Youssra");
		
	}
	
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    

	
}
