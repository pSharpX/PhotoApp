package com.globant.photoapp.api.users.photoappapiusers.services;

import com.globant.photoapp.api.users.photoappapiusers.models.AlbumResponseModel;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class AlbumsServiceFallback implements AlbumServiceClient {

    private final Logger logger = LoggerFactory.getLogger(AlbumsServiceFallbackFactory.class);

    private final Throwable cause;

    public AlbumsServiceFallback(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public List<AlbumResponseModel> getAlbums(String id) {
        if (this.cause instanceof FeignException && ((FeignException) cause).status() == 404) {
            logger.error("404 error took place when getAlbums was called with userId " + id + ". Error message: " + cause.getLocalizedMessage());
        } else {
            logger.error("Other error took place: " + cause.getLocalizedMessage());
        }
        return new ArrayList<>();
    }

}
