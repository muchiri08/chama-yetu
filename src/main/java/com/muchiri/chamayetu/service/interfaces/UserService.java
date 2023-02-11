package com.muchiri.chamayetu.service.interfaces;

import com.muchiri.chamayetu.dto.UserDto;
import com.muchiri.chamayetu.exception.UserNotFoundException;

import java.util.List;

public interface UserService {

    UserDto createNewUser(UserDto user);

    List<UserDto> findAllUsers() throws UserNotFoundException;

    UserDto updateUser(Long id, UserDto userDto);

    String deleteUser(Long id);

}
