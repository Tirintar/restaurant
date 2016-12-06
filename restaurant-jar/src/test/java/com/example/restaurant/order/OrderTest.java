package com.example.restaurant.order;

import com.example.restaurant.dish.Dessert;
import com.example.restaurant.dish.Drink;
import com.example.restaurant.dish.Lunch;
import com.example.restaurant.dish.MainCourse;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderTest {

    @Test
    public void verifyOrderIsCorrectlyMarkedAsEmptyOrComplete() {
        Order order = new Order();
        assertThat(order.isEmpty()).isTrue();
        assertThat(order.isComplete()).isFalse();
        order.setDrink(new Drink("Sample drink", 5.5D));
        assertThat(order.isEmpty()).isFalse();
        assertThat(order.isComplete()).isFalse();
        Lunch lunch = new Lunch();
        lunch.setDessert(new Dessert("Sample dessert", 5.5D));
        lunch.setMainCourse(new MainCourse("Sample main course", 5.5D));
        order.setLunch(lunch);
        assertThat(order.isEmpty()).isFalse();
        assertThat(order.isComplete()).isTrue();
    }

}
