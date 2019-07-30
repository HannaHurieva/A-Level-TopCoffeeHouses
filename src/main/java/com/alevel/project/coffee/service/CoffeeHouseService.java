package com.alevel.project.coffee.service;

import com.alevel.project.coffee.model.CoffeeHouse;
import com.alevel.project.coffee.model.Contact;
import com.alevel.project.coffee.model.CuisineType;
import com.alevel.project.coffee.model.PlaceCategory;

import java.util.List;
import java.util.Optional;

public interface CoffeeHouseService {
    void createCoffeeHouse(CoffeeHouse coffeeHouse, Contact contact);

    List<CoffeeHouse> findAll();

    Optional<CoffeeHouse> findByTitle(String title);

    List<CoffeeHouse> findByContact_Location(String location);

    List<CoffeeHouse> findByCuisineType(String cuisineType);

    List<CoffeeHouse> findByPlaceCategory(String placeCategory);

    boolean isTitleExist(CoffeeHouse coffeeHouse);

    void updateCoffeeHouse(CoffeeHouse coffeeHouse,
                           String title, String description,
                           int timeOpening, int timeClosing,
                           Contact contact,
                           String address, String location, String phone, String website,
                           CuisineType cuisineType,
                           PlaceCategory placeCategory);

    void deleteCoffeeHouse(CoffeeHouse coffeeHouse);

}
