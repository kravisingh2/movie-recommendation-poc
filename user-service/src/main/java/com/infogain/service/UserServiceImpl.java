package com.infogain.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infogain.dto.UserDTO;
import com.infogain.entity.UserEntity;
import com.infogain.exception.UserNotFoundException;
import com.infogain.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDTO createUser(UserDTO userDto) {
		UserEntity userEntity = new UserEntity();
    	userEntity.setEmail(userDto.getEmail());
    	userEntity.setName(userDto.getName());
    	userEntity.setMobileNo(userDto.getMobileNo());
    	userEntity.setPassword(userEntity.getPassword());
    	userEntity = userRepository.saveAndFlush(userEntity);
    	userDto.setId(userEntity.getId());
    	userDto.setPassword(null);
    	return userDto;
	}

	@Override
	public List<UserDTO> getAllUsers() {
		List<UserEntity> userEntity = userRepository.findAll();
		List<UserDTO> userDtoList = new ArrayList<>();
		userEntity.forEach(user -> {
			UserDTO userDto = new UserDTO();
			userDto.setId(user.getId());
			userDto.setEmail(user.getEmail());
			userDto.setMobileNo(user.getMobileNo());
			userDto.setName(user.getName());
			userDtoList.add(userDto);
		});
		return userDtoList;
	}

	@Override
	public UserDTO getUser(Long userId) {
		Optional<UserEntity> userEntityOpt = userRepository.findById(userId);
		if(!userEntityOpt.isPresent()) {
			throw new UserNotFoundException();
		}
		UserEntity userEntity = userEntityOpt.get();
		UserDTO userDto = new UserDTO();
		userDto.setId(userEntity.getId());
		userDto.setEmail(userEntity.getEmail());
		userDto.setMobileNo(userEntity.getMobileNo());
		userDto.setName(userEntity.getName());
		return userDto;
	}

}
