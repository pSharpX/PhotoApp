package com.globant.photoapp.api.users.photoappapiusers.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateUserResponseModel {

    private String firstName;

    private String lastName;

    private String email;

    private String userId;
    
}
