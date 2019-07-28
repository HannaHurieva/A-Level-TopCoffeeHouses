package com.alevel.project.coffee.controller;

import com.alevel.project.coffee.model.CoffeeHouse;
import com.alevel.project.coffee.service.impl.CoffeeHouseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/places")
@PreAuthorize("hasAuthority('ADMIN')")
public class CoffeeHouseAdministrationController {
    private CoffeeHouseServiceImpl coffeeHouseService;

    @Autowired
    public void setCoffeeHouseService(CoffeeHouseServiceImpl coffeeHouseService) {
        this.coffeeHouseService = coffeeHouseService;
    }

    @GetMapping("/create")
    public String createForm() {
        return "createPlace";
    }

    @PostMapping("/create")
    public String createNewCoffeeHouse(@Valid CoffeeHouse coffeeHouse, BindingResult bindingResult,
                                       Model model) {
        if (isValidationHasErrors(coffeeHouse, bindingResult, model))
            return "createPlace";

        coffeeHouseService.createCoffeeHouse(coffeeHouse);
        return "places";
    }

    private boolean isValidationHasErrors(@Valid CoffeeHouse coffeeHouse, BindingResult bindingResult,
                                          Model model) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(errors);
            return true;
        }
        if (coffeeHouseService.isTitleExist(coffeeHouse)) {
            model.addAttribute("titleError", "Title of this place already exists!");
            return true;
        }
        return false;
    }
}
