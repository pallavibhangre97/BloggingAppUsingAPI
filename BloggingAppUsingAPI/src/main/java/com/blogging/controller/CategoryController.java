package com.blogging.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogging.dto.CategoryDto;
import com.blogging.payload.ApiResponse;
import com.blogging.service.implementation.CategoryServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cat")
public class CategoryController {

	@Autowired
	CategoryServiceImpl categoryServiceImpl;

	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto categoryDto2 = categoryServiceImpl.createCategory(categoryDto);
		return new ResponseEntity<CategoryDto>(categoryDto2, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryDto> getSingleCategory(@PathVariable int id) {
		CategoryDto dto = categoryServiceImpl.getCategoryById(id);
		return new ResponseEntity<CategoryDto>(dto, HttpStatus.OK);

	}

	@GetMapping("/getallcategory")
	public ResponseEntity<List<CategoryDto>> getAllCategory() {
		List<CategoryDto> categoryDtos = categoryServiceImpl.getAllCategory();
		return new ResponseEntity<List<CategoryDto>>(categoryDtos, HttpStatus.OK);
	}

	@DeleteMapping("/deletecategory/{id}")
	public ResponseEntity<?> delete(@PathVariable int id) {
		categoryServiceImpl.deleteCategory(id);
		return new ResponseEntity<>(new ApiResponse("Category Deleted Successfully", true), HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public CategoryDto updateCategory(@Valid@RequestBody CategoryDto categoryDto, @PathVariable int id) {
		return categoryServiceImpl.updateCategory(categoryDto, id);

	}
}
