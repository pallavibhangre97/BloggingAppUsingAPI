package com.blogging.dto;

import jakarta.validation.constraints.NotEmpty;

public class CategoryDto {
	private Integer categoryId;
	@NotEmpty(message = "category title should not be empty")
	private String categoryTitle;
	@NotEmpty(message = "category Desciption should not be empty")
	private String categoryDesciption;

	public CategoryDto() {

	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}

	public String getCategoryDesciption() {
		return categoryDesciption;
	}

	public void setCategoryDesciption(String categoryDesciption) {
		this.categoryDesciption = categoryDesciption;
	}
}
