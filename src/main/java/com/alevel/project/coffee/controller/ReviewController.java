package com.alevel.project.coffee.controller;

import com.alevel.project.coffee.model.Review;
import com.alevel.project.coffee.model.User;
import com.alevel.project.coffee.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user/reviews")
public class ReviewController {


    private ReviewRepository reviewRepository;

    @Autowired
    public void setReviewRepository(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    @GetMapping
    public String review(Map<String, Object> model) {
        Iterable<Review> reviews = reviewRepository.findAll();
        model.put("reviews", reviews);
        return "review";
    }





}

