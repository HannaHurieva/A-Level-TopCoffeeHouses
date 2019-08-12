package com.alevel.project.coffee.service.impl;

import com.alevel.project.coffee.model.Place;
import com.alevel.project.coffee.model.Review;
import com.alevel.project.coffee.model.User;
import com.alevel.project.coffee.repository.ReviewRepository;
import com.alevel.project.coffee.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    @Override
    public void createNewReview(Review review, User author) {
        review.setAuthor(author);
        reviewRepository.save(review);
    }

    @Override
    public void save(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public Iterable<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> findByAuthor(User author) {
        return reviewRepository.findByAuthor(author);
    }

    @Override
    public List<Review> findByPlace(Place place) {
        return reviewRepository.findByPlace(place);
    }

    @Override
    public String update(Review review, String text) {
        return review.getText();
    }

    @Override
    public void deleteById(long id) {
        reviewRepository.deleteById(id);
    }
}
