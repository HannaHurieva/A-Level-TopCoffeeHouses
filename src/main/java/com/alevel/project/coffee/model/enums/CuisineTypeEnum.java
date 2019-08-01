package com.alevel.project.coffee.model.enums;

public enum CuisineTypeEnum implements CodeEnum {

    American(0, "American"),
    Argentinean(1, "Argentinean"),
    Armenian(2, "Armenian"),
    Asian(3, "Asian"),
    Caucasian(4, "Caucasian"),
    Chinese(5, "Chinese"),
    European(6, "European"),
    French(7, "French"),
    Georgian(8, "Georgian"),
    German(9, "German"),
    Italian(10, "Italian"),
    Japanese(11, "Japanese"),
    Mexican(12, "Mexican"),
    Russian(13, "Russian"),
    Turkish(14, "Turkish"),
    Ukrainian(15, "Ukrainian"),
    Uzbek(16, "Uzbek"),
    Vietnamese(17, "Vietnamese");

    private Integer code;
    private String message;

    CuisineTypeEnum() {
    }

    CuisineTypeEnum(Integer code, String message) {
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
