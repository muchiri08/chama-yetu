package com.muchiri.chamayetu.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class JwtRequestModel {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
