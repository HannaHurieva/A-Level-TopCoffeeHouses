package com.alevel.project.coffee.repository;

import com.alevel.project.coffee.model.PlaceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlaceCategoryRepo extends JpaRepository<PlaceCategory, Integer> {
    Optional<PlaceCategory> findByPlaceCategory(String placeCategory);
}
