package com.alevel.project.coffee.repository;

import com.alevel.project.coffee.model.CuisineType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuisineTypeRepo extends JpaRepository<CuisineType, Long> {
}
