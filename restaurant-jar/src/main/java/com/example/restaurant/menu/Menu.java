package com.example.restaurant.menu;

import com.example.restaurant.dish.*;
import com.example.restaurant.order.Order;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import static com.example.restaurant.util.Message.*;

/**
 * Class responsible for handling the menu.
 */
public class Menu {

    Page page = Page.ORDER_TYPE;
    List<? extends Dish> availableDishes;
    private Page previousPage;
    private String choice = "";
    private String previousChoice = "";
    private boolean isOpen = true;
    private Order order = new Order();

    /**
     * Reads the current menu page contents.
     */
    public void readPage() {
        previousChoice = choice;
        switch (page) {
            case DRINK_ADDITION:
                System.out.println(DRINK_ADDITION_MESSAGE);
                break;
            case DRINK:
                readOptions(getDrinks());
                break;
            case DESSERT:
                readOptions(getDesserts(choice));
                break;
            case MAIN_COURSE:
                readOptions(getMainCourses(choice));
                break;
            case MAIN_COURSE_CUISINE:
                readOptions(getMainCourseCuisines());
                break;
            case DESSERT_CUISINE:
                readOptions(getDessertCuisines());
                break;
            case LUNCH:
                System.out.println(LUNCH_MESSAGE);
                break;
            case ORDER_TYPE:
                System.out.println(ORDER_TYPE_MESSAGE);
                if (order.isComplete()) {
                    System.out.println(String.format("* %s", OPTION_COMPLETE_ORDER));
                }
                break;
        }
    }

    /**
     * Handles the input from user.
     *
     * @param choice A string representing user input
     */
    public void makeDecision(String choice) {
        this.choice = choice;
        switch (page) {
            case ORDER_TYPE:
                if (OPTION_LUNCH.equalsIgnoreCase(choice)) {
                    turnPage(Page.LUNCH);
                } else if (OPTION_DRINK.equalsIgnoreCase(choice)) {
                    turnPage(Page.DRINK);
                } else if (OPTION_CANCEL.equalsIgnoreCase(choice)) {
                    isOpen = false;
                } else if (OPTION_COMPLETE_ORDER.equalsIgnoreCase(choice) && order.isComplete()) {
                    isOpen = false;
                } else {
                    System.out.println(UNRECOGNIZED_OPTION);
                }
                break;
            case LUNCH:
                if (OPTION_MAIN_COURSE.equalsIgnoreCase(choice)) {
                    turnPage(Page.MAIN_COURSE_CUISINE);
                } else if (OPTION_DESSERT.equalsIgnoreCase(choice)) {
                    turnPage(Page.DESSERT_CUISINE);
                } else if (OPTION_CANCEL.equalsIgnoreCase(choice)) {
                    page = Page.ORDER_TYPE;
                } else {
                    System.out.println(UNRECOGNIZED_OPTION);
                }
                break;
            case MAIN_COURSE_CUISINE:
                if (OPTION_CANCEL.equalsIgnoreCase(choice)) {
                    page = Page.LUNCH;
                } else {
                    turnPage(Page.MAIN_COURSE);
                }
                break;
            case DESSERT_CUISINE:
                if (OPTION_CANCEL.equalsIgnoreCase(choice)) {
                    page = Page.LUNCH;
                } else {
                    turnPage(Page.DESSERT);
                }
                break;
            case MAIN_COURSE:
                if (OPTION_CANCEL.equalsIgnoreCase(choice)) {
                    page = Page.MAIN_COURSE_CUISINE;
                } else {
                    addDishToOrder(choice);
                }
                break;
            case DESSERT:
                if (OPTION_CANCEL.equalsIgnoreCase(choice)) {
                    page = Page.DESSERT_CUISINE;
                } else {
                    addDishToOrder(choice);
                }
                break;
            case DRINK:
                if (OPTION_CANCEL.equalsIgnoreCase(choice)) {
                    page = Page.ORDER_TYPE;
                } else {
                    addDishToOrder(choice);
                }
                break;
            case DRINK_ADDITION:
                handleDrinkAddition(choice);
                break;
        }
    }

    public boolean isOpen() {
        return isOpen;
    }

    public Order getOrder() {
        return order;
    }

    private void turnPage(Page page) {
        previousPage = this.page;
        this.page = page;
    }

    private void readOptions(List options) {
        if (null == options) {
            System.out.println(MENU_ERROR_MESSAGE);
            page = previousPage;
            choice = previousChoice;
            readPage();
        } else if (options.isEmpty()) {
            System.out.println(NO_DISHES_FOUND);
            page = previousPage;
            choice = previousChoice;
            readPage();
        } else {
            System.out.println(MENU_OPTIONS_MESSAGE);
            for (MenuPosition menuPosition : (List<MenuPosition>) options) {
                System.out.println(menuPosition.toString());
            }
            System.out.println(String.format("* %s", OPTION_CANCEL));
        }
    }

    private void addDishToOrder(String choice) {
        boolean correctChoice = false;
        for (Dish availableDish : availableDishes) {
            if (availableDish.getName().equalsIgnoreCase(choice)) {
                Lunch lunch = order.getLunch();
                if (page.equals(Page.MAIN_COURSE)) {
                    lunch.setMainCourse(new MainCourse(availableDish.getName(), availableDish.getPrice()));
                } else if (page.equals(Page.DESSERT)) {
                    lunch.setDessert(new Dessert(availableDish.getName(), availableDish.getPrice()));
                } else if (page.equals(Page.DRINK)) {
                    order.setDrink(new Drink(availableDish.getName(), availableDish.getPrice()));
                }
                order.setLunch(lunch);
                correctChoice = true;
                break;
            }
        }
        if (correctChoice) {
            if (page.equals(Page.DRINK)) {
                turnPage(Page.DRINK_ADDITION);
            } else {
                turnPage(Page.LUNCH);
            }
        } else {
            System.out.println(UNRECOGNIZED_OPTION);
            this.choice = previousChoice;
        }
    }

    private void handleDrinkAddition(String choice) {
        if (OPTION_NONE.equalsIgnoreCase(choice)) {
            order.getDrink().setHasIceCubes(false);
            order.getDrink().setHasLemon(false);
            page = Page.ORDER_TYPE;
        } else if (OPTION_BOTH.equalsIgnoreCase(choice)) {
            order.getDrink().setHasLemon(true);
            order.getDrink().setHasIceCubes(true);
            page = Page.ORDER_TYPE;
        } else if (OPTION_ICECUBES.equalsIgnoreCase(choice)) {
            order.getDrink().setHasLemon(false);
            order.getDrink().setHasIceCubes(true);
            page = Page.ORDER_TYPE;
        } else if (OPTION_LEMON.equalsIgnoreCase(choice)) {
            order.getDrink().setHasLemon(true);
            order.getDrink().setHasIceCubes(false);
            page = Page.ORDER_TYPE;
        } else {
            System.out.println(UNRECOGNIZED_OPTION);
        }
    }

    private List<Cuisine> getMainCourseCuisines() {
        return getCuisines(Cuisine.CUISINE_MAIN_COURSE_FILE);
    }

    private List<Cuisine> getDessertCuisines() {
        return getCuisines(Cuisine.CUISINE_DESSERT_FILE);
    }

    private List<MainCourse> getMainCourses(String cuisine) {
        List<MainCourse> mainCourses = new ArrayList<MainCourse>();
        String jsonFile = readJsonFromResources(String.format("%s/%s.json", MainCourse.MAIN_COURSE_PATH, cuisine));
        if (jsonFile != null) {
            try {
                JSONObject mainCourseJson = new JSONObject(jsonFile);
                JSONArray array = mainCourseJson.getJSONArray(MainCourse.MAIN_COURSE_KEY);
                for (Object object : array) {
                    JSONObject mainCourse = (JSONObject) object;
                    mainCourses.add(new MainCourse(mainCourse.getString(MainCourse.MAIN_COURSE_NAME),
                            mainCourse.getDouble(MainCourse.MAIN_COURSE_PRICE)));
                }
            } catch (JSONException e) {
                return null;
            }
        }
        availableDishes = mainCourses;
        return mainCourses;
    }

    private List<Dessert> getDesserts(String cuisine) {
        List<Dessert> desserts = new ArrayList<Dessert>();
        String jsonFile = readJsonFromResources(String.format("%s/%s.json", Dessert.DESSERT_PATH, cuisine));
        if (jsonFile != null) {
            try {
                JSONObject dessertJson = new JSONObject(jsonFile);
                JSONArray array = dessertJson.getJSONArray(Dessert.DESSERT_KEY);
                for (Object object : array) {
                    JSONObject dessert = (JSONObject) object;
                    desserts.add(new Dessert(dessert.getString(Dessert.DESSERT_NAME),
                            dessert.getDouble(Dessert.DESSERT_PRICE)));
                }
            } catch (JSONException e) {
                return null;
            }
        }
        availableDishes = desserts;
        return desserts;
    }

    private List<Cuisine> getCuisines(String path) {
        List<Cuisine> cuisines = new ArrayList<Cuisine>();
        String jsonFile = readJsonFromResources(path);
        if (jsonFile != null) {
            try {
                JSONObject cuisineJson = new JSONObject(jsonFile);
                JSONArray array = cuisineJson.getJSONArray(Cuisine.CUISINE_KEY);
                for (Object object : array) {
                    JSONObject cuisine = (JSONObject) object;
                    cuisines.add(new Cuisine(cuisine.getString(Cuisine.CUISINE_TYPE)));
                }
            } catch (JSONException e) {
                return null;
            }
        }
        return cuisines;
    }

    private List<Drink> getDrinks() {
        List<Drink> drinks = new ArrayList<Drink>();
        String jsonFile = readJsonFromResources(Drink.DRINK_FILE);
        if (jsonFile != null) {
            try {
                JSONObject drinkJson = new JSONObject(jsonFile);
                JSONArray array = drinkJson.getJSONArray(Drink.DRINK_KEY);
                for (Object object : array) {
                    JSONObject drink = (JSONObject) object;
                    drinks.add(new Drink(drink.getString(Drink.DRINK_NAME), drink.getDouble(Drink.DRINK_PRICE)));
                }
            } catch (JSONException e) {
                return null;
            }
        }
        availableDishes = drinks;
        return drinks;
    }

    private String readJsonFromResources(String fileName) {
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());
            return new String(Files.readAllBytes(file.toPath()));
        } catch (Exception e) {
            return null;
        }
    }

}
