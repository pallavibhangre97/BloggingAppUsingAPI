package com.blogging.service.implementation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogging.dto.UserDto;
import com.blogging.entities.User;
import com.blogging.exception.ResourceNotFoundException;
import com.blogging.repository.UserRepo;
import com.blogging.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepo userRepo;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = userRepo.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto, Integer id) {
		Optional<User> user = this.userRepo.findById(id);

		if (user.isEmpty())
			throw new ResourceNotFoundException(id);
		else {
			User updatedUser = user.get();
			updatedUser.setAbout(userDto.getAbout());
			updatedUser.setEmail(userDto.getEmail());
			updatedUser.setName(userDto.getName());
			updatedUser.setPassword(userDto.getPassword());
			User updatedUser2 = userRepo.save(updatedUser);
			return modelMapper.map(updatedUser2, UserDto.class);
		}

	}

	@Override
	public void deleteUser(int id) {
		Optional<User> user = this.userRepo.findById(id);

		if (user.isEmpty())
			throw new ResourceNotFoundException(id);
		else {
			User deletedUser = user.get();
			userRepo.delete(deletedUser);
		}

	}

	@Override
	public UserDto getUserById(int id) {
		Optional<User> user = this.userRepo.findById(id);

		if (user.isEmpty()) {
			System.out.println("id not found");
			throw new ResourceNotFoundException(id);
		} else {
			User getUserInfo = user.get();
			return this.userToDto(getUserInfo);
		}
	}

	public List<UserDto> getAllUser() {
		List<User> userList = userRepo.findAll();
		List<UserDto> userDtos = new ArrayList<>();
		for (User user : userList) {
			userDtos.add(userToDto(user));
		}
		return userDtos;
	}

	public User dtoToUser(UserDto userDto) {
		User user = this.modelMapper.map(userDto, User.class);

		return user;

	}

	public UserDto userToDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		return userDto;

	}
}
