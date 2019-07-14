package com.alevel.project.coffee.controller;

import com.alevel.project.coffee.model.User;
import com.alevel.project.coffee.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private UserServiceImpl userService;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(@RequestParam("confirmPassword") String confirmPassword,
                                      @Valid User user, BindingResult bindingResult,
                                      Model model) {

        boolean isConfirmEmpty = StringUtils.isEmpty(confirmPassword);
        if (isConfirmEmpty) {
            model.addAttribute("confirmPasswordError", "Password confirmation cannot be empty");
        }

        boolean isPasswordDifferent = user.getPassword() != null && !user.getPassword().equals(confirmPassword);
        if (isPasswordDifferent) {
            model.addAttribute("passwordError", "Passwords are different!");
        }

        if (isConfirmEmpty || isPasswordDifferent || bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);

            return "registration";
        }

        if (!userService.addNewUser(user)) {
            model.addAttribute("usernameError", "User with such login or email exists!");
            return "registration";
        }

        return "redirect:/login";
    }
}
