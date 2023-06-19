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

import com.blogging.dto.UserDto;
import com.blogging.payload.ApiResponse;
import com.blogging.service.implementation.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserServiceImpl userServiceImpl;

	// Post -create user
	@PostMapping("/")
	// ResponseEntity is used to display http status code on postman
	public ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto) {
		UserDto userDto2 = userServiceImpl.createUser(userDto);
		return new ResponseEntity<UserDto>(userDto2, HttpStatus.CREATED);
	}

	// Get -get user info
	@GetMapping("/{id}")
	public UserDto getUserInfo(@PathVariable("id") int id) {
		return userServiceImpl.getUserById(id);
	}

	@GetMapping("/alluser")
	public ResponseEntity<List<UserDto>> getAllUser() {

		return ResponseEntity.ok(userServiceImpl.getAllUser());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") int id) {
		userServiceImpl.deleteUser(id);
		return new ResponseEntity<>(new ApiResponse("User deleted successfully", true), HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public UserDto updateUser(@Valid @RequestBody UserDto userDto, @PathVariable("id") int id) {
		return userServiceImpl.updateUser(userDto, id);

	}

}
