package com.leonardodossantos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.RestController;

@EntityScan(basePackages = "com.leonardodossantos.model")
@ComponentScan(basePackages = "com.leonardodossantos.*")//acrescenteo um ponto antes do *.
@EnableJpaRepositories(basePackages = "com.leonardodossantos.repository")
@RestController
@EnableAutoConfiguration
@SpringBootApplication
public class AvaliacaoLeonardoDosSantosMirandaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AvaliacaoLeonardoDosSantosMirandaApplication.class, args);
	}

}
