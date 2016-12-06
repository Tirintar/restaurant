package com.example.restaurant;

/**
 * Food ordering system.
 *
 */
public class Restaurant {

    public static void main(String[] args) {
        Waiter waiter = new Waiter();
        while (waiter.isTakingOrder()) {
            waiter.takeOrder();
        }
    }

}
