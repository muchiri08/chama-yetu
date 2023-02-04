package com.muchiri.chamayetu.service.implementation;

import com.muchiri.chamayetu.dto.UserDto;
import com.muchiri.chamayetu.entity.User;
import com.muchiri.chamayetu.repository.UserRepository;
import com.muchiri.chamayetu.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    @Override
    public UserDto createNewUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        userRepository.save(user);
        UserDto response = modelMapper.map(user, UserDto.class);

        return response;
    }
}
