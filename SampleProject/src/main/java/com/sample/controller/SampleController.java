package com.sample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sample.repository.Memo;
import com.sample.repository.MemoRepository;

@RestController
public class SampleController {
	
	@Autowired
	MemoRepository memoRepository;

	@RequestMapping(path = "/api/menus", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getPersons() {
    	List<Memo> result = memoRepository.findAll();
		return ResponseEntity.ok(result);		
	}
    
}
