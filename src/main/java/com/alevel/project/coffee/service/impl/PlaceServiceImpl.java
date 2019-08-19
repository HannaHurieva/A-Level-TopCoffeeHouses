package com.alevel.project.coffee.service.impl;

import com.alevel.project.coffee.model.Contact;
import com.alevel.project.coffee.model.CuisineType;
import com.alevel.project.coffee.model.Place;
import com.alevel.project.coffee.model.PlaceCategory;
import com.alevel.project.coffee.repository.ContactRepo;
import com.alevel.project.coffee.repository.CuisineTypeRepo;
import com.alevel.project.coffee.repository.PlaceCategoryRepo;
import com.alevel.project.coffee.repository.PlaceRepo;
import com.alevel.project.coffee.service.PlaceNotFoundException;
import com.alevel.project.coffee.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PlaceServiceImpl implements PlaceService {

    private PlaceRepo placeRepo;
    private ContactRepo contactRepo;
    private CuisineTypeRepo cuisineTypeRepo;
    private PlaceCategoryRepo placeCategoryRepo;

    @Autowired
    public void setPlaceRepo(PlaceRepo placeRepo) {
        this.placeRepo = placeRepo;
    }

    @Autowired
    public void setContactRepo(ContactRepo contactRepo) {
        this.contactRepo = contactRepo;
    }

    @Autowired
    public void setCuisineTypeRepo(CuisineTypeRepo cuisineTypeRepo) {
        this.cuisineTypeRepo = cuisineTypeRepo;
    }

    @Autowired
    public void setPlaceCategoryRepo(PlaceCategoryRepo placeCategoryRepo) {
        this.placeCategoryRepo = placeCategoryRepo;
    }

    @Override
    public void createNewPlace(Place place, Contact contact, Map<String, String> form) {
        place.setCuisineTypes(getSetCuisineTypes(form));
        place.setPlaceCategories(getSetPlaceCategories(form));
        placeRepo.saveAndFlush(place);
        contact.setPlace(place);
        contactRepo.save(contact);
    }

    private Set<CuisineType> getSetCuisineTypes(Map<String, String> form) {
        Set<CuisineType> cuisineTypes = new HashSet<>();
        for (String key : form.keySet()) {
            Optional<CuisineType> cuisineTypeFromDB = cuisineTypeRepo.findByCuisineType(key);
            cuisineTypeFromDB.ifPresent(cuisineTypes::add);
        }
        return cuisineTypes;
    }

    private Set<PlaceCategory> getSetPlaceCategories(Map<String, String> form) {
        Set<PlaceCategory> placeCategories = new HashSet<>();
        for (String key : form.keySet()) {
            Optional<PlaceCategory> placeCategoryFromDB = placeCategoryRepo.findByPlaceCategory(key);
            placeCategoryFromDB.ifPresent(placeCategories::add);
        }
        return placeCategories;
    }

    @Override
    public List<Place> findAll() {
        return placeRepo.findAll();
    }

    @Override
    public Place findById(Long id) {
        return placeRepo.findById(id)
                .orElseThrow(() -> new PlaceNotFoundException(id));
    }

    @Override
    public Place findByTitle(String title) {
        return placeRepo.findByTitle(title);
    }

    @Override
    public List<Place> findByContact_Location(String location) {
        return placeRepo.findByContact_Location(location);
    }

    @Override
    public List<Place> findByCuisineType(String cuisineType) {
        return placeRepo.findByCuisineTypes(cuisineType);
    }

    @Override
    public List<Place> findByPlaceCategory(String placeCategory) {
        return placeRepo.findByPlaceCategories(placeCategory);
    }

    @Override
    public boolean isTitleExist(Place place) {
        Place placeFromDb = findByTitle(place.getTitle());
        return placeFromDb != null;
    }

    @Override
    public void deletePlace(Place place) {
        Place placeById = findById(place.getId());
        placeRepo.delete(placeById);
    }
}
