package com.example.restaurant.dish;

import com.example.restaurant.menu.MenuPosition;

/**
 * Class representing a dessert.
 * Contains the name of directory storing json file containing available desserts, and keys for parsing the file.
 */
public class Dessert extends Dish implements MenuPosition {

    public static final String DESSERT_PATH = "Dessert";
    public static final String DESSERT_KEY = "desserts";
    public static final String DESSERT_NAME = "name";
    public static final String DESSERT_PRICE = "price";

    public Dessert(String name, Double price) {
        super(name, price);
    }

}
