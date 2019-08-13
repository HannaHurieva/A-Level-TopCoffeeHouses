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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        List<Review> reviews = reviewService.findByAuthor(currentUser);
        model.addAttribute("reviews", reviews);
        model.addAttribute("review", review);
        model.addAttribute("isCurrentUser", currentUser.equals(user));
        return "userReviews";
    }

    @PostMapping("{user}")
    @Transactional
    public String updateReview(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam("id") Review review,
            @RequestParam("text") String text,
            Model model) {
        if (review.getAuthor().equals(currentUser)) {
            model.addAttribute("isCurrentUser", currentUser.getId() == user);
            boolean isEmptyText = StringUtils.isEmpty(text);
            if (isEmptyText) {
                model.addAttribute("textEmptyError", "Message cannot be empty");
                List<Review> reviews = reviewService.findByAuthor(currentUser);
                model.addAttribute("reviews", reviews);
                return "userReviews";
            } else {
                reviewService.update(review, text);
                return "redirect:/user/reviews/" + user;
            }
        }
        return "places";
    }

    @GetMapping("{user}/{id}")
    @Transactional
    public String deleteReview(@AuthenticationPrincipal User currentUser,
                               @PathVariable Long user,
                               @PathVariable Long id,
                               @RequestParam("id") Review review,
                               RedirectAttributes redirectAttributes) {
        if (review.getAuthor().equals(currentUser)) {
            reviewService.deleteById(id);
        }
        redirectAttributes.addAttribute("message", "Review was deleted");
        return "redirect:/user/reviews/" + user;
    }

}
