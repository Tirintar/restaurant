package com.example.restaurant;

import com.example.restaurant.dish.Dessert;
import com.example.restaurant.dish.Drink;
import com.example.restaurant.dish.Lunch;
import com.example.restaurant.dish.MainCourse;
import com.example.restaurant.menu.*;
import com.example.restaurant.order.Order;

import java.util.Scanner;

import static com.example.restaurant.util.Message.*;

/**
 * Class responsible for handling user input, and passing into menu mechanism.
 */
class Waiter {

    private boolean isTakingOrder = true;
    private Scanner scanner;
    private Menu menu;
    private String choice = "";

    Waiter() {
        menu = new Menu();
        scanner = new Scanner(System.in);
    }

    void takeOrder() {
        readCurrentOrder();
        menu.readPage();
        choice = scanner.nextLine();
        menu.makeDecision(choice);
        if(!menu.isOpen()) {
            isTakingOrder = false;
        }
    }

    /**
     * Method checks if application should await input, or exit.
     *
     * @return true if the application should exit, false otherwise
     */
    boolean isTakingOrder() {
        return isTakingOrder;
    }

    private void readCurrentOrder() {
        Order order = menu.getOrder();
        if (!order.isEmpty()) {
            System.out.println(CURRENT_ORDER);
            Lunch lunch = order.getLunch();
            MainCourse mainCourse = lunch.getMainCourse();
            Dessert dessert = lunch.getDessert();
            Drink drink = order.getDrink();
            Double totalPrice = 0.0D;
            if (null != mainCourse) {
                System.out.println(String.format(ORDER_MAIN_COURSE, mainCourse.getName(), mainCourse.getPrice()));
                totalPrice += mainCourse.getPrice();
            }
            if (null != dessert) {
                System.out.println(String.format(ORDER_DESSERT, dessert.getName(), dessert.getPrice()));
                totalPrice += dessert.getPrice();
            }
            if (null != drink) {
                String addition = "";
                if (drink.hasIceCubes() && drink.hasLemon()) {
                    addition = DRINK_HAS_ICECUBES_AND_LEMON;
                } else if (drink.hasLemon()) {
                    addition = DRINK_HAS_LEMON;
                } else if (drink.hasIceCubes()) {
                    addition = DRINK_HAS_ICECUBES;
                }
                System.out.println(String.format(ORDER_DRINK, drink.getName(), addition, drink.getPrice()));
                totalPrice += drink.getPrice();
            }
            System.out.println(String.format(ORDER_TOTAL, totalPrice));
        }
    }
}
