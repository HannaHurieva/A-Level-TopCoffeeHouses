package com.alevel.project.coffee.controller;

import com.alevel.project.coffee.model.Place;
import com.alevel.project.coffee.model.Review;
import com.alevel.project.coffee.model.User;
import com.alevel.project.coffee.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/places/reviews")
public class ReviewController {
    private ReviewRepository reviewRepository;

    @Autowired
    public void setReviewRepository(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @GetMapping("{place}")
    public String getReviewsByPlace(@PathVariable Place place, Model model) {
        List<Review> reviewsByPlace = reviewRepository.findByPlace(place);
        model.addAttribute("reviews", reviewsByPlace);
        return "reviews";
    }

    @PostMapping("{place}")
    public String addReview(
            @AuthenticationPrincipal User user,
            @PathVariable Place place,
            @Valid Review review,
            BindingResult bindingResult,
            Model model

    ) throws IOException {
        review.setAuthor(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            model.addAttribute("review", review);
        } else {
            model.addAttribute("review", null);
            reviewRepository.save(review);
        }

        List<Review> reviewsByPlace = reviewRepository.findByPlace(place);
        model.addAttribute("reviews", reviewsByPlace);
        return "reviews";
    }

}
