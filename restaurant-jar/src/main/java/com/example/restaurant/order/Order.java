package com.example.restaurant.order;

import com.example.restaurant.dish.Drink;
import com.example.restaurant.dish.Lunch;

/**
 * Class representing order.
 */
public class Order {

    private Lunch lunch;

    private Drink drink;

    public Order() {
        lunch = new Lunch();
    }

    public Lunch getLunch() {
        return lunch;
    }

    public void setLunch(Lunch lunch) {
        this.lunch = lunch;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public boolean isEmpty() {
        if (null == drink && null == lunch.getDessert() && null == lunch.getMainCourse()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isComplete() {
        if (null != drink && null != lunch.getDessert() && null != lunch.getMainCourse()) {
            return true;
        } else {
            return false;
        }
    }

}
