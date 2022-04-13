package com.globant.photoapp.api.users.photoappapiusers.repositories;

import com.globant.photoapp.api.users.photoappapiusers.data.UserEntity;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
 
    UserEntity findByEmail(String email);

    UserEntity findByUserId(String userId);
    
}
