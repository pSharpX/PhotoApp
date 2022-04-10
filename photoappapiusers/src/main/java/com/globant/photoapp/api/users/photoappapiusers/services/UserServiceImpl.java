package com.globant.photoapp.api.users.photoappapiusers.services;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import com.globant.photoapp.api.users.photoappapiusers.data.UserEntity;
import com.globant.photoapp.api.users.photoappapiusers.repositories.UserRepository;
import com.globant.photoapp.api.users.photoappapiusers.shared.UserDto;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDto createUser(UserDto userDetails) {
        userDetails.setUserId(UUID.randomUUID().toString());
        userDetails.setEncryptedPassword(this.bCryptPasswordEncoder.encode(userDetails.getPassword()));
        
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        
        UserEntity userEntity = modelMapper.map(userDetails, UserEntity.class);

        this.userRepository.save(userEntity);

        UserDto response = modelMapper.map(userEntity, UserDto.class);
        return response;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return Optional.ofNullable(this.userRepository.findByEmail(username))
            .map(userDetails -> new User(userDetails.getEmail(), userDetails.getEncryptedPassword(), true, true, true, true, Collections.emptyList()))
            .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return Optional.ofNullable(this.userRepository.findByEmail(email))
            .map(userDetails -> modelMapper.map(userDetails, UserDto.class))
            .orElseThrow(() -> new UsernameNotFoundException(email));
    }
    
}
