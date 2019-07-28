package com.alevel.project.coffee.service.impl;

import com.alevel.project.coffee.model.CoffeeHouse;
import com.alevel.project.coffee.model.Contact;
import com.alevel.project.coffee.model.CuisineType;
import com.alevel.project.coffee.model.PlaceCategory;
import com.alevel.project.coffee.repository.CoffeeHouseRepo;
import com.alevel.project.coffee.service.CoffeeHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoffeeHouseServiceImpl implements CoffeeHouseService {

    private final CoffeeHouseRepo coffeeHouseRepo;

    @Autowired
    public CoffeeHouseServiceImpl(CoffeeHouseRepo coffeeHouseRepo) {
        this.coffeeHouseRepo = coffeeHouseRepo;
    }

    @Override
    public void createCoffeeHouse(CoffeeHouse coffeeHouse) {
        coffeeHouseRepo.save(coffeeHouse);
    }

    @Override
    public List<CoffeeHouse> findAll() {
        return coffeeHouseRepo.findAll();
    }

    @Override
    public Optional<CoffeeHouse> findByTitle(String title) {
        return Optional.ofNullable(coffeeHouseRepo.findByTitle(title));
    }

    @Override
    public List<CoffeeHouse> findByContact_Location(String location) {
        return coffeeHouseRepo.findByContact_Location(location);
    }

    @Override
    public List<CoffeeHouse> findByCuisineType(String cuisineType) {
        return coffeeHouseRepo.findByCuisineTypes(cuisineType);
    }

    @Override
    public List<CoffeeHouse> findByPlaceCategory(String placeCategory) {
        return coffeeHouseRepo.findByPlaceCategories(placeCategory);
    }

    @Override
    public boolean isTitleExist(CoffeeHouse coffeeHouse) {
        CoffeeHouse coffeeHouseFromDb = coffeeHouseRepo.findByTitle(coffeeHouse.getTitle());
        return coffeeHouseFromDb != null;
    }

    @Override
    public void updateCoffeeHouse(CoffeeHouse coffeeHouse, 
                                  String title, String description, 
                                  int timeOpening, int timeClosing, 
                                  Contact contact, 
                                  String address, String location, String phone, String website, 
                                  CuisineType cuisineType, 
                                  PlaceCategory placeCategory) {
    //TODO
    }

    @Override
    public void deleteCoffeeHouse(CoffeeHouse coffeeHouse) {
        coffeeHouseRepo.deleteById(coffeeHouse.getId());
    }
}
