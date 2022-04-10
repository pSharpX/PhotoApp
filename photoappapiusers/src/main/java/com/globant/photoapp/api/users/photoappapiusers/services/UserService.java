package com.globant.photoapp.api.users.photoappapiusers.services;

import com.globant.photoapp.api.users.photoappapiusers.shared.UserDto;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    public UserDto createUser(UserDto userDetails);

    public UserDto getUserDetailsByEmail(String email);
    
}
