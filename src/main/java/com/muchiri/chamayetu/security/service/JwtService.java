package com.muchiri.chamayetu.security.service;

import com.muchiri.chamayetu.dto.JwtRequestModel;
import com.muchiri.chamayetu.dto.JwtUserResponseModel;
import com.muchiri.chamayetu.entity.User;
import com.muchiri.chamayetu.repository.UserRepository;
import com.muchiri.chamayetu.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class JwtService {
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final CustomUDS userDetailsService;

    public JwtUserResponseModel createJwtToken(JwtRequestModel model){
        String username = model.getUsername();
        String password = model.getPassword();

        authenticate(username, password);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        String newGeneratedToken = jwtUtil.generateToken(userDetails);

        User user = userRepository.findByUsername(username).get();

        return new JwtUserResponseModel(user, newGeneratedToken);
    }

    private void authenticate(String username, String password){
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e){
            //TODO: handle by creating InvalidInputException
        } catch (DisabledException e){

        }
    }
}
