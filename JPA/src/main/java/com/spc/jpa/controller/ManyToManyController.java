package com.spc.jpa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spc.jpa.domain.manytomany.Category;
import com.spc.jpa.domain.manytomany.CategoryRepository;
import com.spc.jpa.domain.manytomany.Product;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiResponse;
import com.wordnik.swagger.annotations.ApiResponses;

@RestController
public class ManyToManyController {
	@Autowired
	CategoryRepository categoryRepository;

    @ApiOperation(value = "categories", notes = "get : categories", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "success request")})
	@RequestMapping(path = "/swapi/categories", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<?> getPersons() {

    	List<Category> result = categoryRepository.findAll();
    	
    	List<Product> product = result.get(0).getProducts();
    	
		return ResponseEntity.ok(result);
		
	}
}
