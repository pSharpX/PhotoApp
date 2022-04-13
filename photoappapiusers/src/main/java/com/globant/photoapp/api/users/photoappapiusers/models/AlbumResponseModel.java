package com.globant.photoapp.api.users.photoappapiusers.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AlbumResponseModel {

    private String albumId;

    private String userId;

    private String name;

    private String description;

}
