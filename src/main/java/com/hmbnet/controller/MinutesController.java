package com.hmbnet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hmbnet.model.Exercise;

@Controller
public class MinutesController {

	// passing in single model attribute, exercise
	@RequestMapping(value = "/addMinutes")
	public String addMinutes(@ModelAttribute("exercise") Exercise exercise) {
		System.out.println("exercise: " + exercise.getMinutes());

		// return name of jsp file
		return "addMinutes";
	}

	@RequestMapping(value = "/forwardToAddMinutes")
	public String forwardToAddMinutes(@ModelAttribute("exercise") Exercise exercise) {
		System.out.println("preparing to forward: " + exercise.getMinutes());

		// chain to addMinutes.html. This approach goes outside the Spring framework, and is forwarded
		// back in, to a new url. This bypasses the ViewResolver. Request is kept open during a forward
		return "forward:addMinutes.html";
	}

	@RequestMapping(value = "/redirectToAddMinutes")
	public String redirectToAddMinutes(@ModelAttribute("exercise") Exercise exercise) {
		System.out.println("preparing to redirect: " + exercise.getMinutes());

		// chain to addMinutes.html. This approach goes outside the Spring framework, and is redirected
		// back in, to a new url. This bypasses the ViewResolver. Request is closed before redirecting to new
		// url. Redirect is good for preventing a user from submitting a form multiple times
		return "redirect:addMinutes.html";
	}
}
