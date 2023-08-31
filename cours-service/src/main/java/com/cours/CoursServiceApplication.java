package com.cours;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
//import org.springframework.cloud.openfeign.EnableFeignClients;
@SpringBootApplication
//@EnableFeignClients
public class CoursServiceApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(CoursServiceApplication.class, args);
		System.out.println("service cours est lancee");
	}
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    
}
