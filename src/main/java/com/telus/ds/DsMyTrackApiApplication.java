package com.telus.ds;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "DSU Track API", version = "1.0", description = "DSU Track API"))
public class DsMyTrackApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DsMyTrackApiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
