package com.alevel.project.coffee.controller;

import com.alevel.project.coffee.model.Contact;
import com.alevel.project.coffee.model.Place;
import com.alevel.project.coffee.service.impl.CuisineTypeServiceImpl;
import com.alevel.project.coffee.service.impl.PlaceCategoryServiceImpl;
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
    private CuisineTypeServiceImpl cuisineTypeService;
    private PlaceCategoryServiceImpl placeCategoryService;

    @Autowired
    public void setPlaceService(PlaceServiceImpl placeService) {
        this.placeService = placeService;
    }

    @Autowired
    public void setCuisineTypeService(CuisineTypeServiceImpl cuisineTypeService) {
        this.cuisineTypeService = cuisineTypeService;
    }

    @Autowired
    public void setPlaceCategoryService(PlaceCategoryServiceImpl placeCategoryService) {
        this.placeCategoryService = placeCategoryService;
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("cuisineTypes", cuisineTypeService.findAll());
        model.addAttribute("placeCategories", placeCategoryService.findAll());
        return "placeCreate";
    }

    @PostMapping("/create")
    @Transactional
    public String createNewPlace(@ModelAttribute("place") @Valid Place place, BindingResult bindingResultPlace,
                                 @ModelAttribute("contact") @Valid Contact contact, BindingResult bindingResultContact,
                                 @RequestParam Map<String, String> form,
                                 Model model) {

        if (isValidationHasErrors(place, bindingResultPlace, contact, bindingResultContact, model)) {
            return createForm(model);
        }
        placeService.createNewPlace(place, contact, form);
        return "redirect:/places";
    }

    private boolean isValidationHasErrors(@ModelAttribute("place") @Valid Place place, BindingResult bindingResultPlace,
                                          @ModelAttribute("contact") @Valid Contact contact, BindingResult bindingResultContact,
                                          Model model) {
        if (bindingResultPlace.hasErrors()) {
            Map<String, String> errorsPlace = ControllerUtils.getErrors(bindingResultPlace);
            model.mergeAttributes(errorsPlace);
            return true;
        }
        if (bindingResultContact.hasErrors()) {
            Map<String, String> errorsContact = ControllerUtils.getErrors(bindingResultContact);
            model.mergeAttributes(errorsContact);
            return true;
        }
        if (placeService.isTitleExist(place)) {
            model.addAttribute("titleError", "Title of this place already exists!");
            return true;
        }
        return false;
    }

    @GetMapping("/delete")
    @Transactional
    public String resultDeletingPlace(Long id, Model model) {
        model.addAttribute("message", "The Place with id = " + id + " was successful deleted");
        return "requestOk";
    }

    @PostMapping("/delete/{place}")
    @Transactional
    public String deletePlace(@PathVariable(name = "place") Place place,
                              Model model) {
        placeService.deletePlace(place);
        return resultDeletingPlace(place.getId(), model);
    }
}
