package com.example.restaurant.dish;

import com.example.restaurant.menu.MenuPosition;

public class Drink extends Dish implements MenuPosition {

    public static final String DRINK_FILE = "Drink.json";
    public static final String DRINK_KEY = "drinks";
    public static final String DRINK_NAME = "name";
    public static final String DRINK_PRICE = "price";

    private boolean hasIceCubes;
    private boolean hasLemon;

    public Drink(String name, Double price) {
        super(name, price);
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean hasIceCubes() {
        return hasIceCubes;
    }

    public void setHasIceCubes(boolean hasIceCubes) {
        this.hasIceCubes = hasIceCubes;
    }

    public boolean hasLemon() {
        return hasLemon;
    }

    public void setHasLemon(boolean hasLemon) {
        this.hasLemon = hasLemon;
    }
}
