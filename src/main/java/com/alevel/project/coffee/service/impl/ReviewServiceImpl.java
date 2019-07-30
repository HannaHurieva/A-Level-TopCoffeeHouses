package com.alevel.project.coffee.service.impl;

import com.alevel.project.coffee.model.Review;
import com.alevel.project.coffee.model.User;
import com.alevel.project.coffee.repository.ReviewRepository;
import com.alevel.project.coffee.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Override
    public Iterable<Review> findAll() {
        return reviewRepository.findAll();
    }

    @Override
    public void deleteById(long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    public String findByAuthor(User author) {
        return null;
    }

    @Override
    public void update(Long id, Review review) {

    }

    @Override
    public Long add(Review review) {
        return reviewRepository.save(review).getId();
    }
}
