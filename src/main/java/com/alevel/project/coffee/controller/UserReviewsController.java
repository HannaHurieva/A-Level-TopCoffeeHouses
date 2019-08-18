package com.alevel.project.coffee.controller;

import com.alevel.project.coffee.model.Review;
import com.alevel.project.coffee.model.User;
import com.alevel.project.coffee.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequestMapping("/user/reviews")
public class UserReviewsController {

    private ReviewService reviewService;

    @Autowired
    public void setReviewService(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("{user}")
    @Transactional
    public String getUserReviews(@AuthenticationPrincipal User currentUser,
                                 @PathVariable User user, Model model,
                                 @RequestParam(required = false) Review review) {
        List<Review> reviews = reviewService.findByAuthor(user);
        model.addAttribute("reviews", reviews);
        model.addAttribute("review", review);
        model.addAttribute("isCurrentUser", currentUser.getId() == user.getId());
        return "userReviews";
    }

    @PostMapping("{user}")
    @Transactional
    public String updateReview(
            @AuthenticationPrincipal User currentUser,
            @PathVariable(name = "user") Long userId,
            @RequestParam("id") Review review,
            @RequestParam("text") String text,
            Model model) {

        if (currentUser.getId() == review.getAuthor().getId()) {
            model.addAttribute("isCurrentUser", "true");
            boolean isEmptyText = StringUtils.isEmpty(text);
            if (!isEmptyText) {
                reviewService.update(review, text);
            }
        }
        return "redirect:/user/reviews/" + userId;
    }

    @GetMapping("{user}/delete")
    @Transactional
    public String resultDeletingReview(@PathVariable(name = "user") Long userId, Long id, Model model) {
        model.addAttribute("message", "The review with id = " + id + " was successful deleted");
        return "requestOk";
    }

    @PostMapping("{user}/delete/{id}")
    @Transactional
    public String deleteReview(@AuthenticationPrincipal User currentUser,
                               @PathVariable(name = "user") Long userId,
                               @PathVariable(name = "id") Review review,
                               Model model) {
        if (currentUser.getId() == userId) {
            reviewService.deleteReview(review);
            return resultDeletingReview(userId, review.getId(), model);
        }
        return "redirect:/user/reviews/" + currentUser.getId();
    }

}
