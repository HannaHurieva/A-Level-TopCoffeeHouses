package com.alevel.project.coffee.controller;

import com.alevel.project.coffee.model.Place;
import com.alevel.project.coffee.service.impl.PlaceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/places")
public class MainController {
    private PlaceServiceImpl placeService;

    @Autowired
    public void setPlaceService(PlaceServiceImpl placeService) {
        this.placeService = placeService;
    }

    @GetMapping
    public String getAllPlaces(Model model) {
        List<Place> places = placeService.findAll();
        model.addAttribute("places", places);
        return "places";
    }

    @PostMapping("/cuisine")
    public String findByCuisineType(@RequestParam String cuisineType, Model model) {
        List<Place> placesByCuisineType = placeService.findByCuisineType(cuisineType);
        model.addAttribute("places", placesByCuisineType);
        return "places";
    }

    @PostMapping("/location")
    public String findByLocation(@RequestParam String location, Model model) {
        List<Place> placesByContact_Location = placeService.findByContact_Location(location);
        model.addAttribute("places", placesByContact_Location);
        return "places";
    }

    @PostMapping("/category")
    public String findByCategory(@RequestParam String category, Model model) {
        List<Place> placesByPlaceCategory = placeService.findByPlaceCategory(category);
        model.addAttribute("places", placesByPlaceCategory);
        return "places";
    }

}
