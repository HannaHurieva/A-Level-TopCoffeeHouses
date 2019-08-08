package com.alevel.project.coffee.service;

import com.alevel.project.coffee.model.CuisineType;

import java.util.List;
import java.util.Optional;

public interface CuisineTypeService {
    List<CuisineType> findAll();

    Optional<CuisineType> findById(Integer id);

    Optional<CuisineType> findByCuisineType(String cuisineType);
}
