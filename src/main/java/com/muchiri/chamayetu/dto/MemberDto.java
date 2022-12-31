package com.muchiri.chamayetu.dto;

import com.muchiri.chamayetu.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {
    private Long id;
    @NotBlank(message = "firstName cannot be empty or cannot contain only whitespaces")
    private String firstName;
    @NotBlank(message = "last cannot be empty or cannot contain only whitespaces")
    private String lastName;
    @NotEmpty
    private String phoneNumber;
    @NotBlank(message = "email cannot be empty or cannot contain only whitespaces")
    @Email(message = "invalid email address")
    private String emailAddress;
    @NotBlank(message = "date of birth cannot be empty or cannot contain only whitespaces")
    private LocalDate dateOfBirth;
    @NotBlank(message = "gender cannot be empty or cannot contain only whitespaces")
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @NotBlank(message = "occupation cannot be empty or cannot contain only whitespaces")
    private String occupation;
    @NotBlank(message = "location cannot be empty or cannot contain only whitespaces")
    private String location;

}
