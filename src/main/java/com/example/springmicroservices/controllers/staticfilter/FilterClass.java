package com.example.springmicroservices.controllers.staticfilter;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springmicroservices.beans.staticfilter.Student;

/**
 * This class is for stating the static filters applied that is when 
 * we want only some of our properties to be transmitted via response.
 * @author HP
 *
 */

@RestController
public class FilterClass {

	@GetMapping(path = "/student")
	public Student getStudent() {
		return new Student(1, "Rajesh", 10);
	}
	
	@GetMapping(path = "/students")
	public List<Student> getStudentList() {
		return Arrays.asList(
				new Student(1, "Rajesh", 10),
				new Student(2, "Pinku", 20),
				new Student(3, "Rinku", 30));
	}
	
}
