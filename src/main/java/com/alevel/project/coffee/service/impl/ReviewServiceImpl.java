package com.alevel.project.coffee.service.impl;

import com.alevel.project.coffee.model.Place;
import com.alevel.project.coffee.model.Review;
import com.alevel.project.coffee.model.User;
import com.alevel.project.coffee.repository.ReviewRepo;
import com.alevel.project.coffee.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("reviewService")
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepo reviewRepo;

    @Autowired
    public ReviewServiceImpl(ReviewRepo reviewRepo) {
        this.reviewRepo = reviewRepo;
    }

    @Override
    public void createNewReview(Place place, Review review, User author) {
        review.setPlace(place);
        review.setAuthor(author);
        reviewRepo.save(review);
    }

    @Override
    public List<Review> findAll() {
        return reviewRepo.findAll();
    }

    @Override
    public List<Review> findByAuthor(User author) {
        return reviewRepo.findByAuthor(author);
    }

    @Override
    public List<Review> findByPlace(Place place) {
        return reviewRepo.findByPlace(place);
    }

    @Override
    public void update(Review review, String text) {
        if (!(review.getText().equals(text))) {
            review.setText(text);
        }
        reviewRepo.save(review);
    }

    @Override
    public void deleteById(long id) {
        reviewRepo.deleteById(id);
    }
}
