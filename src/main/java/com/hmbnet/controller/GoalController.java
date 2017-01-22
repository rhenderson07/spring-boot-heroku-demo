package com.hmbnet.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hmbnet.model.Goal;

@Controller
@SessionAttributes("goal")
public class GoalController {

	// Only handle GET requests. Produces error on POST.
	// passing in entire model, not individual attributes.
	@RequestMapping(value = "addGoal", method = RequestMethod.GET)
	public String viewAddGoalForm(Model model) {
		Goal defaultGoal = new Goal();
		defaultGoal.setMinutes(10);

		model.addAttribute("goal", defaultGoal);

		return "addGoal";
	}

	// Only handle POST requests. This will be used to submitting form
	@RequestMapping(value = "addGoal", method = RequestMethod.POST)
	public String updateGoal(@ModelAttribute("goal") Goal goal) {
		System.out.println("Minutes updated: " + goal.getMinutes());

		return "redirect:addMinutes.html";
	}
}
