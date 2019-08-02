package com.alevel.project.coffee.service;

import com.alevel.project.coffee.model.Review;
import com.alevel.project.coffee.model.User;

import java.util.List;

public interface ReviewService {

    Iterable<Review> findAll();

    void deleteById(long id);

    List<Review> findByAuthor(User author);

    String update(Review review, String text);

    void add(Review review, User author);




}
