package com.workshop.postal;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;





@SpringBootApplication
public class PostalCompanyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostalCompanyApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
