package com.example.restaurant.dish;

/**
 * Class representing lunch.
 */
public class Lunch {

    private MainCourse mainCourse;
    private Dessert dessert;

    public MainCourse getMainCourse() {
        return mainCourse;
    }

    public void setMainCourse(MainCourse mainCourse) {
        this.mainCourse = mainCourse;
    }

    public Dessert getDessert() {
        return dessert;
    }

    public void setDessert(Dessert dessert) {
        this.dessert = dessert;
    }
}
