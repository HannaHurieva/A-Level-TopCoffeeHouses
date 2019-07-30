package com.alevel.project.coffee.controller;

import com.alevel.project.coffee.model.CoffeeHouse;
import com.alevel.project.coffee.model.Contact;
import com.alevel.project.coffee.service.impl.CoffeeHouseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    @Transactional
    public String createNewCoffeeHouse(@ModelAttribute("coffeeHouse") @Valid CoffeeHouse coffeeHouse, BindingResult bindingResultPlace,
                                       @ModelAttribute("contact") @Valid Contact contact, BindingResult bindingResultContact,
                                       Model model) {

        if (bindingResultPlace.hasErrors()) {
            Map<String, String> errorsPlace = ControllerUtils.getErrors(bindingResultPlace);
            model.mergeAttributes(errorsPlace);
            return "createPlace";
        }
        if (bindingResultContact.hasErrors()) {
            Map<String, String> errorsContact = ControllerUtils.getErrors(bindingResultContact);
            model.mergeAttributes(errorsContact);
            return "createPlace";
        }
        if (coffeeHouseService.isTitleExist(coffeeHouse)) {
            model.addAttribute("titleError", "Title of this place already exists!");
            return "createPlace";
        }
        coffeeHouseService.createCoffeeHouse(coffeeHouse, contact);
        return "places";
    }

}
