package com.alevel.project.coffee.controller;

import com.alevel.project.coffee.model.User;
import com.alevel.project.coffee.model.dto.CaptchaResponseDto;
import com.alevel.project.coffee.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Collections;
import java.util.Map;

@Controller
@RequestMapping("/registration")
public class RegistrationController {
    private final static String CAPTCHA_URL = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s";

    private UserServiceImpl userService;
    private RestTemplate restTemplate;

    @Value("${recaptcha.secret}")
    private String secret;

    @Autowired
    public void setUserService(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String registration() {
        return "registration";
    }

    @PostMapping
    public String processRegistration(@RequestParam("confirmPassword") String confirmPassword,
                                      @RequestParam("g-recaptcha-response") String captchaResponse,
                                      @Valid User user, BindingResult bindingResult,
                                      Model model) {

        String url = String.format(CAPTCHA_URL, secret, captchaResponse);
        CaptchaResponseDto response = restTemplate.postForObject(url, Collections.emptyList(), CaptchaResponseDto.class);

        if (!response.isSuccess()) {
            model.addAttribute("captchaError", "Fill captcha");
        }

        if (isValidationHasErrors(confirmPassword, user, bindingResult, model, response)) {
            return "registration";
        }

        userService.addNewUser(user);

        return "redirect:/login";
    }

    private boolean isValidationHasErrors(@RequestParam("confirmPassword") String confirmPassword,
                                          @Valid User user, BindingResult bindingResult,
                                          Model model,
                                          CaptchaResponseDto response) {
        boolean isConfirmEmpty = StringUtils.isEmpty(confirmPassword);
        if (isConfirmEmpty) {
            model.addAttribute("confirmPasswordError", "Password confirmation cannot be empty");
            return true;
        }

        boolean isPasswordDifferent = user.getPassword() != null && !user.getPassword().equals(confirmPassword);
        if (isPasswordDifferent) {
            model.addAttribute("passwordError", "Passwords are different!");
            return true;
        }

        if (!response.isSuccess() || bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return true;
        }

        if (userService.isUserExist(user)) {
            model.addAttribute("usernameError", "User already exists!");
            return true;
        }
        if (userService.isEmailExist(user)) {
            model.addAttribute("emailError", "Email already exists!");
            return true;
        }
        return false;
    }

}
