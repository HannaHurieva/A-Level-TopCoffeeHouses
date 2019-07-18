package com.alevel.project.coffee.controller;

import com.alevel.project.coffee.model.User;
import com.alevel.project.coffee.model.enums.Role;
import com.alevel.project.coffee.model.enums.Status;
import com.alevel.project.coffee.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserServiceImpl userService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userService.findAll());
        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        model.addAttribute("status", Status.values());
        return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> roles,
            @RequestParam String status,
            @RequestParam("userId") User user) {
        userService.updateUserRoleAndStatus(user, username, roles, status);
        return "redirect:/user";
    }

    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("username", user.getUsername());
        model.addAttribute("firstName", user.getFirstName());
        model.addAttribute("lastName", user.getLastName());
        model.addAttribute("email", user.getEmail());
        return "profile";
    }

    @PostMapping("profile")
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

    @GetMapping("reviews")
    public String getUserReviews(Model model) {
        //model.addAttribute("reviews", reviewService.findAll());
        return "reviews";
    }
}
