package com.springproject.blog.services.impl;

import java.util.List;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springproject.blog.entities.User;
import com.springproject.blog.exceptions.*;
import com.springproject.blog.payloads.UserDto;
import com.springproject.blog.repositories.UserRepo;
import com.springproject.blog.services.UserService;
@Service
public class UserServiceimpl implements UserService {
	@Autowired
	private UserRepo  userRepo;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public UserDto createUser(UserDto userDto) {
		User user = this.dtoToUser(userDto);
		User savedUser = this.userRepo.save(user);
		
		return this.usertoDto(savedUser);
	}

	@Override
	public UserDto updateUser(UserDto userDto,Integer userId) {
			User user =this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," id ",userId));

			user.setName(userDto.getName());
			user.setEmail(userDto.getEmail());
	        user.setPassword(userDto.getPassword());
	        user.setAbout(userDto.getAbout());
	        User updatedUser = this.userRepo.save(user);
	        
	        UserDto userdto1 = this.usertoDto(updatedUser);
	        return userdto1;
	        
 	}

	@Override
	public UserDto getUserById(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," id ",userId));
		return this.usertoDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users = this.userRepo.findAll();
		List<UserDto> userDtos = users.stream().map(user ->this.usertoDto(user)).collect(Collectors.toList());
		return userDtos;
		
	}

	@Override
	public void deleteUser(Integer userId) {
		User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," id ",userId));
		this.userRepo.delete(user);
	}
	private User dtoToUser(UserDto userDto ) {
		User user = this.modelMapper.map(userDto,User.class);
		
        return user;
	}
	private UserDto usertoDto(User user) {
		UserDto userDto = this.modelMapper.map(user, UserDto.class);
		
		
		return userDto;
		
	}

	
}
