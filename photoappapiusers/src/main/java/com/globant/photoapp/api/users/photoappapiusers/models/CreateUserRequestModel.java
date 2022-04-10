package com.globant.photoapp.api.users.photoappapiusers.models;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class CreateUserRequestModel {

    @NotNull(message = "FirstName cannot be null")
    @Size(min = 2, message = "FirstName must not be less than two characters")
    private String firstName;

    @NotNull(message = "LastName cannot be null")
    @Size(min = 2, message = "LastName must not be less than two characters")
    private String lastName;

    @NotNull(message = "Password cannot be null")
    @Size(min = 8, max = 16, message = "Password must not be less than 16 characters")
    private String password;

    @NotNull(message = "Email cannot be null")
    @Email
    private String email;
    
}
