package com.blogging.service;

import java.util.List;

import com.blogging.dto.UserDto;

public interface UserService {

	UserDto createUser(UserDto userDto);

	UserDto updateUser(UserDto userDto, Integer id);

	void deleteUser(int id);

	UserDto getUserById(int id);

	List<UserDto> getAllUser();

}
