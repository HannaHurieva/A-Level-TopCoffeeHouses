package com.alevel.project.coffee.service.impl;

import com.alevel.project.coffee.model.CoffeeHouse;
import com.alevel.project.coffee.model.Contact;
import com.alevel.project.coffee.model.PlaceCategory;
import com.alevel.project.coffee.model.enums.CuisineTypeEnum;
import com.alevel.project.coffee.repository.CoffeeHouseRepo;
import com.alevel.project.coffee.repository.ContactRepo;
import com.alevel.project.coffee.service.CoffeeHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CoffeeHouseServiceImpl implements CoffeeHouseService {

    private final CoffeeHouseRepo coffeeHouseRepo;
    private final ContactRepo contactRepo;

    @Autowired
    public CoffeeHouseServiceImpl(CoffeeHouseRepo coffeeHouseRepo, ContactRepo contactRepo) {
        this.coffeeHouseRepo = coffeeHouseRepo;
        this.contactRepo = contactRepo;
    }

    @Override
    public void createCoffeeHouse(CoffeeHouse coffeeHouse, Contact contact, Map<String, String> form) {
        Set<String> cuisineSet = Arrays.stream(CuisineTypeEnum.values())
                .map(CuisineTypeEnum::name)
                .collect(Collectors.toSet());

        Set<CuisineTypeEnum> cuisineTypesOfCoffeeHouse = new HashSet<>();
        for (String key : form.keySet()) {
            if (cuisineSet.contains(key))
                cuisineTypesOfCoffeeHouse.add(CuisineTypeEnum.valueOf(key));
        }
        coffeeHouse.setCuisineTypes(cuisineTypesOfCoffeeHouse);
        coffeeHouseRepo.saveAndFlush(coffeeHouse);
        contact.setCoffeeHouse(coffeeHouse);
        contactRepo.save(contact);
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
                                  Map<String, String> cuisineType,
                                  PlaceCategory placeCategory) {
        //TODO
    }

    @Override
    public void deleteCoffeeHouse(CoffeeHouse coffeeHouse) {
        coffeeHouseRepo.deleteById(coffeeHouse.getId());
    }
}
