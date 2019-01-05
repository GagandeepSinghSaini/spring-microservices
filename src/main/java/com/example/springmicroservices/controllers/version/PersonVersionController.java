package com.example.springmicroservices.controllers.version;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springmicroservices.beans.version.Name;
import com.example.springmicroservices.beans.version.PersonV1;
import com.example.springmicroservices.beans.version.PersonV2;

@RestController
public class PersonVersionController {

	@GetMapping(path = "/person/v1")
	public PersonV1 personV1() {
		return new PersonV1(new Name("Gagandeep", "Saini"));
	}
	
	@GetMapping(path = "/person/v2")
	public PersonV2 personV2() {
		return new PersonV2("Gagandeep Singh Saini");
	}
	
	@GetMapping(path="/person/param" , params = "version=1")
	public PersonV1 paramV1() {
		return new PersonV1(new Name("param","version1"));
	}
	
	@GetMapping(path = "/person/param", params = "version=2")
	public PersonV2 paramV2() {
		return new PersonV2("param version 2");
	}
	
	@GetMapping(path = "/person/header", headers = "X-PERSON_V=1")
	public PersonV1 headerV1() {
		return new PersonV1(new Name("header", "Version1"));
	}
	
	@GetMapping(path = "/person/header", headers = "X-PERSON_V=2")
	public PersonV2 headerV2() {
		return new PersonV2("Header Version2");
	}
	
}
