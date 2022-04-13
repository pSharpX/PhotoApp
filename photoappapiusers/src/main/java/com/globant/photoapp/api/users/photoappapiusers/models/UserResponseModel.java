package com.globant.photoapp.api.users.photoappapiusers.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseModel {

    private String userId;

    private String firstName;

    private String lastName;

    private String email;

    private List<AlbumResponseModel> albums;

}
