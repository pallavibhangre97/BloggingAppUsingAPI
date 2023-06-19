package com.blogging.service;

import java.util.List;

import com.blogging.dto.CategoryDto;

public interface CategoryService {
	CategoryDto createCategory(CategoryDto categoryDto);

	CategoryDto updateCategory(CategoryDto categoryDto, Integer id);

	void deleteCategory(int id);

	CategoryDto getCategoryById(int id);

	List<CategoryDto> getAllCategory();

}
