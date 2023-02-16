package com.muchiri.chamayetu.dto;

import com.muchiri.chamayetu.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtUserResponseModel {
    private User user;
    private String jwtToken;
}
