package com.globant.photoapp.api.users.photoappapiusers.services;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class AlbumsServiceFallbackFactory implements FallbackFactory<AlbumServiceClient> {

    @Override
    public AlbumServiceClient create(Throwable cause) {
        return new AlbumsServiceFallback(cause);
    }

}
