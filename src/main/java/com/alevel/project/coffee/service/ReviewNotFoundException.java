package com.alevel.project.coffee.service;

import com.alevel.project.coffee.model.Review;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ReviewNotFoundException extends RuntimeException {
    public ReviewNotFoundException(Review review) {
        super("The review with id = " + review.getId() + " not found");
    }
}
