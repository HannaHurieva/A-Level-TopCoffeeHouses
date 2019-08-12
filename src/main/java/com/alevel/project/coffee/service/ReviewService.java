package com.alevel.project.coffee.service;

import com.alevel.project.coffee.model.Place;
import com.alevel.project.coffee.model.Review;
import com.alevel.project.coffee.model.User;

import java.util.List;

public interface ReviewService {
    void createNewReview(Review review, User author);

    void save(Review review);

    Iterable<Review> findAll();

    List<Review> findByAuthor(User author);

    List<Review> findByPlace(Place place);

    String update(Review review, String text);

    void deleteById(long id);

}
