package com.alevel.project.coffee.service;

import com.alevel.project.coffee.model.Place;
import com.alevel.project.coffee.model.Review;
import com.alevel.project.coffee.model.User;

import java.util.List;

public interface ReviewService {
    void createNewReview(Place place, Review review, User author);

    List<Review> findAll();

    List<Review> findByAuthor(User author);

    List<Review> findByPlace(Place place);

    void update(Review review, String text);

    void deleteById(long id);

}
