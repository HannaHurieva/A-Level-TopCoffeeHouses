package com.alevel.project.coffee.model.enums;

public enum PlaceCategoryEnum implements CodeEnum {

    AntiCafe(0, "Anticafe"),
    ArtCafe(1, "Art cafe"),
    Bakery(2, "Bakery"),
    Bar(3, "Bar"),
    Cafe(4, "Cafe"),
    CoffeeHouse(5, "Coffee house"),
    CoffeeToGo(6, "Coffee to go"),
    Pizzeria(7, "Pizzeria"),
    Pub(8, "Pub"),
    Restaurant(9, "Restaurant"),
    SushiBar(10, "Sushi bar"),
    TeaRoom(11, "Tea Room");

    private Integer code;
    private String message;

    PlaceCategoryEnum() {
    }

    PlaceCategoryEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
