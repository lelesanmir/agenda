package com.leonardodossantos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/home")
	public String home() {
		return "home/index"; // Retorna o nome do arquivo HTML sem a extensão
	}

	@GetMapping("/sair")
	public String sair() {
		return "home/sair"; // Retorna o nome do arquivo sair.html sem a extensão
	}

}
