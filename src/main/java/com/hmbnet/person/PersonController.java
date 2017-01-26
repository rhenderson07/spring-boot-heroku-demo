/*
 * Copyright 2015 Benedikt Ritter
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.hmbnet.person;

import javax.validation.Valid;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/person")
public class PersonController {

	private PersonRepository repository;

	@Autowired
	public PersonController(PersonRepository repository) {
		this.repository = repository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String viewPeople(ModelMap model) {
		List<Person> people = repository.findAll();
		model.addAttribute("people", people);
		model.addAttribute("insertPerson", new Person());
		return "people";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String addPerson(ModelMap model, @ModelAttribute("insertRecord") @Valid Person record,
			BindingResult result) {

		if (!result.hasErrors()) {
			repository.save(record);
		}
		
		return "redirect:/person";
	}
}
