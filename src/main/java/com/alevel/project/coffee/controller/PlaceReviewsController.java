package com.alevel.project.coffee.controller;

import com.alevel.project.coffee.model.Place;
import com.alevel.project.coffee.model.Review;
import com.alevel.project.coffee.model.User;
import com.alevel.project.coffee.service.impl.ReviewServiceImpl;
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
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/places/reviews")
public class PlaceReviewsController {
    private ReviewServiceImpl reviewService;

    @Autowired
    public void setReviewRepo(ReviewServiceImpl reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("{place}")
    public String getReviewsByPlace(@PathVariable Place place, Model model) {
        List<Review> reviewsByPlace = reviewService.findByPlace(place);
        model.addAttribute("reviews", reviewsByPlace);
        model.addAttribute("place", place);
        return "reviews";
    }

    @GetMapping("{place}/create")
    public String getFormReview(@PathVariable Place place) {
        return "reviewCreate";
    }

    @PostMapping("{place}/create")
    public String createNewReview(
            @AuthenticationPrincipal User user, @PathVariable Place place,
            @Valid Review review, BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errorsMap);
            return "reviewCreate";
        }
        reviewService.createNewReview(place, review, user);
        return getReviewsByPlace(place, model);
    }

}
