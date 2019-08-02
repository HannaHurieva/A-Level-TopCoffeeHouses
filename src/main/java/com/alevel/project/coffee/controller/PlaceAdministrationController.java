package com.alevel.project.coffee.controller;

import com.alevel.project.coffee.model.Contact;
import com.alevel.project.coffee.model.Place;
import com.alevel.project.coffee.model.enums.CuisineTypeEnum;
import com.alevel.project.coffee.model.enums.PlaceCategoryEnum;
import com.alevel.project.coffee.service.impl.PlaceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/places")
@PreAuthorize("hasAuthority('ADMIN')")
public class PlaceAdministrationController {
    private PlaceServiceImpl placeService;

    @Autowired
    public void setPlaceService(PlaceServiceImpl placeService) {
        this.placeService = placeService;
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("cuisineTypes", CuisineTypeEnum.values());
        model.addAttribute("placeCategories", PlaceCategoryEnum.values());
        return "createPlace";
    }

    @PostMapping("/create")
    @Transactional
    public String createNewPlace(@ModelAttribute("place") @Valid Place place, BindingResult bindingResultPlace,
                                 @ModelAttribute("contact") @Valid Contact contact, BindingResult bindingResultContact,
                                 @RequestParam Map<String, String> form,
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
        if (placeService.isTitleExist(place)) {
            model.addAttribute("titleError", "Title of this place already exists!");
            return "createPlace";
        }
        placeService.createNewPlace(place, contact, form);
        return "places";
    }

}
