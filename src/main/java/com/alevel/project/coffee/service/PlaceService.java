package com.alevel.project.coffee.service;

import com.alevel.project.coffee.model.Contact;
import com.alevel.project.coffee.model.Place;

import java.util.List;
import java.util.Map;

public interface PlaceService {
    void createNewPlace(Place place, Contact contact, Map<String, String> form);

    List<Place> findAll();

    Place findById(Long id);

    Place findByTitle(String title);

    List<Place> findByContact_Location(String location);

    List<Place> findByCuisineType(String cuisineType);

    List<Place> findByPlaceCategory(String placeCategory);

    boolean isTitleExist(Place place);

    void deletePlace(Place place);

}
