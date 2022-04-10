package com.yousef.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/")
public class MainController {
	
	@GetMapping
	public String welcome() {
		System.out.println("success");
		return "index";
	}
}
