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
import java.io.IOException;
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
        //Set<Review> reviews = currentUser.getReviews();
        model.addAttribute("reviews", reviews);
        //model.addAttribute("review", review);
        model.addAttribute("isCurrentUser", currentUser.equals(user));
        return "userReviews";
    }

    @PostMapping("{user}")
    @Transactional
    public String updateReview(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam("id") Review review,
            @RequestParam("text") String text)
            throws IOException {
        if (review.getAuthor().equals(currentUser)) {
            if (!StringUtils.isEmpty(text)) {
                review.setText(text);
            }
            reviewService.save(review);
        }
        return "redirect:/user/reviews/" + user;
    }

    @GetMapping("{id)/delete")
    @Transactional
    public String deleteReview(@AuthenticationPrincipal User currentUser,
                               @PathVariable Long id,
                               @RequestParam("id") Review review,
                               RedirectAttributes redirectAttributes) {
        if (review.getAuthor().equals(currentUser)) {
            reviewService.deleteById(id);
        }
        redirectAttributes.addAttribute("message", "Review was deleted");
        return "redirect:/user/reviews/" + id;
    }

}
