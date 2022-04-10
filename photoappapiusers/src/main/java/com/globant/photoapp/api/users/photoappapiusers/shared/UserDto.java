package com.globant.photoapp.api.users.photoappapiusers.shared;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserDto implements Serializable {

    private String firstName;

    private String lastName;

    private String password;

    private String encryptedPassword;

    private String email;

    private String userId;

}
