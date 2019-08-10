package com.alevel.project.coffee.controller;

import com.alevel.project.coffee.model.Place;
import com.alevel.project.coffee.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/places")
public class MainController {

    private PlaceService placeService;

    @Autowired
    public void setPlaceService(PlaceService placeService) {
        this.placeService = placeService;
    }


    @GetMapping
    public String showAllPlaces(Map<String, Object> model) {
        Iterable<Place> places = placeService.findAll();

        model.put("places", places);
        return "places";

    }

    @PostMapping("/cuisine")
    public String findByCuisineType(@RequestParam String cuisineType, Map<String, Object> model) {
        List<Place> places = placeService.findByCuisineType(cuisineType);
        model.put("places", places);
        return "places";
    }

    @PostMapping("/location")
    public String findByLocation(@RequestParam String location, Map<String, Object> model){
        List<Place> places = placeService.findByContact_Location(location);
        model.put("places", places);
        return "places";
    }

    @PostMapping("/category")
    public String findByCategory(@RequestParam String category, Map<String, Object> model){
        List<Place> places = placeService.findByPlaceCategory(category);
        model.put("places", places);
        return "places";
    }






}
