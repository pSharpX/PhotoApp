package com.globant.photoapp.api.users.photoappapiusers.resources;

import javax.validation.Valid;

import com.globant.photoapp.api.users.photoappapiusers.models.CreateUserRequestModel;
import com.globant.photoapp.api.users.photoappapiusers.models.CreateUserResponseModel;
import com.globant.photoapp.api.users.photoappapiusers.models.UserResponseModel;
import com.globant.photoapp.api.users.photoappapiusers.services.UserService;
import com.globant.photoapp.api.users.photoappapiusers.shared.UserDto;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {

    private final Environment env;

    private final UserService userService;

    public UserController(Environment env, UserService userService) {
        this.env = env;
        this.userService = userService;
    }

    @GetMapping("/status/check")
    public String status(){
        return "Working on port " + this.env.getProperty("local.server.port");
    }

    @PostMapping
    public ResponseEntity<CreateUserResponseModel> createUser(@Valid @RequestBody CreateUserRequestModel userDetails){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = modelMapper.map(userDetails, UserDto.class);
        this.userService.createUser(userDto);

        CreateUserResponseModel response = modelMapper.map(userDto, CreateUserResponseModel.class);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseModel> getUser(@PathVariable String userId) {
        UserDto userDto = this.userService.getUserByUserId(userId);
        UserResponseModel response = new ModelMapper().map(userDto, UserResponseModel.class);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

} 