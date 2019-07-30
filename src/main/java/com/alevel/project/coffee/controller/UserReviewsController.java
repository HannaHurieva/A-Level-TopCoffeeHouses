package com.alevel.project.coffee.controller;

import com.alevel.project.coffee.model.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/reviews")
public class UserReviewsController {

    @GetMapping()
    public String getUserReviews(Model model, @AuthenticationPrincipal User user) {
        //model.addAttribute("reviews", reviewService.findAll());
        return "reviews";
    }
}
