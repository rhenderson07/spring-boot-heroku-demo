package com.hmbnet.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.ServletRequestAttributes;

@Controller
@RequestMapping("/error")
public class BasicErrorController implements ErrorController {

	private final ErrorAttributes errorAttributes;

	@Autowired
	public BasicErrorController(ErrorAttributes errorAttributes) {
		this.errorAttributes = errorAttributes;
	}

	@Override
	public String getErrorPath() {
		return "/error";
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody
	public Map<String, Object> errorJson(HttpServletRequest request) {
		return getErrorAttributeMap(request);
	}

	@RequestMapping(method = RequestMethod.GET, produces = "text/html")
	public String errorHtml(HttpServletRequest request, Model model) {
		model.addAllAttributes(getErrorAttributeMap(request));
		return getErrorPath();
	}

	private Map<String, Object> getErrorAttributeMap(HttpServletRequest request) {
		Map<String, Object> body = getErrorAttributes(request, getTraceParameter(request));
		String trace = (String) body.get("trace");
		if (trace != null) {
			String[] lines = trace.split("\n\t");
			body.put("trace", lines);
		}
		return body;
	}

	private boolean getTraceParameter(HttpServletRequest request) {
		String parameter = request.getParameter("trace");
		if (parameter == null) {
			return false;
		}
		return !"false".equalsIgnoreCase(parameter);
	}

	private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
		RequestAttributes requestAttributes = new ServletRequestAttributes(request);
		return errorAttributes.getErrorAttributes(requestAttributes, includeStackTrace);
	}

}
