package com.example.restaurant.dish;

/**
 * Class representing a dish.
 */
public class Dish {

    protected Double price;
    protected String name;

    public Dish (String name, Double price) {
        this.name = name;
        this.price = price;
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

    public String toString() {
        return String.format("* %s\t%.2f", name, price);
    }
}
