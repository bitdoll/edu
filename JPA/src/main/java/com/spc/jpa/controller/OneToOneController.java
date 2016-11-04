package com.spc.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spc.jpa.domain.onetoone.Person;
import com.spc.jpa.domain.onetoone.PersonRepository;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@RestController
public class OneToOneController {
	
	@Autowired
	PersonRepository personRepository;

    @ApiOperation(value = "persons", notes = "get : persons", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success request")})
	@RequestMapping(path = "/swapi/persons", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getPersons() {

    	List<Person> result = personRepository.findAll();
		return ResponseEntity.ok(result);
		
	}
    
}
