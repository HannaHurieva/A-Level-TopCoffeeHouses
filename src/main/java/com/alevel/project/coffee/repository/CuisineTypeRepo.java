package com.alevel.project.coffee.repository;

import com.alevel.project.coffee.model.CuisineType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CuisineTypeRepo extends JpaRepository<CuisineType, Integer> {
    Optional<CuisineType> findByCuisineType(String cuisineType);
}
