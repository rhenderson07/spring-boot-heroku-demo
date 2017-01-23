package com.hmbnet.controller;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BasicErrorController implements ErrorController {

	private static final String ERROR_PATH = "/error";

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

	@RequestMapping(value = ERROR_PATH, method = RequestMethod.GET)
	public String viewError(
			@RequestParam(name = "javax.servlet.error.status_code", required = false) Integer statusCode,
			@RequestParam(name = "javax.servlet.error.exception", required = false) Throwable throwable,
			Model model) {

		String errorMessage = throwable != null ? throwable.getMessage() : null;

		model.addAttribute("statusCode", statusCode);
		model.addAttribute("errorMessage", errorMessage);

		return "error";
	}

}
