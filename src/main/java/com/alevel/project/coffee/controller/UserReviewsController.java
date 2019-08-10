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
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/user/reviews")
public class UserReviewsController {

    private ReviewRepository reviewRepository;
    private ReviewService reviewService;

   /* @Autowired
    public void setReviewRepository(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }
*/
   @Autowired
   public void setReviewService(ReviewService reviewService){
       this.reviewService = reviewService;
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
            model.addAttribute("reviews", review);
        } else {

            model.addAttribute("reviews", null);
            reviewService.add(review,user);
           // reviewRepository.save(review);
        }

       // Iterable<Review> reviews = reviewRepository.findAll();
        Iterable<Review> reviews = reviewService.findAll();

        model.addAttribute("reviews", reviews);

        return "userReviews";
    }


    @Transactional
    @GetMapping
    public String getUserReviews(@AuthenticationPrincipal User user, Model model) {
        List<Review> reviews = reviewService.findByAuthor(user);
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

        return "redirect:/user/reviews/" + id;
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
