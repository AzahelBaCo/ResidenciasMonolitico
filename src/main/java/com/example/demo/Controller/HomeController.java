package com.example.demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home(Model model) {
		return "index";
	}
	
	@GetMapping("/home")
	public String h(Model model) {
		return "Login";
	}
	
}
