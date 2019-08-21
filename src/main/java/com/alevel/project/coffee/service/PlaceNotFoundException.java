package com.alevel.project.coffee.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PlaceNotFoundException extends RuntimeException {
    public PlaceNotFoundException(Long id) {
        super("The Place with id = " + id + " not found");
    }
}
