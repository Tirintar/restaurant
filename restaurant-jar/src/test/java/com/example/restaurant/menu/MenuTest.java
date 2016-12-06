package com.example.restaurant.menu;

import com.example.restaurant.dish.Dish;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MenuTest {

    @DataProvider
    public Object[][] pages() {
        return new Object[][] { {Page.ORDER_TYPE, "Lunch", Page.LUNCH}, {Page.ORDER_TYPE, "Drink", Page.DRINK},
                {Page.ORDER_TYPE, "Bad string", Page.ORDER_TYPE}, {Page.LUNCH, "Main course", Page.MAIN_COURSE_CUISINE},
                {Page.LUNCH, "Dessert", Page.DESSERT_CUISINE}, {Page.LUNCH, "Cancel", Page.ORDER_TYPE},
                {Page.LUNCH, "Bad string", Page.LUNCH}, {Page.MAIN_COURSE_CUISINE, "Polish", Page.MAIN_COURSE},
                {Page.MAIN_COURSE_CUISINE, "Mexican", Page.MAIN_COURSE}, {Page.MAIN_COURSE_CUISINE, "Italian",
                Page.MAIN_COURSE}, {Page.MAIN_COURSE_CUISINE, "Cancel", Page.LUNCH}, {Page.MAIN_COURSE_CUISINE,
                "Bad string", Page.MAIN_COURSE_CUISINE}, {Page.DESSERT_CUISINE, "Polish", Page.DESSERT},
                {Page.DESSERT_CUISINE, "Mexican", Page.DESSERT}, {Page.DESSERT_CUISINE, "Italian",
                Page.DESSERT}, {Page.DESSERT_CUISINE, "Cancel", Page.LUNCH}, {Page.DESSERT_CUISINE,
                "Bad string", Page.DESSERT_CUISINE}, {Page.MAIN_COURSE, "Cancel", Page.MAIN_COURSE_CUISINE},
                {Page.DESSERT, "Cancel", Page.DESSERT_CUISINE}, {Page.DRINK, "Cancel", Page.ORDER_TYPE} };
    }

    @Test(dataProvider = "pages")
    public void verifyMenuNavigationBasedOnUserInput(Page pageBefore, String input, Page pageAfter) {
        Menu menu = new Menu();
        menu.page = pageBefore;
        menu.makeDecision(input);
        menu.readPage();
        assertThat(menu.page).isEqualTo(pageAfter);
    }

    @DataProvider
    public Object[][] dishes() {
        return new Object [][] { {Page.DESSERT, "Sample dish", Page.LUNCH}, {Page.MAIN_COURSE, "Sample dish", Page.LUNCH},
                {Page.DRINK, "Sample dish", Page.DRINK_ADDITION}};
    }

    @Test(dataProvider = "dishes")
    public void verifyAddingDishesToOrder(Page pageBefore, String input, Page pageAfter) {
        Menu menu = new Menu();
        menu.page = pageBefore;
        List<Dish> dishes = new ArrayList<Dish>();
        dishes.add(new Dish("Sample dish", 5.5D));
        menu.availableDishes = dishes;
        menu.makeDecision(input);
        menu.readPage();
        assertThat(menu.getOrder().isEmpty()).isFalse();
        assertThat(menu.page).isEqualTo(pageAfter);
    }

    @DataProvider
    public Object[][] incorrectDishes() {
        return new Object [][] { {Page.DESSERT, "Incorrect dish", Page.DESSERT}, {Page.MAIN_COURSE, "Incorrect dish",
                Page.MAIN_COURSE}, {Page.DRINK, "Incorrect dish", Page.DRINK} };
    }

    @Test(dataProvider = "incorrectDishes")
    public void verifyHandlingIncorrectDishes(Page pageBefore, String input, Page pageAfter) {
        Menu menu = new Menu();
        menu.page = pageBefore;
        List<Dish> dishes = new ArrayList<Dish>();
        dishes.add(new Dish("Sample dish", 5.5D));
        menu.availableDishes = dishes;
        menu.makeDecision(input);
        assertThat(menu.getOrder().isEmpty()).isTrue();
        assertThat(menu.page).isEqualTo(pageAfter);
    }

}
