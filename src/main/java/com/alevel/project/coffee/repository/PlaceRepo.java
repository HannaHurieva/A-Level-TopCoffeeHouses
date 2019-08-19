package com.alevel.project.coffee.repository;

import com.alevel.project.coffee.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PlaceRepo extends JpaRepository<Place, Long> {

    Place findByTitle(String title);

    List<Place> findByContactLocation(String location);

    @Query(value = "SELECT * FROM places JOIN place_cuisine_type ON places.place_id = place_cuisine_type.place_id " +
            "JOIN cuisine_types ON place_cuisine_type.cuisine_type_id = cuisine_types.cuisine_type_id " +
            "WHERE cuisine_types.cuisine_type = ?1",nativeQuery = true)
    List<Place> findBy_CuisineType(String cuisineType);

    @Query(value = "SELECT * FROM places JOIN place_to_place_category ON places.place_id = place_to_place_category.place_id\n" +
            "JOIN place_categories ON place_to_place_category.place_category_id = place_categories.place_category_id \n" +
            "WHERE place_categories.place_category = ?1",nativeQuery = true)
    List<Place> findBy_PlaceCategory(String placeCategory);

}
