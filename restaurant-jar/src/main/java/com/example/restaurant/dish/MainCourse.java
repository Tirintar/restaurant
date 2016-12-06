package com.example.restaurant.dish;

import com.example.restaurant.menu.MenuPosition;

/**
 * Class representing main course.
 * Contains the name of directory storing json file containing available main courses, and keys for parsing the file.
 */
public class MainCourse extends Dish implements MenuPosition {

    public static final String MAIN_COURSE_PATH = "MainCourse";
    public static final String MAIN_COURSE_KEY = "mainCourses";
    public static final String MAIN_COURSE_NAME = "name";
    public static final String MAIN_COURSE_PRICE = "price";

    public MainCourse(String name, Double price) {
        super(name, price);
    }

}
