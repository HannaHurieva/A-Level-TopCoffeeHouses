package com.alevel.project.coffee.service;

import com.alevel.project.coffee.model.Review;
import com.alevel.project.coffee.model.User;

public interface ReviewService {

    Iterable<Review> findAll();

    void deleteById(long id);

    String findByAuthor(User author);

    void update(Long id, Review review);

    Long add(Review review);




}
