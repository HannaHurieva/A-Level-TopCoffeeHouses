package com.alevel.project.coffee.controller;

import com.alevel.project.coffee.model.Review;
import com.alevel.project.coffee.model.User;
import com.alevel.project.coffee.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;


    @GetMapping
    public String review(Map<String, Object> model) {
        Iterable<Review> reviews = reviewRepository.findAll();
        model.put("reviews", reviews);
        return "review";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
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

        Iterable<Review> reviews = reviewRepository.findAll();

        model.addAttribute("reviews", review);

        return "main";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {
        Iterable<Review> reviews;
        if (filter != null && !filter.isEmpty()) {
            reviews = reviewRepository.findByTag(filter);
        } else {
            reviews = reviewRepository.findAll();
        }


        model.put("review", reviews);
        return "review";
    }

    @GetMapping("/user-reviews/{user}")
    public String userReviews(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model,
            @RequestParam(required = false) Review review
    ) {
        Set<Review> reviews = user.getReviews();

        model.addAttribute("reviews", reviews);
        model.addAttribute("review", review);
        model.addAttribute("isCurrentUser", currentUser.equals(user));

        return "userReviews";
    }

    @PostMapping("/user-reviews/{user}")
    public String updateReview(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam("id") Review review,
            @RequestParam("text") String text,
            @RequestParam("tag") String tag)
            throws IOException {
        if (review.getAuthor().equals(currentUser)) {
            if (!StringUtils.isEmpty(text)) {
                review.setText(text);
            }

            if (!StringUtils.isEmpty(tag)) {
                review.setTag(tag);
            }

            reviewRepository.save(review);
        }

        return "redirect:/user-reviews/" + user;
    }
}
