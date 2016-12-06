package com.example.restaurant.dish;

import com.example.restaurant.menu.MenuPosition;

/**
 * Class representing a cuisine.
 * Contains the name of json file containing available cuisines, and keys for parsing the file.
 */
public class Cuisine implements MenuPosition {

    public static final String CUISINE_MAIN_COURSE_FILE = "MainCourseCuisine.json";
    public static final String CUISINE_DESSERT_FILE = "DessertCuisine.json";
    public static final String CUISINE_KEY = "cuisines";
    public static final String CUISINE_TYPE = "type";

    private String type;

    public Cuisine(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "* " + type;
    }
}
