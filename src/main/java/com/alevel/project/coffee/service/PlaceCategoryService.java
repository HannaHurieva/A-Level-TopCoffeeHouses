package com.alevel.project.coffee.service;

import com.alevel.project.coffee.model.PlaceCategory;

import java.util.List;
import java.util.Optional;

public interface PlaceCategoryService {
    List<PlaceCategory> findAll();

    Optional<PlaceCategory> findById(Integer id);

    Optional<PlaceCategory> findByPlaceCategory(String placeCategory);
}
