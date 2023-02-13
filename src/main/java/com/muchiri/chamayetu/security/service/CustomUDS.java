package com.muchiri.chamayetu.security.service;

import com.muchiri.chamayetu.entity.User;
import com.muchiri.chamayetu.exception.UserNotFoundException;
import com.muchiri.chamayetu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomUDS implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        try {
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new UserNotFoundException("User not found!")
            );

            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));

        } catch (UserNotFoundException e) {
            log.error("User not found", e);
            return null;
        }
    }

    private Set getAuthorities(User user) {
        Set authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));

        return authorities;
    }
}
