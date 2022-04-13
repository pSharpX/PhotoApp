package com.globant.photoapp.api.users.photoappapiusers.services;

import com.globant.photoapp.api.users.photoappapiusers.shared.UserDto;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    UserDto createUser(UserDto userDetails);

    UserDto getUserDetailsByEmail(String email);

    UserDto getUserByUserId(String userId);
}
