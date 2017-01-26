package com.hmbnet.person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long personId;

	@NotEmpty
	private String firstName;

	private String middleName;

	@NotEmpty
	private String lastName;

	@NotEmpty
	private String email;
}
