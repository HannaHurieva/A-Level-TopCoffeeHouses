package com.alevel.project.coffee.service;

import com.alevel.project.coffee.model.Contact;
import com.alevel.project.coffee.model.Place;
import com.alevel.project.coffee.repository.ContactRepo;
import com.alevel.project.coffee.repository.CuisineTypeRepo;
import com.alevel.project.coffee.repository.PlaceCategoryRepo;
import com.alevel.project.coffee.repository.PlaceRepo;
import com.alevel.project.coffee.service.impl.PlaceServiceImpl;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class PlaceServiceImplTest {
    private PlaceRepo placeRepo = mock(PlaceRepo.class);
    private ContactRepo contactRepo = mock(ContactRepo.class);
    private CuisineTypeRepo cuisineTypeRepo = mock(CuisineTypeRepo.class);
    private PlaceCategoryRepo placeCategoryRepo = mock(PlaceCategoryRepo.class);

    private PlaceServiceImpl placeService = new PlaceServiceImpl();

    private static final Place place = new Place();
    private static final Contact contact = new Contact();
    private static final Map<String, String> form = new HashMap<>();

    @BeforeEach
    void setUp() {
        placeService.setPlaceRepo(placeRepo);
        placeService.setContactRepo(contactRepo);
        placeService.setCuisineTypeRepo(cuisineTypeRepo);
        placeService.setPlaceCategoryRepo(placeCategoryRepo);

        place.setId(1L);
        place.setTitle("CoffeeHouse");
        place.setTimeOpening(0);
        place.setTimeClosing(23);

        contact.setAddress("Kharkov");

        form.put("Ukrainian", "16");
        form.put("Coffee house", "6");
    }

    @Test
    void shouldCreateNewPlaceTest() {
        placeService.createNewPlace(place, contact, form);
        Mockito.verify(placeRepo, Mockito.times(1)).saveAndFlush(place);
        Mockito.verify(contactRepo, Mockito.times(1)).save(contact);
    }

    @Test
    void shouldCreateOtherNewPlaceTest() {
        place.setDescription("Favorite place of the citizens and guests of our city");
        contact.setLocation("Centre");
        contact.setPhone("095-55-55-555");
        contact.setWebsite("site.com.ua");
        form.put("Italian","11");
        form.put("Pizzeria","8");
        placeService.createNewPlace(place, contact, form);
        Mockito.verify(placeRepo, Mockito.times(1)).saveAndFlush(place);
        Mockito.verify(contactRepo, Mockito.times(1)).save(contact);
    }

    @Test
    void shouldReturnTrueIfPlaceExistTest() {
        when(placeRepo.findByTitle("CoffeeHouse")).thenReturn(place);
        Assert.assertTrue(placeService.isTitleExist(place));
    }

    @Test
    void shouldReturnFalseIfPlaceExistTest() {
        when(placeRepo.findByTitle("Nothing")).thenReturn(null);
        Assert.assertFalse(placeService.isTitleExist(place));
    }

    @Test
    void shouldDeletePlaceTest() {
        when(placeRepo.findById(1L)).thenReturn(java.util.Optional.of(place));
        placeService.deletePlace(place);
        Mockito.verify(placeRepo, Mockito.times(1)).delete(place);
    }

}
