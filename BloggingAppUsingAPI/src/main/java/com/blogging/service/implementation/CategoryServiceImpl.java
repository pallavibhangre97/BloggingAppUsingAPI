package com.blogging.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogging.dto.CategoryDto;
import com.blogging.entities.Category;
import com.blogging.exception.ResourceNotFoundException;
import com.blogging.repository.CategoryRepo;
import com.blogging.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	CategoryRepo categoryRepo;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public CategoryDto createCategory(CategoryDto categoryDto) {
		Category category = modelMapper.map(categoryDto, Category.class);

		Category savedCategory = categoryRepo.save(category);
		return modelMapper.map(savedCategory, CategoryDto.class);

	}

	@Override
	public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
		Optional<Category> category1 = categoryRepo.findById(id);
		if (category1.isEmpty())
			throw new ResourceNotFoundException(id);
		else {
			Category category = category1.get();
			category.setCategoryDesciption(categoryDto.getCategoryDesciption());
			category.setCategoryTitle(categoryDto.getCategoryTitle());
			Category savedCategory = categoryRepo.save(category);
			return modelMapper.map(savedCategory, CategoryDto.class);
		}

	}

	@Override
	public void deleteCategory(int id) {
		Optional<Category> category = categoryRepo.findById(id);
		if (category.isEmpty())
			throw new ResourceNotFoundException(id);
		else {
			Category category1 = category.get();
			categoryRepo.delete(category1);
		}
	}

	@Override
	public CategoryDto getCategoryById(int id) {
		Optional<Category> category = categoryRepo.findById(id);
		if (category.isEmpty())
			throw new ResourceNotFoundException(id);
		else {
			Category category1 = category.get();
			return modelMapper.map(category1, CategoryDto.class);
		}

	}

	@Override
	public List<CategoryDto> getAllCategory() {
		List<Category> categories = categoryRepo.findAll();
		List<CategoryDto> categoryDtos = new ArrayList<>();
		for (Category c : categories) {
			categoryDtos.add(modelMapper.map(c, CategoryDto.class));
		}
		return categoryDtos;
	}

}
