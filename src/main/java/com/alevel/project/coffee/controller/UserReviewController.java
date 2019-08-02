package com.alevel.project.coffee.controller;

import com.alevel.project.coffee.model.Review;
import com.alevel.project.coffee.model.User;
import com.alevel.project.coffee.repository.ReviewRepository;
import com.alevel.project.coffee.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/user/reviews")
public class UserReviewController {

    private ReviewRepository reviewRepository;
    private ReviewService reviewService;

    @Autowired
    public void setReviewRepository(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    @PostMapping("/add")
    public String addReview(
            @AuthenticationPrincipal User user,
            @Valid Review review,
            BindingResult bindingResult,
            Model model

    ) throws IOException {
        review.setAuthor(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("reviews", null);
        } else {

            model.addAttribute("reviews", review);

            reviewRepository.save(review);
        }

        Iterable<Review> reviews = reviewRepository.findAll();

        model.addAttribute("reviews", reviews);

        return "userReviews";
    }


    @Transactional
    @GetMapping("/getAll") //это заглушка
    public String getUserReviews(@AuthenticationPrincipal User user, Model model) {
        Set<Review> reviews = user.getReviews();
        model.addAttribute("reviews", reviews);
        return "userReviews";
    }

    @PostMapping("{id}")
    public String updateReview(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long id,
            @RequestParam("id") Review review,
            @RequestParam("text") String text)
            throws IOException {
        if (review.getAuthor().equals(currentUser)) {
            if (!StringUtils.isEmpty(text)) {
                review.setText(text);
            }

            reviewRepository.save(review);
        }

        return "redirect:/user/review/" + id;
    }

    @GetMapping("{id}")
    public String deleteReview(@AuthenticationPrincipal User currentUser,
                               @PathVariable Long id,
                               @RequestParam("id") Review review,
                               RedirectAttributes redirectAttributes) {

        if (review.getAuthor().equals(currentUser)) {
            reviewService.deleteById(id);
        }
        redirectAttributes.addAttribute("message", "Review was deleted");
        return "redirect:/user/reviews";
    }

}
