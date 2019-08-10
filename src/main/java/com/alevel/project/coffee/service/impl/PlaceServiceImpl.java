package com.alevel.project.coffee.service.impl;

import com.alevel.project.coffee.model.Contact;
import com.alevel.project.coffee.model.Place;
import com.alevel.project.coffee.model.enums.CuisineTypeEnum;
import com.alevel.project.coffee.model.enums.PlaceCategoryEnum;
import com.alevel.project.coffee.repository.ContactRepo;
import com.alevel.project.coffee.repository.PlaceRepo;
import com.alevel.project.coffee.service.PlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PlaceServiceImpl implements PlaceService {

    private final PlaceRepo placeRepo;
    private final ContactRepo contactRepo;

    @Autowired
    public PlaceServiceImpl(PlaceRepo placeRepo, ContactRepo contactRepo) {
        this.placeRepo = placeRepo;
        this.contactRepo = contactRepo;
    }

    @Override
    public void createNewPlace(Place place, Contact contact, Map<String, String> form) {
        //get data for types of cuisine
        Set<String> cuisineSet = Arrays.stream(CuisineTypeEnum.values())
                .map(CuisineTypeEnum::name)
                .collect(Collectors.toSet());

        Set<CuisineTypeEnum> cuisineTypesOfPlace = new HashSet<>();
        for (String key : form.keySet()) {
            if (cuisineSet.contains(key))
                cuisineTypesOfPlace.add(CuisineTypeEnum.valueOf(key));
        }
        place.setCuisineTypes(cuisineTypesOfPlace);

        //get data for categories of place
        Set<String> categorySet = Arrays.stream(PlaceCategoryEnum.values())
                .map(PlaceCategoryEnum::name)
                .collect(Collectors.toSet());

        Set<PlaceCategoryEnum> categoriesOfPlace = new HashSet<>();
        for (String key : form.keySet()) {
            if (categorySet.contains(key))
                categoriesOfPlace.add(PlaceCategoryEnum.valueOf(key));
        }
        place.setPlaceCategories(categoriesOfPlace);

        placeRepo.saveAndFlush(place);
        contact.setPlace(place);
        contactRepo.save(contact);
    }

    @Override
    public List<Place> findAll() {
        return placeRepo.findAll();
    }

    @Override
    public Optional<Place> findByTitle(String title) {
        return Optional.ofNullable(placeRepo.findByTitle(title));
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
        Place placeFromDb = placeRepo.findByTitle(place.getTitle());
        return placeFromDb != null;
    }

    @Override
    public void updatePlace(Place place,
                            String title, String description,
                            int timeOpening, int timeClosing,
                            Contact contact,
                            String address, String location, String phone, String website,
                            Map<String, String> form) {
        //TODO
    }

    @Override
    public void deletePlace(Place place) {
        placeRepo.deleteById(place.getId());
    }
}
