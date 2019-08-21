package com.alevel.project.coffee.controller;

import com.alevel.project.coffee.model.Place;
import com.alevel.project.coffee.service.impl.PlaceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/places")
public class MainPlaceController {
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

    @GetMapping("{place}")
    public String getPlacesByTitle(@PathVariable(name = "place") Place place, Model model) {
        Place placeByTitle = placeService.findByTitle(place.getTitle());
        model.addAttribute("place", placeByTitle);
        return "placeDetailed";
    }

    @GetMapping("/cuisine")
    public String getListPlacesByFilter(List<Place> places, Model model) {
        model.addAttribute("places", places);
        return "places";
    }

    @PostMapping("/cuisine")
    public String findByCuisineType(@RequestParam(name = "cuisineFilter") String cuisine, Model model) {
        List<Place> placesList = placeService.findByCuisineType(cuisine);
        return getListPlacesByFilter(placesList, model);
    }

    @PostMapping("/category")
    public String findByCategory(@RequestParam(name = "categoryFilter") String category, Model model) {
        List<Place> placesList = placeService.findByPlaceCategory(category);
        return getListPlacesByFilter(placesList, model);
    }

    @PostMapping("/location")
    public String findByLocation(@RequestParam(name = "locationFilter") String location, Model model) {
        List<Place> placesList = placeService.findByContactLocation(location);
        return getListPlacesByFilter(placesList, model);
    }
}
