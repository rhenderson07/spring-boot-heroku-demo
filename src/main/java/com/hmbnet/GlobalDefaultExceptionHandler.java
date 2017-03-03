package com.hmbnet;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
class GlobalDefaultExceptionHandler {
    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
	// If the exception is annotated with @ResponseStatus rethrow it and let
	// the framework handle it
	if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
	    throw e;
	}

	// Otherwise setup and send the user to a default error-view.
	ModelAndView mav = new ModelAndView();
	
	HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
	
	mav.addObject("timestamp", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
	mav.addObject("status", status.value());
	mav.addObject("error", status.getReasonPhrase());
	mav.addObject("path", req.getRequestURL());
	mav.addObject("message", e.getMessage());
	mav.setViewName(DEFAULT_ERROR_VIEW);
	return mav;
    }
}