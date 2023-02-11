package com.muchiri.chamayetu.controller;

import com.muchiri.chamayetu.dto.UserDto;
import com.muchiri.chamayetu.exception.UserNotFoundException;
import com.muchiri.chamayetu.service.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserDto> createNewUser(@RequestBody UserDto userDto) {
        UserDto response = userService.createNewUser(userDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() throws UserNotFoundException {
        List<UserDto> response = userService.findAllUsers();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        UserDto response = userService.updateUser(id, userDto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        String response = userService.deleteUser(id);

        return ResponseEntity.ok(response);
    }

}
