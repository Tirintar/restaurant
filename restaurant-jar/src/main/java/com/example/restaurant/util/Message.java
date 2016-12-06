package com.example.restaurant.util;

/**
 * Class containing messages used in various other classes.
 */
public class Message {

    public static final String OPTION_LUNCH = "Lunch";
    public static final String OPTION_MAIN_COURSE = "Main course";
    public static final String OPTION_DESSERT = "Dessert";
    public static final String OPTION_DRINK = "Drink";
    public static final String OPTION_CANCEL = "Cancel";
    public static final String OPTION_LEMON = "Lemon";
    public static final String OPTION_ICECUBES = "Ice Cubes";
    public static final String OPTION_BOTH = "Both";
    public static final String OPTION_NONE = "None";
    public static final String OPTION_COMPLETE_ORDER = "Complete order";

    public static final String ORDER_TYPE_MESSAGE = "What would you like to order? \n" +
            "* " + OPTION_LUNCH + "\n* " + OPTION_DRINK + "\n* " + OPTION_CANCEL + "\n";
    public static final String LUNCH_MESSAGE = "Which part would like to order? \n" +
            "* " + OPTION_MAIN_COURSE + "\n* " + OPTION_DESSERT + "\n* " + OPTION_CANCEL + "\n";
    public static final String MENU_OPTIONS_MESSAGE = "Here are the available options. \n" +
            "Which one would you like to choose?";
    public static final String DRINK_ADDITION_MESSAGE = "Would you like something extra?\n" +
            "* " + OPTION_LEMON + "\n* " + OPTION_ICECUBES + "\n* " + OPTION_BOTH + "\n* " + OPTION_NONE + "\n";
    public static final String NO_DISHES_FOUND = "Well, that's embarrassing... it seems we don't have any.\n" +
            "Please make sure you have chosen a correct option, or choose a different one.\n";
    public static final String MENU_ERROR_MESSAGE = "This menu page is broken... \nPlease choose another option, " +
            "while we look into that.\n";
    public static final String UNRECOGNIZED_OPTION = "I am sorry, I don't understand. Could you make sure you made " +
            "a correct choice?";
    public static final String CURRENT_ORDER = "Your current order is: \n";
    public static final String ORDER_MAIN_COURSE = "Main course: %s\t%.2f";
    public static final String ORDER_DESSERT = "Dessert: %s\t%.2f";
    public static final String ORDER_DRINK = "Drink: %s%s\t%.2f";
    public static final String DRINK_HAS_ICECUBES = " (ice cubes) ";
    public static final String DRINK_HAS_LEMON = " (lemon) ";
    public static final String DRINK_HAS_ICECUBES_AND_LEMON = " (ice cubes and lemon) ";
    public static final String ORDER_TOTAL = "Order total: %.2f\n";

}
