package com.spc.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spc.jpa.domain.onetomany.Member;
import com.spc.jpa.domain.onetomany.MemberRepository;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@RestController
public class OneToManyController {
	@Autowired
	MemberRepository memberRepository;

    @ApiOperation(value = "members", notes = "get : members", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success request")})
	@RequestMapping(path = "/swapi/members", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getPersons() {

    	List<Member> result = memberRepository.findAll();
    	
		return ResponseEntity.ok(result);
	}
}
