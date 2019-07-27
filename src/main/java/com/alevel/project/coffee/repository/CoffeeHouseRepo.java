package com.alevel.project.coffee.repository;

import com.alevel.project.coffee.model.CoffeeHouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoffeeHouseRepo extends JpaRepository<CoffeeHouse, Long> {

    CoffeeHouse findByTitle(String title);

    List<CoffeeHouse> findByContact_Location(String location);

    List<CoffeeHouse> findByCuisineTypes(String cuisineType);

    List<CoffeeHouse> findByPlaceCategories(String placeCategory);

}
