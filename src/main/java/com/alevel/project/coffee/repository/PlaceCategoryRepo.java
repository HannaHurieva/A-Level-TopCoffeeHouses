package com.alevel.project.coffee.repository;

import com.alevel.project.coffee.model.PlaceCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaceCategoryRepo extends JpaRepository<PlaceCategory, Long> {
}
