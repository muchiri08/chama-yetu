package com.muchiri.chamayetu.dto;

import com.muchiri.chamayetu.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long id;
    @NotBlank(message = "cannot be empty or cannot contain only whitespaces")
    private String firstName;
    @NotBlank(message = "cannot be empty or cannot contain only whitespaces")
    private String lastName;
    @NotBlank(message = "cannot be empty or contain only white spaces")
    private String phoneNumber;
    @NotBlank(message = "email cannot be empty or cannot contain only whitespaces")
    @Email(message = "invalid email address")
    private String emailAddress;
    @NotNull(message = "cannot be null")
    @Past
    private LocalDate dateOfBirth;
    @NotNull(message = "cannot be null")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @NotBlank(message = "occupation cannot be empty or cannot contain only whitespaces")
    private String occupation;
    @NotBlank(message = "cannot be empty or cannot contain only whitespaces")
    private String location;

}
