package com.alevel.project.coffee.controller;

import com.alevel.project.coffee.model.User;
import com.alevel.project.coffee.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user/profile")
public class UserProfileController {
    private UserServiceImpl userService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("email", user.getEmail());
        return "profile";
    }

    @PostMapping()
    public String updateProfile(
            @AuthenticationPrincipal User user,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String password,
            Model model) {

        boolean isEmailEmpty = StringUtils.isEmpty(email);
        if (isEmailEmpty) {
            model.addAttribute("emailEmptyError", "Email cannot be empty");
            return "profile";
        } else {
            userService.updateUserProfile(user, firstName, lastName, email, password);
            return "redirect:/user/reviews";
        }
    }
}
