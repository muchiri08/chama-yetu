package com.muchiri.chamayetu.service.implementation;

import com.muchiri.chamayetu.repository.UserRepository;
import com.muchiri.chamayetu.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

}
