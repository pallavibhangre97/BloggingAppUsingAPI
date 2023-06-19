package com.blogging.dto;

import java.util.Date;

public class PostDto {
	private String title;
	private String content;
	private String imageName;
	private Date addedDate;
	private UserDto user;
	private CategoryDto category;

	public PostDto() {

	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public UserDto getUserDto() {
		return user;
	}

	public void setUser(UserDto userDto) {
		this.user = userDto;
	}

	public CategoryDto getCategoryDto() {
		return category;
	}

	public void setCategory(CategoryDto categoryDto) {
		this.category = categoryDto;
	}

}
