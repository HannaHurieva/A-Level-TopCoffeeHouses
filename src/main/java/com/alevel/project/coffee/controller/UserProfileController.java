package com.alevel.project.coffee.controller;

import com.alevel.project.coffee.model.User;
import com.alevel.project.coffee.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user/profile")
public class UserProfileController {
    private UserServiceImpl userService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("{user}")
    public String getProfile(@AuthenticationPrincipal User currentUser, @PathVariable User user, Model model) {
        model.addAttribute("isCurrentUser", currentUser.equals(user));
        model.addAttribute("username", currentUser.getUsername());
        model.addAttribute("firstName", currentUser.getFirstName());
        model.addAttribute("lastName", currentUser.getLastName());
        model.addAttribute("email", currentUser.getEmail());
        return "profile";
    }

    @PostMapping("{user}")
    public String updateProfile(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
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
            userService.updateUserProfile(currentUser, firstName, lastName, email, password);
            return "redirect:/user/reviews/" + user;
        }
    }
}
