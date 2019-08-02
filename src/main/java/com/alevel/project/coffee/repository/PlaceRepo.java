package com.alevel.project.coffee.repository;

import com.alevel.project.coffee.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PlaceRepo extends JpaRepository<Place, Long> {

    Place findByTitle(String title);

    List<Place> findByContact_Location(String location);

    List<Place> findByCuisineTypes(String cuisineType);

    List<Place> findByPlaceCategories(String placeCategory);

}
