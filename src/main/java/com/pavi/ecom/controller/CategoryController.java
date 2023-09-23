package com.pavi.ecom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pavi.ecom.Exception.NotFoundException;
import com.pavi.ecom.dto.CategoryDTO;
import com.pavi.ecom.dto.ProductDTO;
import com.pavi.ecom.model.Category;
import com.pavi.ecom.serviceimpl.CategoryServiceImpl;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	  @Autowired	
	  private CategoryServiceImpl categoryServiceImpl;

	
	  @GetMapping("/{name}/{parent}")
	    public Category getProductById(@PathVariable String name,@PathVariable String parent) throws NotFoundException {
	        return categoryServiceImpl.findBynameParent(name, parent);
	    }
}
