package com.hmbnet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloController {

	// create a mapping used by greeting.html
	@RequestMapping(value = {"/greeting" , "/"})
	public String sayHello(String name, Model model) {
		model.addAttribute("name", name);
		return "hello";
	}
}
