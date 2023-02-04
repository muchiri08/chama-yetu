package com.muchiri.chamayetu.controller;

import com.muchiri.chamayetu.dto.UserDto;
import com.muchiri.chamayetu.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createNewUser(@RequestBody UserDto userDto){
        UserDto response = userService.createNewUser(userDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
