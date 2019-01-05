package com.example.springmicroservices.controllers.dynamicfilter;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springmicroservices.beans.dynamicfilter.Teacher;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class DynamicFilterClass {

	@GetMapping(path = "/teacher")
	public MappingJacksonValue getTeacher() {
		Teacher teacher =  new Teacher(101, "Preeti", "CSE");
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("id","name");
		FilterProvider filters = new SimpleFilterProvider().addFilter("teacherFilter", filter );
		
		MappingJacksonValue value = new MappingJacksonValue(teacher);
		value.setFilters(filters);
		
		return value;
		
	}
	
	@GetMapping(path  = "/teachers")
	public MappingJacksonValue getTeachers() {
		List<Teacher> teachers = Arrays.asList(
				new Teacher(102, "Jaspreet", "Physics")
				, new Teacher(103, "Sachin", "Maths"));
		
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name", "department");
		FilterProvider filters = new SimpleFilterProvider().addFilter("teacherFilter", filter );
		
		MappingJacksonValue value = new MappingJacksonValue(teachers);
	    value.setFilters(filters);
	    
	    return value;
	}
	
}
