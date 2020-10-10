package com.avijit.agencyapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {

    @Size(min = 2, max = 50, message = "first name must be between 2 and 20 characters")
    private String firstName;

    @Size(min = 2, max = 50, message = "first name must be between 2 and 20 characters")
    private String lastName;

    @NotBlank
    private String email;

    @Size(min = 2, max = 50, message = "password must be greater than 6 characters")
    private String password;

    @NotBlank
    private String confirmPassword;
}
