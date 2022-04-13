package com.globant.photoapp.api.users.photoappapiusers.shared;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class FeignErrorDecoder implements ErrorDecoder {

    private final Environment environment;

    public FeignErrorDecoder(Environment environment) {
        this.environment = environment;
    }

    @Override
    public Exception decode(String s, Response response) {
        switch (response.status()) {
            case 400:
                break;
            case 404: {
                if (s.contains("getAlbums")) {
                    return new ResponseStatusException(HttpStatus.valueOf(response.status()), this.environment.getProperty("albums.exceptions.albums-not-found"));
                }
                break;
            }
            default:
                return new Exception(response.reason());
        }
        return null;
    }

}
